/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Entity.Cart;
import java.util.ArrayList;
import java.util.List;
import utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class CartDAO extends EduSysDAO<Cart, Integer> {

    final String INSERT_SQL = "INSERT INTO Cart (user_id, product_item_id, qty)\n"
            + "VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Cart SET user_id = ?, product_item_id= ?, qty = ? WHERE id = ?";
    final String UPDATE_SQL_QTITY = "UPDATE Cart SET   qty = ? WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM Cart where id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Cart";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Cart where id = ?";
    final String SELECT_BY_ID_USER = "SELECT * FROM Cart where user_id = ?";

    @Override
    public void insert(Cart entity) {
        jdbcHelper.update(INSERT_SQL,
                entity.getUser_id(),
                entity.getProduct_item_id(),
                entity.getQty());
    }

    @Override
    public void update(Cart entity) {
        jdbcHelper.update(UPDATE_SQL,
                entity.getUser_id(),
                entity.getProduct_item_id(),
                entity.getQty(),
                entity.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<Cart> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Cart selectById(Integer id) {
        List<Cart> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void updateQty(int id_cart, int qty) {
        jdbcHelper.update(UPDATE_SQL_QTITY, qty, id_cart);
    }

    public List<Cart> selectByIdUser(int id_user) {
        List<Cart> list = selectBySql(SELECT_BY_ID_USER, id_user);
        if (list.isEmpty()) {
            return new ArrayList<>(); // Trả về một danh sách rỗng thay vì null
        }
        return list;
    }

    @Override
    public List<Cart> selectBySql(String sql, Object... args) {
        List<Cart> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            try {
                while (rs.next()) {
                    Cart c = new Cart();
                    c.setId(rs.getInt("id"));
                    c.setUser_id(rs.getInt("user_id"));
                    c.setProduct_item_id(rs.getInt("product_item_id"));
                    c.setQty(rs.getInt("qty"));
                    list.add(c);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Map<String, Object>> getCartProduct(int user_id) {
        List<Map<String, Object>> cardItemDetails = new ArrayList<>();

        String sql = "EXEC getProductFromCart @User_id = ?";

        try (ResultSet rs = jdbcHelper.query(sql, user_id)) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; rs.getMetaData().getColumnCount() >= i; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                cardItemDetails.add(row);
            }
        } catch (Exception e) {
            System.out.println("Lỗi cart_Item_DAO");
            throw new RuntimeException(e);
        }

        return cardItemDetails;
    }

    public static void main(String[] args) {

        CartDAO daoCart = new CartDAO();

        List<Map<String, Object>> list = daoCart.getCartProduct(2);
        for (Map<String, Object> map : list) {
            int qty = (int) map.get("qty");
            double price = (double) map.get("price");
            double total = (double) map.get("total");
            String name = (String) map.get("name");
            String img = (String) map.get("img");
            System.out.println(price + " " + total + " " + name + " " + img + " " + qty);

        }

    }

}

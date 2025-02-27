package dao;

import Entity.Product_Item;
import java.util.List;
import utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class Product_ItemDAO extends EduSysDAO<Product_Item, Integer> {

    final String INSERT_SQL = "INSERT INTO Product_Item (product_id, qty_in_stock, price, original_price )\n"
            + "VALUES (?, ?, ?,?);";
    final String UPDATE_SQL = "UPDATE Product_Item\n"
            + "SET product_id = ?, qty_in_stock = ?, price = ?, original_price = ?, update_at = GETDATE()\n"
            + "WHERE id = ?;";
    final String DELETE_SQL = "DELETE FROM Product_Item where id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Product_Item";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Product_Item where id = ?";

    @Override
    public void insert(Product_Item entity) {
        jdbcHelper.update(INSERT_SQL,
                entity.getProduct_id(),
                entity.getQty_in_stock(),
                entity.getPrice(),
                entity.getOriginal_price());
    }

    @Override
    public void update(Product_Item entity) {
        jdbcHelper.update(UPDATE_SQL,
                entity.getProduct_id(),
                entity.getQty_in_stock(),
                entity.getPrice(),
                entity.getOriginal_price(),
                entity.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<Product_Item> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Product_Item selectById(Integer id) {
        List<Product_Item> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Product_Item> selectBySql(String sql, Object... args) {
        List<Product_Item> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            try {
                while (rs.next()) {
                    Product_Item entity = new Product_Item();
                    entity.setId(rs.getInt("id"));
                    entity.setProduct_id(rs.getInt("product_id"));
                    entity.setQty_in_stock(rs.getInt("qty_in_stock"));
                    entity.setPrice(rs.getFloat("price"));
                    entity.setOriginal_price(rs.getFloat("original_price"));
                    entity.setCreate_at(rs.getDate("create_at"));
                    entity.setUpdate_at(rs.getDate("update_at"));
                    list.add(entity);
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

    public Map<String, Object> GetProductDetails(int order_id) {
        Map<String, Object> productDetails = new HashMap<>();

        String sql = "EXEC GetProductDetails @product_id = ?";

        try (ResultSet rs = jdbcHelper.query(sql, order_id)) {
            if (rs.next()) {
                for (int i = 1; rs.getMetaData().getColumnCount() >= i; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Object value = rs.getObject(i);
                    productDetails.put(columnName, value);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi Product_Item_DAO");
            throw new RuntimeException(e);
        }

        return productDetails;
    }
     public Map<String, Object> GetProductReviewCountsRating (int order_id) {
        Map<String, Object> countRating = new HashMap<>();

        String sql = "EXEC GetProductReviewCounts @product_id  = ?";

        try (ResultSet rs = jdbcHelper.query(sql, order_id)) {
            if (rs.next()) {
                for (int i = 1; rs.getMetaData().getColumnCount() >= i; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Object value = rs.getObject(i);
                    countRating.put(columnName, value);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi Product_Item_DAO");
            throw new RuntimeException(e);
        }

        return countRating;
    }
    
    

    public static void main(String[] args) {
        Product_ItemDAO dao = new Product_ItemDAO();
        Map<String, Object> list = dao.GetProductReviewCountsRating(2);
        int stoc = (int) list.get("five_star_count");
         int stoc1 = (int) list.get("four_star_count");
        
        System.out.println("count 5" + stoc);
        System.out.println("count 4" + stoc1);
    }

}

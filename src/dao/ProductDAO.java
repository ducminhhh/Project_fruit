/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Entity.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.jdbcHelper;
import java.sql.ResultSetMetaData;

/**
 *
 * @author PC
 */
public class ProductDAO extends EduSysDAO<Product, Integer>{
    final String INSERT_SQL = "INSERT INTO Product (category_id, name, description)\n"
                            + "VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE Product\n" +
                              "SET name = ?, description = ?,update_at = GETDATE()\n" +
                              "WHERE id = ?;";
    final String DELETE_SQL = "DELETE FROM Product\n" +
                              "WHERE id = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM Product";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Product where id = ?";
    @Override
    public void insert(Product entity) {
        jdbcHelper.update(INSERT_SQL, 
                            entity.getCategory_id(),
                            entity.getName(),
                            entity.getDescription());
    }

    @Override
    public void update(Product entity) {
        jdbcHelper.update(UPDATE_SQL,
                            entity.getName(),
                            entity.getDescription(),
                            entity.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<Product> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Product selectById(Integer id) {
        List<Product> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Product> selectBySql(String sql, Object... args) {
        List<Product> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            try {
                while(rs.next())
                {
                    Product entity = new Product();
                    entity.setId(rs.getInt("id"));
                    entity.setCategory_id(rs.getInt("category_id"));
                    entity.setName(rs.getString("name"));
                    entity.setDescription(rs.getString("description"));
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
    
    public static List<Map<String, Object>> getDataFromStoredProcedure() {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query("EXEC GetData");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                result.add(row);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
    
    
    public static void main(String[] args) {
       List<Map<String, Object>> data = ProductDAO.getDataFromStoredProcedure();
        for (Map<String, Object> row : data) {
            System.out.println("thumbnail"+row.get("thumbnail"));
              System.out.println("ifd"+row.get("id"));
        }
    }
                
    }
    


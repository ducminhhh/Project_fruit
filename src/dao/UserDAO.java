/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Entity.User;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import utils.jdbcHelper;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class UserDAO extends EduSysDAO<User, String>{

    final String INSERT_SQL = "INSERT INTO [User] (name, password, phone, email)\n" +
                              "VALUES (?, ?, ?, ?);";
    final String UPDATE_SQL = "UPDATE [User] SET name = ?, password= ?, phone = ?, email = ?  WHERE id = ?";
    final String DELETE_SQL = "DELETE FROM [User] where id = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM [User]";
       
    final String SELECT_BY_ID_SQL = "SELECT * FROM [User] where id = ?";
    
    @Override
    public void insert(User entity) {
        jdbcHelper.update(INSERT_SQL,
                          entity.getName(),
                          entity.getPassword(),
                          entity.getPhone(),
                          entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcHelper.update(UPDATE_SQL,
                          entity.getName(),
                          entity.getPassword(),
                          entity.getPhone(),
                          entity.getEmail(),
                          entity.getId());    
    }

    @Override
    public void delete(String id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<User> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public User selectById(String id) {
        List<User> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);    
    }
    
    

    @Override
    public List<User> selectBySql(String sql, Object... args) {
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            try {
                while (rs.next()) {
                    User entity = new User();
                    entity.setId(rs.getString("id"));
                    entity.setPassword(rs.getString("password"));
                    entity.setName(rs.getString("name"));
                    entity.setEmail(rs.getString("email"));
                    entity.setPhone(rs.getString("phone"));
                    entity.setRole(rs.getBoolean("role"));
                    entity.setCreateAt(rs.getDate("create_at"));
                    entity.setUpdateAt(rs.getDate("update_at"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;    }
    
    public User selectByEmail(String Email) {
        String sql = "SELECT * FROM [User] WHERE email = ?";
        List<User> list = this.selectBySql(sql, Email);
        return list.size() > 0 ? list.get(0) : null;
    }    
public User login(User user) throws SQLException {
  
    String sql = "SELECT * FROM [User] WHERE email = ? AND password = ?";
    
    try (PreparedStatement pstmt = jdbcHelper.getStmt(sql)) {
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (user != null && rs.next()) {
            user.setId(rs.getString("id")); 
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
             user.setPhone(rs.getString("phone")); 
            user.setRole(rs.getBoolean("role"));
            user.setCreateAt(rs.getDate("create_at"));
            user.setUpdateAt(rs.getDate("update_at"));
            user.setName(rs.getString("name"));
        } 
        
    } catch (SQLException e) {
        e.getMessage();
      
    }
    return user;
}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Entity.Address;
import com.sun.jdi.connect.spi.Connection;
import java.util.List;
import utils.jdbcHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class AddressDAO {

    final static String INSERT_SQL_Client = "EXEC sp_CreateAddressClient @user_id = ? , @city= ?, @ward = ?, @street = ?";
    final static String UPDATE_SQL = "EXEC UpdateUserAddress @user_id = ?, @address_id = ?, @city = ?, @ward = ?, @street = ?";
    final static String DELETE_SQL = "EXEC DeleteUserAddress @user_id = ?, @address_id = ?";
    private static final String SELECT_BY_USER_ID_SQL
            = "SELECT a.id, a.city, a.ward, a.street, ua.is_Default "
            + "FROM User_Address ua "
            + "JOIN Address a ON ua.address_id = a.id "
            + "WHERE ua.user_id = ?";
    private static final String SELECT_ALL = "SELECT \n"
            + "	a.id,\n"
            + "	ua.user_id,\n"
            + "    a.city, \n"
            + "    a.ward, \n"
            + "    a.street\n"
            + "FROM User_Address ua\n"
            + "JOIN Address a ON ua.address_id = a.id";
  
     

    public static void insert(int userId, String city, String ward, String street) {
        jdbcHelper.update(INSERT_SQL_Client, userId, city, ward, street );
    }

    public static void update(int userId, int address_id, String city, String ward, String street) {
        jdbcHelper.update(UPDATE_SQL, userId, address_id, city, ward, street);
    }

    public static void delete(int user_id, int address_id) {
        jdbcHelper.update(DELETE_SQL, user_id, address_id);
    }

    public static List<Address> selectByUserId(int userId) {
        List<Address> addresses = new ArrayList<>();

        try (ResultSet rs = jdbcHelper.query(SELECT_BY_USER_ID_SQL, userId)) {
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getString("id"));
                address.setCity(rs.getString("city"));
                address.setWard(rs.getString("ward"));
                address.setStreet(rs.getString("street"));
                // Assuming you want to add isDefault to the Address class
                address.setIsDefault(rs.getBoolean("is_Default"));

                addresses.add(address);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return addresses;
    }

     public static List<Object> getAllAddresses() {
        List<Object> addresses = new ArrayList<>();
        
       try (ResultSet rs = jdbcHelper.query(SELECT_ALL)){

            while (rs.next()) {
                Map<String, Object> address = new HashMap<>();
                address.put("id", rs.getInt("id"));
                address.put("user_id", rs.getInt("user_id"));
                address.put("city", rs.getString("city"));
                address.put("ward", rs.getString("ward"));
                address.put("street", rs.getString("street"));
                
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return addresses;
    }
   public static void main(String[] args) {
        AddressDAO addressDAO = new AddressDAO();
        List<Object> addresses = addressDAO.getAllAddresses();

        // In ra kết quả
        for (Object obj : addresses) {
            if (obj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> address = (Map<String, Object>) obj;
                System.out.println("ID: " + address.get("id"));
                System.out.println("User ID: " + address.get("user_id"));
                System.out.println("City: " + address.get("city"));
                System.out.println("Ward: " + address.get("ward"));
                System.out.println("Street: " + address.get("street"));
                System.out.println("--------------------");
            }
        }
    }

}

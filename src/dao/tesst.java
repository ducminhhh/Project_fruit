/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Entity.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public class tesst {
   
    public static void main(String[] args) {
       UserDAO dao = new UserDAO();
       List<User> list= dao.selectAll();
        for (User user : list) {
            System.out.println(user.toString());
        }
    }
            
}

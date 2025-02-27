package utils;

import Entity.User;
import dao.UserDAO;
import java.sql.SQLException;
import ui.DangNhap;



public class Check {
   
    public static User user;

//    Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
 
    public static void clear() {
        Check.user = null;
    }

//     Kiểm tra xem đăng nhập hay chưa
   
    public static boolean isLogin() throws SQLException {
//     UserDAO userDao = new UserDAO();
//        user =  userDao.login(user);
////        System.out.println(user);
//      if (user.getName() == null) {
//          return false;
//      
//      } 
        return true;
    }
     
//      Kiểm tra xem có phải là trưởng phòng hay không
     
//    public static boolean isManager() {
//        return Check.isLogin() && user.isRole();
//    }
    public static void main(String[] args) {
    
    }
}

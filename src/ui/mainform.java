/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import Entity.Address;
import Entity.Cart;
import Entity.Gallery;
import Entity.Order;
import Entity.Review;
import Entity.User;
import Entity.User_Payment_Method;
import com.formdev.flatlaf.FlatLightLaf;
import com.sun.jdi.connect.Connector;
import static com.sun.source.util.DocTrees.instance;
import static com.sun.source.util.JavacTask.instance;
import static com.sun.source.util.Trees.instance;
import dao.AddressDAO;
import dao.CartDAO;
import dao.GalleryDAO;
import dao.OrderDAO;
import dao.Order_ItemDAO;
import dao.ProductDAO;
import dao.Product_ItemDAO;
import dao.ReviewDAO;
import dao.UserDAO;
import dao.User_Payment_MethodDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.html.parser.DTD;
import utils.Check;
import utils.XDate;

/**
 *
 * @author Admin
 */
public class mainform extends javax.swing.JFrame {

    private ArrayList<product> pr;
    private ArrayList<OrderDetail> listOrder;
    public static User user;
    private ArrayList<product> list_chiTietThanhToan;
    private ArrayList<comment> comment_User;
    private JPanel pnlAllItem, pnlItem, pnlHinh, pnlTen, pnlGia, pnlButton, pnlImageItems, pnlBoxComment, pnlAvata,
            pnlBoxComment2, pnlImg_Comment, pnlText_Comment, pnlTen_Ngay, pnlCheckMuaHang, pnlRate_star, pnlComment_User,
            pnlOrder, pnlMaHoaDon, pnlTongTien, pnlNgayMuaHang, pnlChiTiet, pnlInformation_Product, pnlImg_Information,
            pnlText_Information, pnlTenSP_Loai_infor, pnlSoLuong_GiaDon_GiaTong, pnlAddressOne, pnlSTTaddress, pnlAddresEdit, pnlBtnChiTietAddress;
    private JLabel lblHinh, lblTenHinh, lblGia, lblDonViGia, lblItemImg, lblTenUser, lblThoiGianMua, lblCommentUser, lblAvata_Comment,
            lblIconCheck, lblTextCheck, lblIconStar, lblNumberRate, lblTextRate, lblText_User_Comment,
            lblMaHoaDon, lblTongTien, lblNgayMuaHang, lblChiTiet, lblImg_Information, lblTextInformation,
            lblTenProd_Info, lblLoaiPr_Info, lblSoLuong_Order, lblGiaDon_Order, lblTongGia_Order, lbl,
            lblSTTAddress, lblAddress;

    private JButton btnLike, bntAddtoCart, BtnChinhSua, btnMacDinh;
    private int id_order, id_user_order;
    int countQty = 1;
    boolean qty_UP = true;
    private static final int PRODUCTS_PER_PAGE = 8;
    private int currentPage = 0;
    private JPanel pnlYeuThich;
    private JButton btnYeuThich;
    private JPanel pnlitem;
    private JPanel pnlHinhGioHang;
    private JPanel pnlTextGioHang;
    private JLabel lblTextGioHang;
    private JPanel pnlTitleGioHang;
    private JLabel lblTenSanPhamGioHang;
    private JButton btnRemvoeItemGioHang;
    private JPanel pnlGiaGioHang;
    private JLabel lblGiaGioHang;
    private JPanel pnlSoLuong;
    private JLabel lblSoLuongGioHang;
    private JPanel pnlTongTienGioHang;
    private JLabel lblTongTienGioHang;
    private JTextField txtSoLuongGioHang;
    private JLabel lblHinhGioHang;

    private JPanel pnlSanPhamChitietThanhToan;
    private JLabel lblTenSanPhamChiTietThanhToan;
    private JLabel lblgiaChiTietThanhToan;

    public mainform() {

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        pnlSanPhamThanhToan_ChiTiet.setPreferredSize(new Dimension(600, 300));
        pnlSanPhamThanhToan_ChiTiet.setMaximumSize(new Dimension(600, 300));
        pnlSanPhamThanhToan_ChiTiet.setMinimumSize(new Dimension(600, 300));
        pnlProfileSetSize.setPreferredSize(new Dimension(1100, 1450));
        pnlProfileSetSize.setMaximumSize(new Dimension(1100, 1450));
        pnlProfileSetSize.setMinimumSize(new Dimension(1100, 1450));

        if (Check.user != null) {
            user = Check.user;

            lblWelcome.setText("welcome " + user.getName());
            btnDangNhap.setEnabled(false);
        }
        System.out.println(user);

        renderPRODUCT();
        hoverBtnProfile();
        renderDETAIL_ORDER();
        renderWishLists();
        renderAddress_User();
        pnlBtnNav.setVisible(false);

    }

    public void renderChiTietThanhToan() {
        pnlSanPhamThanhToan_ChiTiet.removeAll();

        if (user != null) {
            CartDAO daoCart = new CartDAO();
            List<Map<String, Object>> listCart = daoCart.getCartProduct(Integer.parseInt(user.getId()));
            System.out.println(listCart);
            pnlSanPhamThanhToan_ChiTiet.setLayout(new BoxLayout(pnlSanPhamThanhToan_ChiTiet, BoxLayout.Y_AXIS));
            double totalAmo = 0;
            for (Map<String, Object> pr : listCart) {
                int idCart = (int) pr.get("id");
                int qty = (int) pr.get("qty");
                double price = (double) pr.get("price");
                double total = (double) pr.get("total");
                String name = (String) pr.get("name");

                totalAmo += total;
                pnlSanPhamChitietThanhToan = new JPanel(new FlowLayout(FlowLayout.LEFT));
                pnlSanPhamChitietThanhToan.setBackground(Color.white);
                Dimension sizeChiTietThanhToan = new Dimension(665, 50);
                pnlSanPhamChitietThanhToan.setPreferredSize(sizeChiTietThanhToan);
                pnlSanPhamChitietThanhToan.setMaximumSize(sizeChiTietThanhToan);
                pnlSanPhamChitietThanhToan.setMinimumSize(sizeChiTietThanhToan);
                lblTenSanPhamChiTietThanhToan = new JLabel(name + " X" + qty);
                lblTenSanPhamChiTietThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 22));

                lblgiaChiTietThanhToan = new JLabel(String.valueOf(price) + " VNĐ / Kg");
                lblgiaChiTietThanhToan.setFont(new Font("Segoe UI", Font.PLAIN, 18));

                pnlSanPhamChitietThanhToan.add(lblTenSanPhamChiTietThanhToan);

                pnlSanPhamChitietThanhToan.add(Box.createHorizontalStrut(350));

                pnlSanPhamChitietThanhToan.add(lblgiaChiTietThanhToan);
                pnlSanPhamThanhToan_ChiTiet.add(pnlSanPhamChitietThanhToan);

            }
            lblTotal.setText(String.valueOf(totalAmo));

        } else {

            System.out.println("user is null ");
        }

    }

    public void renderAddress_User() {
        pnlAddress_User.setLayout(new BoxLayout(pnlAddress_User, BoxLayout.Y_AXIS));
        AddressDAO dao = new AddressDAO();
        List<Address> listAddress = dao.selectByUserId(1);

        for (Address ad : listAddress) {

            pnlAddressOne = new JPanel();
            pnlAddressOne.setBackground(Color.white);
            pnlAddressOne.setBorder(new MatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));
            pnlAddressOne.setLayout(new BoxLayout(pnlAddressOne, BoxLayout.X_AXIS));
            pnlAddressOne.setPreferredSize(new Dimension(900, 150));
            pnlAddressOne.setMaximumSize(new Dimension(900, 150));
            pnlAddressOne.setMinimumSize(new Dimension(900, 150));

            pnlSTTaddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
            Dimension sizeSTTaddress = new Dimension(185, 50);
            pnlSTTaddress.setPreferredSize(sizeSTTaddress);
            pnlSTTaddress.setMinimumSize(sizeSTTaddress);
            pnlSTTaddress.setMaximumSize(sizeSTTaddress);
            pnlSTTaddress.setBackground(Color.white);
            lblSTTAddress = new JLabel(ad.getId());//  STT ở đâyyyyyyyyyyyyyy
            lblSTTAddress.setFont(new Font("Segoe UI", Font.BOLD, 12));
            pnlSTTaddress.add(Box.createHorizontalStrut(10));
            pnlSTTaddress.add(lblSTTAddress);

            pnlAddresEdit = new JPanel(new FlowLayout(FlowLayout.LEFT));
            Dimension sizeAddresEdit = new Dimension(560, 50);
            pnlAddresEdit.setPreferredSize(sizeAddresEdit);
            pnlAddresEdit.setMinimumSize(sizeAddresEdit);
            pnlAddresEdit.setMaximumSize(sizeAddresEdit);
            pnlAddresEdit.setBackground(Color.white);
            lblAddress = new JLabel(ad.getStreet() + " " + ad.getWard() + " " + ad.getCity());// ADDRESS hereeeeeeeeeeeeeeee
            lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 14));
            pnlAddresEdit.add(lblAddress);

            pnlBtnChiTietAddress = new JPanel();
            pnlBtnChiTietAddress.setLayout(new BoxLayout(pnlBtnChiTietAddress, BoxLayout.Y_AXIS));
            Dimension sizeBtnChiTietAddress = new Dimension(150, 90);
            pnlBtnChiTietAddress.setPreferredSize(sizeBtnChiTietAddress);
            pnlBtnChiTietAddress.setMinimumSize(sizeBtnChiTietAddress);
            pnlBtnChiTietAddress.setMaximumSize(sizeBtnChiTietAddress);
            pnlBtnChiTietAddress.setBackground(Color.white);
            BtnChinhSua = new JButton("Chỉnh Sửa");
            Dimension SizeBtnChinhSua = new Dimension(140, 40);
            BtnChinhSua.setPreferredSize(SizeBtnChinhSua);
            BtnChinhSua.setMinimumSize(SizeBtnChinhSua);
            BtnChinhSua.setMaximumSize(SizeBtnChinhSua);
            BtnChinhSua.setFont(new Font("Segoe UI", Font.BOLD, 15));
            BtnChinhSua.setForeground(new Color(24, 135, 84));
            BtnChinhSua.addActionListener((e) -> {
                txtEditCity.setText(ad.getCity());
                txtEditWard.setText(ad.getWard());
                txtEditStreet.setText(ad.getStreet());
                pnlTabAll_profile.removeAll();
                pnlTabAll_profile.add(pnlEditAddress);
                pnlTabAll_profile.repaint();
                pnlTabAll_profile.revalidate();
            });

            btnMacDinh = new JButton("Mặc Định");
            Dimension SizebtnMacDinh = new Dimension(140, 40);
            btnMacDinh.setPreferredSize(SizebtnMacDinh);
            btnMacDinh.setMinimumSize(SizebtnMacDinh);
            btnMacDinh.setMaximumSize(SizebtnMacDinh);
            btnMacDinh.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btnMacDinh.setForeground(new Color(24, 135, 84));

            pnlBtnChiTietAddress.add(BtnChinhSua);
            pnlBtnChiTietAddress.add(Box.createVerticalStrut(10));
            pnlBtnChiTietAddress.add(btnMacDinh);

            pnlAddressOne.add(pnlSTTaddress);
            pnlAddressOne.add(pnlAddresEdit);
            pnlAddressOne.add(pnlBtnChiTietAddress);

//         pnlAddress_User.add(Box.createVerticalStrut(30));
            pnlAddress_User.add(pnlAddressOne);
        }

    }

    public void renderGioHang() {

        pnlitems.removeAll();
        CartDAO daoCart = new CartDAO();
        pnlitems.setLayout(new BoxLayout(pnlitems, BoxLayout.Y_AXIS));
        List<Map<String, Object>> listCart = daoCart.getCartProduct(Integer.parseInt(user.getId()));
        System.out.println(listCart);
        lblCartQty.setText("" + listCart.size());
        double totalAmo = 0;

        for (Map<String, Object> map : listCart) {
            int idCart = (int) map.get("id");
            int qty = (int) map.get("qty");
            double price = (double) map.get("price");
            double total = (double) map.get("total");
            String name = (String) map.get("name");
            String imgManh = (String) map.get("img");
            totalAmo += total;
            pnlitem = new JPanel();
            pnlitem.setLayout(new BoxLayout(pnlitem, BoxLayout.X_AXIS));
            pnlitem.setBackground(Color.white);
            pnlitem.setBorder(new MatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));
            Dimension SizeItem = new Dimension(700, 230);
            pnlitem.setPreferredSize(SizeItem);
            pnlitem.setMaximumSize(SizeItem);
            pnlitem.setMinimumSize(SizeItem);

            pnlHinhGioHang = new JPanel();
            pnlHinhGioHang.setBackground(Color.white);
            Dimension size = new Dimension(250, 200);
            pnlHinhGioHang.setPreferredSize(size);
            pnlHinhGioHang.setMaximumSize(size);
            pnlHinhGioHang.setMinimumSize(size);

            pnlTextGioHang = new JPanel();
            pnlTextGioHang.setBackground(Color.white);
            pnlTextGioHang.setLayout(new BoxLayout(pnlTextGioHang, BoxLayout.Y_AXIS));
            Dimension SizeTextGioHang = new Dimension(500, 200);
            pnlTextGioHang.setPreferredSize(SizeTextGioHang);
            pnlTextGioHang.setMaximumSize(SizeTextGioHang);
            pnlTextGioHang.setMinimumSize(SizeTextGioHang);

            lblTextGioHang = new JLabel();

            pnlTitleGioHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlTitleGioHang.setBackground(Color.white);
            Dimension SizeTilteGioHang = new Dimension(500, 50);
            pnlTitleGioHang.setPreferredSize(SizeTilteGioHang);
            pnlTitleGioHang.setMaximumSize(SizeTilteGioHang);
            pnlTitleGioHang.setMinimumSize(SizeTilteGioHang);

            lblTenSanPhamGioHang = new JLabel(name);
            lblTenSanPhamGioHang.setBackground(Color.white);
            Dimension SizelblTenSanPhamGioHang = new Dimension(200, 50);
            lblTenSanPhamGioHang.setPreferredSize(SizelblTenSanPhamGioHang);
            lblTenSanPhamGioHang.setMaximumSize(SizelblTenSanPhamGioHang);
            lblTenSanPhamGioHang.setMinimumSize(SizelblTenSanPhamGioHang);

            btnRemvoeItemGioHang = new JButton("Remove");

            pnlTitleGioHang.add(lblTenSanPhamGioHang);

            pnlTitleGioHang.add(Box.createHorizontalStrut(150));
            pnlTitleGioHang.add(btnRemvoeItemGioHang);
            lblTenSanPhamGioHang.setFont(new Font("Times New Roman", Font.BOLD, 25));

            pnlGiaGioHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlGiaGioHang.setBackground(Color.white);
            lblGiaGioHang = new JLabel("" + price + " " + "VNĐ");

            lblGiaGioHang.setFont(new Font("Times New Roman", Font.ITALIC, 25));
            pnlGiaGioHang.add(lblGiaGioHang);

            pnlSoLuong = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlSoLuong.setBackground(Color.white);
            Dimension SizeSoLuong = new Dimension(500, 30);
            pnlSoLuong.setPreferredSize(SizeSoLuong);
            pnlSoLuong.setMaximumSize(SizeSoLuong);
            pnlSoLuong.setMinimumSize(SizeSoLuong);

            lblSoLuongGioHang = new JLabel("Số Lượng : ");
            Dimension SizepnlSoLuong = new Dimension(100, 20);
            lblSoLuongGioHang.setPreferredSize(SizepnlSoLuong);
            lblSoLuongGioHang.setMaximumSize(SizepnlSoLuong);
            lblSoLuongGioHang.setMinimumSize(SizepnlSoLuong);

            lblSoLuongGioHang.setFont(new Font("Times New Roman", Font.BOLD, 15));
            pnlSoLuong.add(lblSoLuongGioHang);

            pnlTongTienGioHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlTongTienGioHang.setBackground(Color.white);
            txtSoLuongGioHang = new JTextField("" + qty);

            txtSoLuongGioHang.setPreferredSize(new Dimension(110, 30));

            btnRemvoeItemGioHang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CartDAO cartDao = new CartDAO();
                    cartDao.delete(idCart);

                    JOptionPane.showMessageDialog(null, "Đã xóa sản phẩm ra khỏi giỏ hàng của bạn");
                    renderGioHang();
                    pnlitems.repaint();
                    pnlitems.revalidate();

                }

            });

            lblTongTienGioHang = new JLabel("TỔNG: " + total + " VND");
            lblTongTienGioHang.setForeground(Color.red);
            lblTongTienGioHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
            pnlTongTienGioHang.add(txtSoLuongGioHang);
            pnlTongTienGioHang.add(Box.createHorizontalStrut(130));
            pnlTongTienGioHang.add(lblTongTienGioHang);

            pnlTextGioHang.add(pnlGiaGioHang);
            pnlTextGioHang.add(pnlTitleGioHang);
            pnlTextGioHang.add(pnlGiaGioHang);
            pnlTextGioHang.add(pnlSoLuong);
//                 pnlTextGioHang.add(Box.createVerticalStrut(-20));
            pnlTextGioHang.add(pnlTongTienGioHang);

            lblHinhGioHang = new JLabel();
            ImageIcon icon = new ImageIcon("D:\\cao_dang_fpt\\KY4\\ProjectBanTraiCay\\DuAn_1_PROJECT_FRUIT\\DuAn_1_PROJECT_FRUIT\\QuanLyVaCungCapTraiCay\\src\\products\\" + imgManh);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            lblHinhGioHang.setIcon(icon);
            pnlTextGioHang.add(lblTextGioHang);
            pnlHinhGioHang.add(lblHinhGioHang);

            pnlitem.add(pnlHinhGioHang);
            pnlitem.add(pnlTextGioHang);

            pnlitems.add(pnlitem);
        }

        lblTotal_Cart.setText("" + totalAmo);
    }

    public void renderWishLists() {
        pr = new ArrayList<>();
        pr.add(new product("Sản Phẩm 1", 10.99, "grape1.png"));
        pr.add(new product("Sản Phẩm 2", 15000000.99, "grape2.png"));
        pr.add(new product("Sản Phẩm 3", 8.99, "grape3.png"));
        pr.add(new product("Sản Phẩm 2", 15000000.99, "grape2.png"));
        pr.add(new product("Sản Phẩm 3", 8.99, "grape3.png"));

        pnlWishLists.setLayout(new BoxLayout(pnlWishLists, BoxLayout.Y_AXIS));

        for (product item : pr) {
            JPanel pnlWishListItem = new JPanel();
            pnlWishListItem.setLayout(new BoxLayout(pnlWishListItem, BoxLayout.X_AXIS));
            pnlWishListItem.setBackground(Color.WHITE);
            pnlWishListItem.setBorder(new MatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));
            Dimension sizeItem = new Dimension(1300, 230);
            pnlWishListItem.setPreferredSize(sizeItem);
            pnlWishListItem.setMaximumSize(sizeItem);
            pnlWishListItem.setMinimumSize(sizeItem);

            // Panel chứa hình ảnh
            JPanel pnlHinhWishList = new JPanel();
            pnlHinhWishList.setBackground(Color.WHITE);
            Dimension size = new Dimension(250, 200);
            pnlHinhWishList.setPreferredSize(size);
            pnlHinhWishList.setMaximumSize(size);
            pnlHinhWishList.setMinimumSize(size);
            JLabel lblHinhWishList = new JLabel();
            ImageIcon icon = new ImageIcon("D:\\cao_dang_fpt\\KY4\\ProjectBanTraiCay\\DuAn_1_PROJECT_FRUIT\\DuAn_1_PROJECT_FRUIT\\QuanLyVaCungCapTraiCay\\src\\products\\" + item.getImg());
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            lblHinhWishList.setIcon(icon);
            pnlHinhWishList.add(lblHinhWishList);

            // Panel chứa thông tin sản phẩm
            JPanel pnlTextWishList = new JPanel();
            pnlTextWishList.setBackground(Color.WHITE);
            pnlTextWishList.setLayout(new BoxLayout(pnlTextWishList, BoxLayout.Y_AXIS));
            Dimension sizeTextWishList = new Dimension(500, 200);
            pnlTextWishList.setPreferredSize(sizeTextWishList);
            pnlTextWishList.setMaximumSize(sizeTextWishList);
            pnlTextWishList.setMinimumSize(sizeTextWishList);

            // Tên sản phẩm
            JPanel pnlTitleWishList = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlTitleWishList.setBackground(Color.white);
            Dimension sizeTitleWishList = new Dimension(500, 30);
            pnlTitleWishList.setPreferredSize(sizeTitleWishList);
            pnlTitleWishList.setMaximumSize(sizeTitleWishList);
            pnlTitleWishList.setMinimumSize(sizeTitleWishList);
            JLabel lblTenSanPhamWishList = new JLabel(item.getName());
            lblTenSanPhamWishList.setFont(new Font("Times New Roman", Font.BOLD, 25));
            pnlTitleWishList.add(lblTenSanPhamWishList);

            // Giá sản phẩm
            JPanel pnlGiaWishList = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlGiaWishList.setBackground(Color.WHITE);
            JLabel lblGiaWishList = new JLabel(item.getPrice() + " VND");
            lblGiaWishList.setFont(new Font("Times New Roman", Font.ITALIC, 25));
            pnlGiaWishList.add(lblGiaWishList);

            // Số lượng sản phẩm
            JPanel pnlSoLuongWishList = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlSoLuongWishList.setBackground(Color.WHITE);
            JLabel lblSoLuongWishList = new JLabel("Tình trạng: CÒN HÀNG");
            lblSoLuongWishList.setFont(new Font("Times New Roman", Font.BOLD, 15));
            pnlSoLuongWishList.add(lblSoLuongWishList);

            pnlYeuThich = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlYeuThich.setBackground(Color.WHITE);
            Dimension sizePnlYeuThich = new Dimension(500, 50);
            pnlYeuThich.setPreferredSize(sizePnlYeuThich);
            pnlYeuThich.setMaximumSize(sizePnlYeuThich);
            pnlYeuThich.setMinimumSize(sizePnlYeuThich);
            btnYeuThich = new JButton("like");

            btnYeuThich.setPreferredSize(new Dimension(150, 40));
            btnYeuThich.setBackground(new Color(220, 53, 69));
            btnYeuThich.setForeground(Color.WHITE);
            pnlYeuThich.add(btnYeuThich);

            // Thêm các panel vào pnlTextWishList
            pnlTextWishList.add(pnlTitleWishList);
            pnlTextWishList.add(pnlSoLuongWishList);
            pnlTextWishList.add(pnlGiaWishList);
            pnlTextWishList.add(pnlYeuThich);

            // Thêm khoảng cách dọc giữa các mục
            pnlWishListItem.add(Box.createRigidArea(new Dimension(0, 10)));

            // Thêm panel hình ảnh và thông tin vào panel chính
            pnlWishListItem.add(pnlHinhWishList);
            pnlWishListItem.add(pnlTextWishList);

            pnlWishLists.add(pnlWishListItem);
        }
    }

    public void renderRatingNumber(int pr_id) {
        Product_ItemDAO dao = new Product_ItemDAO();
        Map<String, Object> list = dao.GetProductReviewCountsRating(pr_id);

        lblRatingFive.setText(String.valueOf(list.get("five_star_count")));
        lblRatingFour.setText(String.valueOf(list.get("four_star_count")));
        lblRatingThree.setText(String.valueOf(list.get("three_star_count")));
        lblRatingTwo.setText(String.valueOf(list.get("two_star_count")));
        lblRatingOne.setText(String.valueOf(list.get("one_star_count")));

    }

    public void renderPRODUCT() {
        ProductDAO prDao = new ProductDAO();
        GalleryDAO daoGa = new GalleryDAO();
        CartDAO daoCart = new CartDAO();

        if (user == null) {
            lblQtyCart.setText("0");
        } else {
            List<Cart> listCart = daoCart.selectByIdUser(Integer.parseInt(user.getId()));
            System.out.println(listCart.size());
            lblQtyCart.setText(String.valueOf(listCart.size()));
        }

        List<Map<String, Object>> listPR = prDao.getDataFromStoredProcedure();

        pnl_Products.removeAll();

        // Thiết lập layout và tạo các panel mới
        pnl_Products.setLayout(new BorderLayout());

        JPanel pnlPagination = new JPanel();
        pnlPagination.setBackground(Color.WHITE);
        pnlPagination.setPreferredSize(new Dimension(300, 120));
        pnlPagination.setMaximumSize(new Dimension(300, 120));
        pnlPagination.setMinimumSize(new Dimension(300, 120));
        pnlPagination.setLayout(new FlowLayout(FlowLayout.CENTER));

        int totalProducts = listPR.size();
        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        // Tạo và thêm panel để hiển thị sản phẩm
        JPanel pnlAllItem = new JPanel(new GridLayout(2, 4, 8, 8));
        pnlAllItem.setPreferredSize(new Dimension(900, 700));
        pnlAllItem.setBackground(Color.WHITE);

        int startIdx = currentPage * PRODUCTS_PER_PAGE;
        int endIdx = Math.min(startIdx + PRODUCTS_PER_PAGE, totalProducts);
        

        for (int i = startIdx; i < endIdx; i++) {
            Map<String, Object> row = listPR.get(i);
            for (String key : row.keySet()) {
            }
            String name = (String) row.get("name");
            double price = (double) row.get("price");
            int id = (Integer) row.get("id");

            List<String> listGa = daoGa.getThumnailByProductId(id);
            String thumbnail = (String) row.get("thumbnail");

            JPanel pnlItem = new JPanel();
            pnlItem.setLayout(new BoxLayout(pnlItem, BoxLayout.Y_AXIS));
            pnlItem.setPreferredSize(new Dimension(365, 500));
            pnlItem.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));

            JLabel lblHinh = new JLabel();
            ImageIcon icon = new ImageIcon("D:\\cao_dang_fpt\\KY4\\ProjectBanTraiCay\\DuAn_1_PROJECT_FRUIT\\DuAn_1_PROJECT_FRUIT\\QuanLyVaCungCapTraiCay\\src\\products\\" + listGa.get(0));

            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(250, 200, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            lblHinh.setIcon(icon);
            lblHinh.setPreferredSize(new Dimension(250, 250));

            lblHinh.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pnlTabAll.removeAll();
                    pnlTabAll.add(detail_product);
                    pnlTabAll.repaint();
                    pnlTabAll.revalidate();
                    renderDetail_PRODUCT(id);
                    renderComment(id);
                    renderRatingNumber(id);

                }

            });

            JLabel lblTenHinh = new JLabel(name);
            Font newFont = new Font("Segoe UI", Font.BOLD, 18);
            lblTenHinh.setForeground(new Color(61, 138, 106));
            lblTenHinh.setFont(newFont);

            JLabel lblDonViGia = new JLabel(price + " VNĐ/kg");
            Font newFont2 = new Font("Segoe UI", Font.BOLD, 14);
            lblDonViGia.setFont(newFont2);

            JPanel pnlHinh = new JPanel();
            pnlHinh.setPreferredSize(new Dimension(260, 260));

            JPanel pnlTen = new JPanel();
            JPanel pnlGia = new JPanel();
            JPanel pnlButton = new JPanel();
            pnlTen.setBackground(Color.WHITE);
            pnlGia.setBackground(Color.WHITE);
            pnlButton.setBackground(Color.WHITE);

            JButton bntAddtoCart = new JButton("ADD TO CART");
//            List<Cart> listCart = daoCart.selectByIdUser(2);
            bntAddtoCart.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "bạn phải đăng nhập mới thêm vào giỏ hàng được");

                    } else {

                        List<Cart> listCart = daoCart.selectByIdUser(Integer.parseInt(user.getId()));

                        Cart c = new Cart();
                        if (listCart.size() <= 0) {

                            // Nếu listCart là null hoặc rỗng, tạo mới một Cart
                            c.setUser_id(Integer.parseInt(user.getId()));
                            c.setProduct_item_id(id);
                            c.setQty(1);
                            daoCart.insert(c);

                        } else {
                            boolean found = false;
                            for (Cart cart : listCart) {
                                if (cart.getProduct_item_id() == id) {
                                    daoCart.updateQty(cart.getId(), cart.getQty() + 1);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
//                             Nếu sản phẩm chưa có trong giỏ hàng, tạo mới một Cart
                                c.setUser_id(Integer.parseInt(user.getId()));
                                c.setProduct_item_id(id);
                                c.setQty(1);
                                daoCart.insert(c);
                            }
                            lblQtyCart.setText(String.valueOf(listCart.size()));
                        }
                        JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào giỏ hàng");

                    }

//                  
//                    pnlTabAll.removeAll();
//                    pnlTabAll.add(pnlGioHang);
//                    pnlTabAll.repaint();
//                    pnlTabAll.revalidate();
//                    renderGioHang(id);
                }

            });

            bntAddtoCart.setPreferredSize(new Dimension(120, 30));
            Font fontbtnAdd = new Font("Segoe UI", Font.BOLD, 13);
            bntAddtoCart.setForeground(Color.WHITE);
            bntAddtoCart.setBackground(new Color(61, 138, 106));
            bntAddtoCart.setFont(fontbtnAdd);

            ImageIcon iconLikeIcon = new ImageIcon("D:\\Luyen_Cong\\Du_An_1\\QuanLyVaCungCapTraiCay\\src\\icon\\heart-regular.svg");
            JButton btnLike = new JButton("Like");
            Font fontbtnLike = new Font("Segoe UI", Font.BOLD, 13);
            btnLike.setFont(fontbtnLike);
            btnLike.setForeground(Color.WHITE);
            btnLike.setBackground(new Color(220, 53, 69));
            btnLike.setPreferredSize(new Dimension(60, 30));

            pnlHinh.add(lblHinh);
            pnlTen.add(lblTenHinh);
            pnlGia.add(lblDonViGia);
            pnlButton.add(Box.createHorizontalStrut(-10));
            pnlButton.add(btnLike);
            pnlButton.add(bntAddtoCart);
            pnlItem.add(pnlHinh);
            pnlItem.add(pnlTen);
            pnlItem.add(pnlGia);
            pnlItem.add(pnlButton);
            pnlAllItem.add(pnlItem);

            lblHinh.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pnlItem.setBackground(Color.LIGHT_GRAY); // Màu khi di chuột vào
                    pnlItem.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pnlItem.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));
                }
            });
        }

        // Tạo các nút điều hướng
        JButton btnPrev = new JButton("Previous");
        btnPrev.setPreferredSize(new Dimension(140, 40));
        btnPrev.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnPrev.setForeground(new Color(25, 135, 84));
        btnPrev.setEnabled(currentPage > 0);
        btnPrev.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                renderPRODUCT(); // Cập nhật lại trang
            }
        });

        JButton btnNext = new JButton("Next");
        btnNext.setPreferredSize(new Dimension(140, 40));
        btnNext.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnNext.setEnabled(currentPage < totalPages - 1);
        btnNext.setForeground(new Color(25, 135, 84));
//           btnNext.setBackground(currentPage < totalPages - 1 ?  btnNext.setEnabled(true) : Color.WHITE);
        btnNext.addActionListener(e -> {
            if (currentPage < totalPages - 1) {
                currentPage++;
                renderPRODUCT(); // Cập nhật lại trang
            }
        });
        pnlPagination.add(Box.createVerticalStrut(140));
        pnlPagination.add(btnPrev);

        // Tạo các nút số trang
        for (int i = 0; i < totalPages; i++) {
            int pageIndex = i; // Cần final hoặc hiệu ứng tương tự để sử dụng trong lambda
            JButton btnPage = new JButton(String.valueOf(i + 1));
            btnPage.setPreferredSize(new Dimension(45, 36));
            btnPage.setFont(new Font("Segoe UI", Font.BOLD, 14));

            btnPage.setBackground(currentPage == pageIndex ? new Color(25, 135, 84) : Color.WHITE); // Màu cho nút hiện tại
            btnPage.setForeground(currentPage == pageIndex ? Color.WHITE : new Color(25, 135, 84));
            btnPage.addActionListener(e -> {
                currentPage = pageIndex;
                renderPRODUCT(); // Cập nhật lại trang
            });
            pnlPagination.add(btnPage);
        }

        pnlPagination.add(btnNext);

        pnl_Products.add(pnlAllItem, BorderLayout.CENTER);
        pnl_Products.add(pnlPagination, BorderLayout.SOUTH);

        // Làm mới panel
        pnl_Products.revalidate();
        pnl_Products.repaint();
    }

    public void render_Information_Product_Order() {
        Order_ItemDAO dao = new Order_ItemDAO();

        List<Map<String, Object>> listOrderItemDetail = dao.getOrderItemDetails(id_order);

//        UserDAO daoUser = new UserDAO();
//        User u = daoUser.selectById(String.valueOf(id_user_order));
//       
        lblNameUser.setText(user.getName());
        lblSdtUser.setText(user.getPhone());
        pnlInformation_product_ALL.removeAll();
        pnlInformation_product_ALL.setLayout(new BoxLayout(pnlInformation_product_ALL, BoxLayout.Y_AXIS));

        int countQty = 0;
        for (Map<String, Object> p : listOrderItemDetail) {
            String anh = (String) p.get("thumbnail");
            System.out.println(anh);
            int qty = (int) p.get("qty");
            countQty += qty;
            double price = (double) p.get("price");
            double total = (double) p.get("total");
            String namePR = (String) p.get("name");
            double total_amount = (double) p.get("total_amount");
            String address = (String) p.get("shipping_address");
            String payment_type_name = (String) p.get("payment_type_name");
            lblAddress_User.setText(address);
            lblpayment_type_name.setText(payment_type_name);
            lbltotal_amount.setText(total_amount + "đ");

            pnlInformation_Product = new JPanel();
            pnlInformation_Product.setLayout(new BoxLayout(pnlInformation_Product, BoxLayout.X_AXIS));
            Dimension sizeInformation_Product = new Dimension(890, 100);
            pnlInformation_Product.setPreferredSize(sizeInformation_Product);
            pnlInformation_Product.setMaximumSize(sizeInformation_Product);
            pnlInformation_Product.setMinimumSize(sizeInformation_Product);
            pnlInformation_Product.setBackground(Color.WHITE);
            pnlInformation_Product.setBorder(new MatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));

            pnlImg_Information = new JPanel();
            pnlImg_Information.setBackground(Color.WHITE);
            Dimension size = new Dimension(140, 93);
            pnlImg_Information.setPreferredSize(size);
            pnlImg_Information.setMaximumSize(size);
            pnlImg_Information.setMinimumSize(size);
            lblImg_Information = new JLabel();
            ImageIcon iconMain = new ImageIcon("D:\\cao_dang_fpt\\KY4\\ProjectBanTraiCay\\DuAn_1_PROJECT_FRUIT\\DuAn_1_PROJECT_FRUIT\\QuanLyVaCungCapTraiCay\\src\\products\\" + anh);
            Image img = iconMain.getImage();
            Image newImg = img.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
            iconMain = new ImageIcon(newImg);
            lblImg_Information.setIcon(iconMain);
            pnlImg_Information.add(lblImg_Information);

            pnlText_Information = new JPanel();
            pnlText_Information.setLayout(new BoxLayout(pnlText_Information, BoxLayout.Y_AXIS));
            pnlText_Information.setBackground(Color.red);
            Dimension sizeText_Information = new Dimension(730, 93);
            pnlText_Information.setPreferredSize(sizeText_Information);
            pnlText_Information.setMaximumSize(sizeText_Information);
            pnlText_Information.setMinimumSize(sizeText_Information);

            pnlTenSP_Loai_infor = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlTenSP_Loai_infor.setBackground(Color.WHITE);
            lblTenProd_Info = new JLabel(namePR);
            lblTenProd_Info.setFont(new Font("Times New Roman", Font.BOLD, 21));
            lblTenProd_Info.setForeground(new Color(25, 135, 84));
            lblLoaiPr_Info = new JLabel("(Loại 1)");
            lblLoaiPr_Info.setFont(new Font("Segoe UI", Font.BOLD, 13));
            pnlTenSP_Loai_infor.add(lblTenProd_Info);
            pnlTenSP_Loai_infor.add(lblLoaiPr_Info);

            pnlSoLuong_GiaDon_GiaTong = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlSoLuong_GiaDon_GiaTong.setBackground(Color.WHITE);
            Dimension sizeSoLuong_GiaDon_GiaTong = new Dimension(900, 40);
            pnlSoLuong_GiaDon_GiaTong.setPreferredSize(sizeSoLuong_GiaDon_GiaTong);
            pnlSoLuong_GiaDon_GiaTong.setMaximumSize(sizeSoLuong_GiaDon_GiaTong);
            pnlSoLuong_GiaDon_GiaTong.setMinimumSize(sizeSoLuong_GiaDon_GiaTong);

            lblSoLuong_Order = new JLabel("Số Lượng :  " + qty);
            lblSoLuong_Order.setFont(new Font("Segoe UI", Font.BOLD, 13));
            Dimension sizeSoLuong_Order = new Dimension(210, 30);
            lblSoLuong_Order.setPreferredSize(sizeSoLuong_Order);
            lblSoLuong_Order.setMaximumSize(sizeSoLuong_Order);
            lblSoLuong_Order.setMinimumSize(sizeSoLuong_Order);

            lblGiaDon_Order = new JLabel(price + "  đ ");
            lblGiaDon_Order.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 19));
            lblGiaDon_Order.setForeground(new Color(25, 135, 84));
            Dimension sizeGiaDon_Order = new Dimension(230, 30);
            lblGiaDon_Order.setPreferredSize(sizeGiaDon_Order);
            lblGiaDon_Order.setMaximumSize(sizeGiaDon_Order);
            lblGiaDon_Order.setMinimumSize(sizeGiaDon_Order);

            lblTongGia_Order = new JLabel(total + "đ");
            lblTongGia_Order.setFont(new Font("Segoe UI", Font.CENTER_BASELINE, 22));
            lblTongGia_Order.setForeground(Color.red);

            Dimension sizeTongGia_Order = new Dimension(230, 30);
            lblTongGia_Order.setPreferredSize(sizeTongGia_Order);
            lblTongGia_Order.setMaximumSize(sizeTongGia_Order);
            lblTongGia_Order.setMinimumSize(sizeTongGia_Order);

            pnlSoLuong_GiaDon_GiaTong.add(Box.createVerticalStrut(40));

            pnlSoLuong_GiaDon_GiaTong.add(lblSoLuong_Order);
//                pnlSoLuong_GiaDon_GiaTong.add(Box.createHorizontalStrut(120));
            pnlSoLuong_GiaDon_GiaTong.add(lblGiaDon_Order);
//        pnlSoLuong_GiaDon_GiaTong.add(Box.createHorizontalStrut(120));
            pnlSoLuong_GiaDon_GiaTong.add(lblTongGia_Order);

            pnlText_Information.add(pnlTenSP_Loai_infor);
            pnlText_Information.add(pnlSoLuong_GiaDon_GiaTong);

            pnlInformation_Product.add(pnlImg_Information);
            pnlInformation_Product.add(pnlText_Information);

            pnlInformation_product_ALL.add(Box.createVerticalStrut(20));
            pnlInformation_product_ALL.add(pnlInformation_Product);
        }
        lblQty.setText("SL :" + countQty);

    }

    public void renderDETAIL_ORDER() {
        OrderDAO dao = new OrderDAO();
        if (user != null) {
            List<Order> listOrder = dao.selectByUserId(Integer.parseInt(user.getId()));
            pnlDetail_Order_All.setLayout(new BoxLayout(pnlDetail_Order_All, BoxLayout.Y_AXIS));

            for (Order o : listOrder) {

//            lblEmail_Profile.setText(o.get);
                pnlOrder = new JPanel();
                pnlOrder.setLayout(new BoxLayout(pnlOrder, BoxLayout.X_AXIS));

                pnlOrder.setBackground(Color.white);
                Dimension size = new Dimension(900, 90);
                pnlOrder.setPreferredSize(size);
                pnlOrder.setMaximumSize(size);
                pnlOrder.setMinimumSize(size);
//             pnlOrder.setBorder(new MatteBorder(1, 1, 0, 1, Color.WHITE));
                pnlOrder.setBorder(new MatteBorder(0, 0, 1, 0, new Color(240, 240, 240)));

                pnlMaHoaDon = new JPanel(new FlowLayout(FlowLayout.LEFT));
                Dimension sizeMaHoaDon = new Dimension(270, 25);
                pnlMaHoaDon.setPreferredSize(sizeMaHoaDon);
                pnlMaHoaDon.setMaximumSize(sizeMaHoaDon);
                pnlMaHoaDon.setMinimumSize(sizeMaHoaDon);
                pnlTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
                Dimension sizeTongTien = new Dimension(280, 25);
                pnlTongTien.setPreferredSize(sizeTongTien);
                pnlTongTien.setMaximumSize(sizeTongTien);
                pnlTongTien.setMinimumSize(sizeTongTien);
                pnlNgayMuaHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
                Dimension sizeNgayMuaHang = new Dimension(230, 25);
                pnlNgayMuaHang.setPreferredSize(sizeNgayMuaHang);
                pnlNgayMuaHang.setMaximumSize(sizeNgayMuaHang);
                pnlNgayMuaHang.setMinimumSize(sizeNgayMuaHang);
                pnlNgayMuaHang.setBackground(Color.white);
                pnlChiTiet = new JPanel(new FlowLayout(FlowLayout.LEFT));
                pnlMaHoaDon.setBackground(Color.white);
                pnlTongTien.setBackground(Color.white);
                pnlChiTiet.setBackground(Color.white);
                Dimension sizeChiTiet = new Dimension(133, 30);
                pnlChiTiet.setPreferredSize(sizeChiTiet);
                pnlChiTiet.setMaximumSize(sizeChiTiet);
                pnlChiTiet.setMinimumSize(sizeChiTiet);

                lblMaHoaDon = new JLabel(o.getId() + "");
                lblTongTien = new JLabel(o.getTotal_amount() + " " + " VNĐ");
                lblNgayMuaHang = new JLabel(o.getCreate_at() + "");
                JButton btnChiTiet = new JButton("Chi Tiet");
                lblChiTiet = new JLabel("Chi Tiet");
                btnChiTiet.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        pnlTabAll_profile.removeAll();
                        pnlTabAll_profile.add(pnlDetail_Order);
                        pnlTabAll_profile.repaint();
                        pnlTabAll_profile.revalidate();
                        id_order = o.getId();
                        id_user_order = o.getUser_id();
                        render_Information_Product_Order();
                    }
                });
                lblMaHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 13));
                lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 13));
                lblNgayMuaHang.setFont(new Font("Segoe UI", Font.BOLD, 13));
                btnChiTiet.setFont(new Font("Segoe UI", Font.BOLD, 13));
                btnChiTiet.setForeground(new Color(25, 135, 84));

                pnlMaHoaDon.add(Box.createHorizontalStrut(40));
                pnlMaHoaDon.add(lblMaHoaDon);

                pnlTongTien.add(lblTongTien);
                pnlNgayMuaHang.add(lblNgayMuaHang);
                pnlChiTiet.add(btnChiTiet);
                pnlOrder.add(Box.createVerticalStrut(10));
                pnlOrder.add(pnlMaHoaDon);
                pnlOrder.add(pnlTongTien);
                pnlOrder.add(pnlNgayMuaHang);
                pnlOrder.add(pnlChiTiet);
                pnlDetail_Order_All.add(Box.createVerticalStrut(10));
                pnlDetail_Order_All.add(pnlOrder);
            }

        }
    }

    public void hoverBtnProfile() {
        pnlThongTin.setBackground(new Color(25, 135, 84));
        JLabel[] lblRrofiles = {lblThongTin, lblChinhSuaDiaChi, lblLichSuMuaHANG, lblDoiMatKhau};
        JPanel[] pnlProfiles = {pnlThongTin, pnlChinhSuaDiaChi, pnlLichSuMuaHANG, pnlDoiMatKhau};
        for (int i = 0; i < lblRrofiles.length; i++) {
            final int index = i;
            lblRrofiles[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Reset background color for all panels
                    for (JPanel panel : pnlProfiles) {

                        panel.setBackground(Color.white);
                    }
                    for (JLabel label : lblRrofiles) {
                        label.setForeground(new Color(171, 173, 173));
                    }

                    lblRrofiles[index].setForeground(new Color(25, 135, 84));
                    pnlProfiles[index].setBackground(new Color(25, 135, 84));
                }
            });
        }

    }

    public void renderComment(int pr_id) {
        ReviewDAO daoR = new ReviewDAO();
        List<Map<String, Object>> listComment = daoR.GetProductReviews(pr_id);
        comment_User = new ArrayList<>();
        comment_User.add(new comment("Thanh Manh", "01/02/2023", "Ngon tuyệt vời, màu đẹp", 5));
        comment_User.add(new comment("Thanh Manh", "01/02/2023", "Ngon tuyệt vời, màu đẹp", 5));
        comment_User.add(new comment("Minh Tu", "15/03/2023", "Rất ngon, dịch vụ tốt", 4));
        comment_User.add(new comment("Ha Linh", "20/04/2023", "Không hài lòng, màu xấu", 2));
        comment_User.add(new comment("Bao Anh", "25/05/2023", "Ngon tuyệt, sẽ quay lại", 5));

        pnlAllComment.setLayout(new BoxLayout(pnlAllComment, BoxLayout.Y_AXIS));

        for (Map<String, Object> c : listComment) {
            String reviewer_name = (String) c.get("reviewer_name");
            String review_date = XDate.toString((Date) c.get("review_date"), "dd/MM/yyyy");
            int rating = (int) c.get("rating");
            String comment = (String) c.get("comment");

            pnlBoxComment = new JPanel();
            pnlBoxComment.setLayout(new BoxLayout(pnlBoxComment, BoxLayout.X_AXIS));
            pnlBoxComment.setBackground(Color.WHITE);
            pnlBoxComment.setBorder(new MatteBorder(0, 0, 2, 0, new Color(238, 238, 238)));
            pnlBoxComment.setPreferredSize(new Dimension(1200, 150));
            pnlBoxComment.setMaximumSize(new Dimension(1200, 150));
            pnlBoxComment.setMinimumSize(new Dimension(1200, 150));

            pnlImg_Comment = new JPanel();
            pnlImg_Comment.setLayout(new BoxLayout(pnlImg_Comment, BoxLayout.X_AXIS));
            pnlImg_Comment.setBackground(Color.WHITE);
            pnlImg_Comment.setPreferredSize(new Dimension(80, 200));
            pnlImg_Comment.setMaximumSize(new Dimension(80, 200));
            pnlImg_Comment.setMinimumSize(new Dimension(80, 200));

            pnlAvata = new JPanel();
            pnlAvata.setBackground(Color.WHITE);
            lblAvata_Comment = new JLabel();
            ImageIcon icon = new ImageIcon("D:\\Luyen_Cong\\Du_An_1\\QuanLyVaCungCapTraiCay\\src\\icon\\hinhTron.png");
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            lblAvata_Comment.setIcon(icon);

            pnlAvata.add(lblAvata_Comment);
            pnlImg_Comment.add(pnlAvata);

            pnlText_Comment = new JPanel();
            pnlText_Comment.setBackground(Color.WHITE);

//        pnlText_Comment.setPreferredSize(new Dimension(1400, 200));
            pnlText_Comment.setLayout(new BoxLayout(pnlText_Comment, BoxLayout.Y_AXIS));
            pnlTen_Ngay = new JPanel(new FlowLayout(FlowLayout.LEFT));

            pnlTen_Ngay.setBackground(Color.WHITE);
            lblTenUser = new JLabel(reviewer_name); //NAME USER COMMMENT HEREEEEEEEEE
            lblTenUser.setFont(new Font("Segoe UI", Font.BOLD, 15));
            ImageIcon myIcon = new ImageIcon("D:\\Luyen_Cong\\Du_An_1\\QuanLyVaCungCapTraiCay\\src\\icon\\oclock.png");
            JLabel lbliconOclock = new JLabel(myIcon);
            lbliconOclock.setIcon(myIcon);
            JLabel lblDate_user = new JLabel(review_date);//DATE USER COMMMENT HEREEEEEEEEE
            lblDate_user.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lblThoiGianMua = new JLabel("lblThoiGianMua");
            lblCommentUser = new JLabel("lblCommentUser");
            pnlTen_Ngay.add(lblTenUser);
            pnlTen_Ngay.add(lbliconOclock);
            pnlTen_Ngay.add(lblDate_user);

            pnlCheckMuaHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlCheckMuaHang.setBackground(Color.WHITE);
            lblIconCheck = new JLabel();
            ImageIcon iconCheck = new ImageIcon("D:\\Luyen_Cong\\Du_An_1\\QuanLyVaCungCapTraiCay\\src\\icon\\check.png");
            lblIconCheck.setIcon(iconCheck);
            lblTextCheck = new JLabel("Đã mua hàng");
            lblTextCheck.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblTextCheck.setForeground(new Color(140, 212, 31));
            pnlCheckMuaHang.add(lblIconCheck);
            pnlCheckMuaHang.add(lblTextCheck);

            pnlRate_star = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlRate_star.setBackground(Color.WHITE);
            lblTextRate = new JLabel("Đã đánh giá : ");
            lblTextRate.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblNumberRate = new JLabel(String.valueOf(rating));//DATING USER COMMMENT HEREEEEEEEEE
            lblNumberRate.setFont(new Font("Segoe UI", Font.BOLD, 15));
            lblIconStar = new JLabel();
            ImageIcon iconStar = new ImageIcon("D:\\Luyen_Cong\\Du_An_1\\QuanLyVaCungCapTraiCay\\src\\icon\\starIcon.png");
            lblIconStar.setIcon(iconStar);
            pnlRate_star.add(lblTextRate);
            pnlRate_star.add(lblNumberRate);
            pnlRate_star.add(lblIconStar);

            pnlComment_User = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlComment_User.setBackground(Color.WHITE);
            lblText_User_Comment = new JLabel(comment);//TEXT  USER COMMMENT HEREEEEEEEEE
            lblText_User_Comment.setFont(new Font("Segoe UI", Font.LAYOUT_NO_LIMIT_CONTEXT, 16));
            pnlComment_User.add(lblText_User_Comment);

            pnlText_Comment.add(pnlTen_Ngay);
            pnlText_Comment.add(pnlCheckMuaHang);
            pnlText_Comment.add(pnlRate_star);
            pnlText_Comment.add(pnlComment_User);

            pnlBoxComment.add(pnlImg_Comment);
//        pnlBoxComment.add(Box.createHorizontalStrut(-10));
            pnlBoxComment.add(pnlText_Comment);

            pnlAllComment.add(pnlBoxComment);
            pnlAllComment.add(Box.createVerticalStrut(20));

        }

    }

    public void renderDetail_PRODUCT(int pr_id) {
        GalleryDAO daoGa = new GalleryDAO();

        Product_ItemDAO dao = new Product_ItemDAO();

        Map<String, Object> list = dao.GetProductDetails(pr_id);
        int stoc = (int) list.get("qty_in_stock");
        double price = (double) list.get("price");
        String product_name = (String) list.get("product_name");
        String category_name = (String) list.get("category_name");
        String description = (String) list.get("description");

        txtNameProduct.setText(product_name);
        lblPriceProduct.setText(price + " VNĐ");
        lblQtyProduct.setText("Còn " + stoc + " Sản phẩm");
        lblcategory_name.setText(category_name);
        lblDescriptionPr.setText(description);

        pnlItems.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlItems.setBackground(Color.white);

        if (pnlImageItems != null) {
            pnlImageItems.removeAll();
        } else {
            pnlImageItems = new JPanel(new GridLayout(0, 2, 8, 8));
            pnlImageItems.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 2));
        }

        List<String> listGa = daoGa.getThumnailByProductId(pr_id);
        for (String imgPath : listGa) {

            ImageIcon icon = new ImageIcon("D:\\cao_dang_fpt\\KY4\\ProjectBanTraiCay\\DuAn_1_PROJECT_FRUIT\\DuAn_1_PROJECT_FRUIT\\QuanLyVaCungCapTraiCay\\src\\products\\" + imgPath);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            JLabel lblItemImg = new JLabel(icon);

            // Thêm event listener cho JLabel thumbnail
            lblItemImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    ImageIcon mainIcon = new ImageIcon("D:\\cao_dang_fpt\\KY4\\ProjectBanTraiCay\\DuAn_1_PROJECT_FRUIT\\DuAn_1_PROJECT_FRUIT\\QuanLyVaCungCapTraiCay\\src\\products\\" + imgPath);
                    Image mainImg = mainIcon.getImage();
                    Image newMainImg = mainImg.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                    mainIcon = new ImageIcon(newMainImg);
                    lblImgMain.setIcon(mainIcon);
                }
            });

            pnlImageItems.add(lblItemImg);

        }

        pnlItems.add(pnlImageItems);

// Hiển thị hình ảnh đầu tiên ở hình chính
        ImageIcon iconMain = new ImageIcon("D:\\cao_dang_fpt\\KY4\\ProjectBanTraiCay\\DuAn_1_PROJECT_FRUIT\\DuAn_1_PROJECT_FRUIT\\QuanLyVaCungCapTraiCay\\src\\products\\" + listGa.get(0));
        System.out.println(iconMain);
        Image img = iconMain.getImage();
        Image newImg = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        iconMain = new ImageIcon(newImg);
        lblImgMain.setIcon(iconMain);
    }

    public void fillUPM(int user_id) {
        DefaultComboBoxModel modelUPM = (DefaultComboBoxModel) JcbPhuongThucThanhToan.getModel();
        modelUPM.removeAllElements();
        User_Payment_MethodDAO upmDao = new User_Payment_MethodDAO();
        List<User_Payment_Method> listUPM = upmDao.selectByUserId(user_id);
        for (User_Payment_Method upm : listUPM) {
            modelUPM.addElement(upm);
        }
    }

    public void ThanhToan() {
        int user_id = Integer.parseInt(user.getId());
        User_Payment_Method upm = (User_Payment_Method) JcbPhuongThucThanhToan.getSelectedItem();
        int upm_id = upm.getId();
        Address a = (Address) JcbDiaChiGiaoHang.getSelectedItem();
        int a_id = Integer.parseInt(a.getId());

        OrderDAO orderDAO = new OrderDAO();
        try {
            orderDAO.insert(user_id, upm_id, a_id);
            JOptionPane.showMessageDialog(null, "Thanh toán thành công");
        } catch (Exception e) {
            e.getMessage();

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBtnNav = new javax.swing.JPanel();
        btnDangNhap = new javax.swing.JButton();
        btnDangKy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblIconShop = new javax.swing.JLabel();
        lblIconUser = new javax.swing.JLabel();
        lblIconSearch = new javax.swing.JLabel();
        lblIconHea = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblQtyCart = new javax.swing.JLabel();
        pnlTabAll = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        pnlChiTietThanhToan = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jPanel81 = new javax.swing.JPanel();
        jLabel215 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jPanel82 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jLabel219 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel225 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        pnlScroll_SanPhamThanhToanChioTiet = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        pnlSanPhamThanhToan_ChiTiet = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel228 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        JcbPhuongThucThanhToan = new javax.swing.JComboBox<>();
        jLabel232 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        JcbDiaChiGiaoHang = new javax.swing.JComboBox<>();
        btnSelectDiaChiGiaoHang = new javax.swing.JButton();
        jLabel236 = new javax.swing.JLabel();
        bntThanhToanDonHang = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel230 = new javax.swing.JLabel();
        pnlProfile = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        pnlProfileSetSize = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        lblEmail_Profile = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        lblThongTin = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        pnlThongTin = new javax.swing.JPanel();
        lblChinhSuaDiaChi = new javax.swing.JLabel();
        pnlChinhSuaDiaChi = new javax.swing.JPanel();
        pnlLichSuMuaHANG = new javax.swing.JPanel();
        lblLichSuMuaHANG = new javax.swing.JLabel();
        pnlDoiMatKhau = new javax.swing.JPanel();
        lblDoiMatKhau = new javax.swing.JLabel();
        pnlTabAll_profile = new javax.swing.JPanel();
        pnlProfile_user = new javax.swing.JPanel();
        jLabel176 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jButton42 = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        pnlEditAddress = new javax.swing.JPanel();
        jLabel211 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel212 = new javax.swing.JLabel();
        jButton46 = new javax.swing.JButton();
        jLabel213 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtEditWard = new javax.swing.JTextField();
        txtEditCity = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel214 = new javax.swing.JLabel();
        txtEditStreet = new javax.swing.JTextField();
        pnlAddress_Profile = new javax.swing.JPanel();
        jLabel207 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        pnlAddress_User = new javax.swing.JPanel();
        pnlEditProfile = new javax.swing.JPanel();
        jLabel177 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jButton43 = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        pnlFogetPass = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton31 = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        pnlHistoryOrder = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        pnlDetail_Order_All = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        pnlDetail_Order = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        lblSdtUser = new javax.swing.JLabel();
        lblAddress_User = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        lblNameUser = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        pnlInformation_product_ALL = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        lblpayment_type_name = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        lbltotal_amount = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jLabel141 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jLabel126 = new javax.swing.JLabel();
        jPanel62 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        pnlGioHang = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel39 = new javax.swing.JPanel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jPanel78 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        pnlitems = new javax.swing.JPanel();
        jPanel79 = new javax.swing.JPanel();
        jPanel80 = new javax.swing.JPanel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        lblTotal_Cart = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        lblCartQty = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        pnlWistLisyReal = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel74 = new javax.swing.JPanel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jPanel75 = new javax.swing.JPanel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jPanel76 = new javax.swing.JPanel();
        jLabel187 = new javax.swing.JLabel();
        jPanel77 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        pnlWishLists = new javax.swing.JPanel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        detail_product = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnlSetSizeDetail_Product = new javax.swing.JPanel();
        pnlItems = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblQtyProduct = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPriceProduct = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblcategory_name = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        txtNameProduct = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        lblDescriptionPr = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        pnlBoGoc = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        lblRatingOne = new javax.swing.JLabel();
        lblRatingFive = new javax.swing.JLabel();
        lblRatingFour = new javax.swing.JLabel();
        lblRatingThree = new javax.swing.JLabel();
        lblRatingTwo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel85 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        pnlAllComment = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jPanel65 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jPanel66 = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jPanel67 = new javax.swing.JPanel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jPanel68 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jPanel69 = new javax.swing.JPanel();
        jLabel166 = new javax.swing.JLabel();
        jPanel70 = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        jLabel174 = new javax.swing.JLabel();
        jPanel73 = new javax.swing.JPanel();
        jLabel175 = new javax.swing.JLabel();
        jPanel71 = new javax.swing.JPanel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblImgMain = new javax.swing.JLabel();
        pnlAddress = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel40 = new javax.swing.JPanel();
        products = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        pnlCatalory = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnl_Products = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBtnNav.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtnNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlBtnNavMouseExited(evt);
            }
        });
        pnlBtnNav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDangNhap.setText("ĐĂNG NHẬP");
        btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseClicked(evt);
            }
        });
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        pnlBtnNav.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 49));

        btnDangKy.setText("ĐĂNG KÍ");
        btnDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangKyMouseClicked(evt);
            }
        });
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });
        pnlBtnNav.add(btnDangKy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 49));

        getContentPane().add(pnlBtnNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 60, 200, 100));

        jPanel1.setBackground(new java.awt.Color(248, 249, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(61, 138, 106));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(61, 138, 106));
        jLabel2.setText("BRAND");
        jLabel2.setAutoscrolls(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 90, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("TRANG CHỦ");
        jLabel4.setAutoscrolls(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 130, 30));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("LIÊN HỆ");
        jLabel5.setAutoscrolls(true);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 90, 30));

        lblIconShop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cart.png"))); // NOI18N
        lblIconShop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconShopMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconShopMouseEntered(evt);
            }
        });
        jPanel1.add(lblIconShop, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 30, -1, 30));

        lblIconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N
        lblIconUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconUserMouseEntered(evt);
            }
        });
        jPanel1.add(lblIconUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 30, -1, 30));

        lblIconSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search .png"))); // NOI18N
        lblIconSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconSearchMouseEntered(evt);
            }
        });
        jPanel1.add(lblIconSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 30, -1, 30));

        lblIconHea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hea.png"))); // NOI18N
        lblIconHea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconHeaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconHeaMouseEntered(evt);
            }
        });
        jPanel1.add(lblIconHea, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 30, -1, 30));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("HỒ SƠ");
        jLabel10.setAutoscrolls(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 80, 30));

        jLabel108.setBackground(new java.awt.Color(0, 0, 0));
        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("SẢN PHẢM");
        jLabel108.setAutoscrolls(true);
        jLabel108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel108MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 130, 30));

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(lblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 270, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, -1, -1));

        lblQtyCart.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQtyCart.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(lblQtyCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 10, 60, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 90));

        pnlTabAll.setMinimumSize(new java.awt.Dimension(1540, 2850));
        pnlTabAll.setLayout(new java.awt.CardLayout());

        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1524, 1830));
        jPanel2.setPreferredSize(new java.awt.Dimension(1540, 2545));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(214, 232, 181));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("TRÁI CÂY SẠCH");
        jLabel12.setAutoscrolls(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("    MUA NGAY");
        jLabel15.setToolTipText("");
        jLabel15.setAutoscrolls(true);
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 120, 30));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 620, 330, 130));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banner/Green and White Organic Fruits and Vegetables YouTube Thumbnail.png"))); // NOI18N
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1550, 780));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/apple2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 940, 310, 290));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/avocado1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGap(20, 20, 20)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 286, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 940, 310, -1));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/avocado2.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, Short.MAX_VALUE)
                .addGap(68, 68, 68))
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 940, 310, 290));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(62, 137, 84));
        jLabel17.setText("TỰ NHIÊN");
        jLabel17.setToolTipText("");
        jLabel17.setAutoscrolls(true);
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 120, 30));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(62, 137, 84));
        jLabel19.setText("GIÁ RẺ");
        jLabel19.setToolTipText("");
        jLabel19.setAutoscrolls(true);
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 80, 30));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(62, 137, 84));
        jLabel21.setText("HỮU CƠ");
        jLabel21.setToolTipText("");
        jLabel21.setAutoscrolls(true);
        jPanel9.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 90, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 1250, 1030, -1));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("SẢN PHẨM MỚI");
        jLabel16.setToolTipText("");
        jLabel16.setAutoscrolls(true);
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1400, 300, 70));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("HÀNG MỚI NHẬP");
        jLabel20.setToolTipText("");
        jLabel20.setAutoscrolls(true);
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1460, 110, 30));

        jPanel14.setBackground(new java.awt.Color(248, 249, 250));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/apple3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 75, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1550, 310, -1));

        jButton9.setBackground(new java.awt.Color(220, 53, 69));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton9.setText("SẢN PHẨM KHÁC");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 2070, 220, 41));

        jPanel11.setBackground(new java.awt.Color(248, 249, 250));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/twiterter.png"))); // NOI18N
        jPanel11.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 43, 30));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gg.png"))); // NOI18N
        jPanel11.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 30, 30));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ig.png"))); // NOI18N
        jPanel11.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/link.png"))); // NOI18N
        jPanel11.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 43, 30));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gitH.png"))); // NOI18N
        jPanel11.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 43, 30));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fb.png"))); // NOI18N
        jPanel11.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, 30));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2260, 1540, 120));

        jPanel22.setBackground(new java.awt.Color(235, 236, 237));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setBackground(new java.awt.Color(255, 255, 255));
        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel44.setToolTipText("");
        jLabel44.setAutoscrolls(true);
        jPanel22.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel2.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2380, 1530, 90));

        jPanel23.setBackground(new java.awt.Color(248, 249, 250));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/banana3.png"))); // NOI18N
        jLabel23.setText("jLabel23");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1550, 310, -1));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel46.setBackground(new java.awt.Color(255, 255, 255));
        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("VNĐ/kg");
        jLabel46.setToolTipText("");
        jLabel46.setAutoscrolls(true);

        jLabel47.setBackground(new java.awt.Color(255, 255, 255));
        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(62, 137, 84));
        jLabel47.setText("TÁO ĐỎ");
        jLabel47.setToolTipText("");
        jLabel47.setAutoscrolls(true);

        jLabel48.setBackground(new java.awt.Color(255, 255, 255));
        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("10,000 ");
        jLabel48.setToolTipText("");
        jLabel48.setAutoscrolls(true);

        jButton10.setBackground(new java.awt.Color(220, 53, 69));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(25, 135, 84));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(102, 102, 102))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel47)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel48))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1840, 310, -1));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel49.setBackground(new java.awt.Color(255, 255, 255));
        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("VNĐ/kg");
        jLabel49.setToolTipText("");
        jLabel49.setAutoscrolls(true);

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(62, 137, 84));
        jLabel50.setText("TÁO ĐỎ");
        jLabel50.setToolTipText("");
        jLabel50.setAutoscrolls(true);

        jLabel51.setBackground(new java.awt.Color(255, 255, 255));
        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("10,000 ");
        jLabel51.setToolTipText("");
        jLabel51.setAutoscrolls(true);

        jButton12.setBackground(new java.awt.Color(220, 53, 69));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(25, 135, 84));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49)
                .addGap(102, 102, 102))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel50)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel51))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1840, 310, 160));

        jPanel27.setBackground(new java.awt.Color(248, 249, 250));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/kiwi1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel24))
        );

        jPanel2.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 1550, 310, -1));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("VNĐ/kg");
        jLabel56.setToolTipText("");
        jLabel56.setAutoscrolls(true);

        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(62, 137, 84));
        jLabel57.setText("TÁO ĐỎ");
        jLabel57.setToolTipText("");
        jLabel57.setAutoscrolls(true);

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("10,000 ");
        jLabel58.setToolTipText("");
        jLabel58.setAutoscrolls(true);

        jButton16.setBackground(new java.awt.Color(220, 53, 69));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(25, 135, 84));
        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton17.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel56)
                .addGap(102, 102, 102))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel57)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel58))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 1840, 310, -1));

        jPanel29.setBackground(new java.awt.Color(248, 249, 250));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/apple1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1550, 310, -1));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel60.setBackground(new java.awt.Color(255, 255, 255));
        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("VNĐ/kg");
        jLabel60.setToolTipText("");
        jLabel60.setAutoscrolls(true);

        jLabel61.setBackground(new java.awt.Color(255, 255, 255));
        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(62, 137, 84));
        jLabel61.setText("TÁO ĐỎ");
        jLabel61.setToolTipText("");
        jLabel61.setAutoscrolls(true);

        jLabel62.setBackground(new java.awt.Color(255, 255, 255));
        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("10,000 ");
        jLabel62.setToolTipText("");
        jLabel62.setAutoscrolls(true);

        jButton18.setBackground(new java.awt.Color(220, 53, 69));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(25, 135, 84));
        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton19.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addGap(102, 102, 102))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel61)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel62))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1840, 310, -1));

        jScrollPane2.setViewportView(jPanel2);

        pnlMain.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 790));

        pnlTabAll.add(pnlMain, "card3");

        pnlChiTietThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        pnlChiTietThanhToan.setPreferredSize(new java.awt.Dimension(1990, 1100));

        jScrollPane15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel81.setBackground(new java.awt.Color(255, 255, 255));
        jPanel81.setForeground(new java.awt.Color(255, 255, 255));
        jPanel81.setMaximumSize(new java.awt.Dimension(2147483647, 1200));
        jPanel81.setMinimumSize(new java.awt.Dimension(310, 1000));
        jPanel81.setOpaque(false);
        jPanel81.setPreferredSize(new java.awt.Dimension(1784, 1200));
        jPanel81.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel215.setText("Trang Chủ /");
        jPanel81.add(jLabel215, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 69, -1, -1));

        jLabel216.setText("Giỏ Hàng /");
        jPanel81.add(jLabel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 69, 59, -1));

        jLabel217.setText("Thanh Toán");
        jPanel81.add(jLabel217, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 69, -1, -1));

        jPanel82.setBackground(new java.awt.Color(255, 255, 255));
        jPanel82.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel82.setAlignmentX(1.0F);
        jPanel82.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel82.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 105, 426, -1));
        jPanel82.add(jLabel219, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 214, 49, -1));

        jTextField8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel82.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 206, 426, -1));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        jPanel82.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 774, -1, -1));

        jLabel225.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel225.setText("ĐƠN HÀNG");
        jPanel82.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 55, -1, -1));

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jPanel82.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 670, 10));

        pnlSanPhamThanhToan_ChiTiet.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlSanPhamThanhToan_ChiTietLayout = new javax.swing.GroupLayout(pnlSanPhamThanhToan_ChiTiet);
        pnlSanPhamThanhToan_ChiTiet.setLayout(pnlSanPhamThanhToan_ChiTietLayout);
        pnlSanPhamThanhToan_ChiTietLayout.setHorizontalGroup(
            pnlSanPhamThanhToan_ChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        pnlSanPhamThanhToan_ChiTietLayout.setVerticalGroup(
            pnlSanPhamThanhToan_ChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        jScrollPane16.setViewportView(pnlSanPhamThanhToan_ChiTiet);

        javax.swing.GroupLayout pnlScroll_SanPhamThanhToanChioTietLayout = new javax.swing.GroupLayout(pnlScroll_SanPhamThanhToanChioTiet);
        pnlScroll_SanPhamThanhToanChioTiet.setLayout(pnlScroll_SanPhamThanhToanChioTietLayout);
        pnlScroll_SanPhamThanhToanChioTietLayout.setHorizontalGroup(
            pnlScroll_SanPhamThanhToanChioTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScroll_SanPhamThanhToanChioTietLayout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlScroll_SanPhamThanhToanChioTietLayout.setVerticalGroup(
            pnlScroll_SanPhamThanhToanChioTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScroll_SanPhamThanhToanChioTietLayout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel82.add(pnlScroll_SanPhamThanhToanChioTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 130, 670, -1));

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        jPanel82.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 430, 660, 10));

        jLabel228.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel228.setForeground(new java.awt.Color(0, 0, 0));
        jLabel228.setText("Tổng Cộng");
        jPanel82.add(jLabel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, -1, -1));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotal.setText("20,000 VNĐ");
        jPanel82.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 480, -1, -1));

        JcbPhuongThucThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        JcbPhuongThucThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JcbPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MOMO", "PAYPAL", "CREADIT CARD" }));
        jPanel82.add(JcbPhuongThucThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, -1, -1));

        jLabel232.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel232.setForeground(new java.awt.Color(0, 0, 0));
        jLabel232.setText("PHƯƠNG THỨC THANH TOÁN");
        jPanel82.add(jLabel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 310, 40));

        jLabel233.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel233.setForeground(new java.awt.Color(0, 0, 0));
        jLabel233.setText("HỌ VÀ TÊN");
        jPanel82.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 150, 40));

        jLabel234.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel234.setForeground(new java.awt.Color(0, 0, 0));
        jLabel234.setText("CHỌN ĐỊA CHỈ GIAO HÀNG");
        jPanel82.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 290, 40));

        JcbDiaChiGiaoHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        JcbDiaChiGiaoHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JcbDiaChiGiaoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JcbDiaChiGiaoHangMouseClicked(evt);
            }
        });
        JcbDiaChiGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcbDiaChiGiaoHangActionPerformed(evt);
            }
        });
        jPanel82.add(JcbDiaChiGiaoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 430, -1));

        btnSelectDiaChiGiaoHang.setBackground(new java.awt.Color(25, 135, 88));
        btnSelectDiaChiGiaoHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSelectDiaChiGiaoHang.setText("ĐỊA CHỈ MỚI");
        btnSelectDiaChiGiaoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSelectDiaChiGiaoHangMouseClicked(evt);
            }
        });
        btnSelectDiaChiGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectDiaChiGiaoHangActionPerformed(evt);
            }
        });
        jPanel82.add(btnSelectDiaChiGiaoHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 200, -1));

        jLabel236.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel236.setForeground(new java.awt.Color(0, 0, 0));
        jLabel236.setText("SỐ ĐIỆN THOẠI");
        jPanel82.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 200, 40));

        bntThanhToanDonHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntThanhToanDonHang.setText("THANH TOÁN");
        bntThanhToanDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bntThanhToanDonHangMouseClicked(evt);
            }
        });
        jPanel82.add(bntThanhToanDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 180, 50));

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jPanel82.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 540, 660, 20));

        jPanel81.add(jPanel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 1340, 792));

        jLabel230.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel230.setText("THANH TOÁN");
        jPanel81.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 103, 171, -1));

        jScrollPane15.setViewportView(jPanel81);

        javax.swing.GroupLayout pnlChiTietThanhToanLayout = new javax.swing.GroupLayout(pnlChiTietThanhToan);
        pnlChiTietThanhToan.setLayout(pnlChiTietThanhToanLayout);
        pnlChiTietThanhToanLayout.setHorizontalGroup(
            pnlChiTietThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietThanhToanLayout.createSequentialGroup()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 450, Short.MAX_VALUE))
        );
        pnlChiTietThanhToanLayout.setVerticalGroup(
            pnlChiTietThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietThanhToanLayout.createSequentialGroup()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 141, Short.MAX_VALUE))
        );

        pnlTabAll.add(pnlChiTietThanhToan, "card8");

        pnlProfile.setBackground(new java.awt.Color(153, 255, 153));
        pnlProfile.setPreferredSize(new java.awt.Dimension(4000, 810));
        pnlProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setToolTipText("");
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlProfileSetSize.setBackground(new java.awt.Color(255, 255, 255));
        pnlProfileSetSize.setMaximumSize(new java.awt.Dimension(2000000, 1100));
        pnlProfileSetSize.setMinimumSize(new java.awt.Dimension(2000, 1275));
        pnlProfileSetSize.setPreferredSize(new java.awt.Dimension(3000, 1100));
        pnlProfileSetSize.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel104.setBackground(new java.awt.Color(0, 128, 0));
        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(0, 128, 0));
        jLabel104.setText("Hồ sơ");
        pnlProfileSetSize.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 104, 30));

        jLabel105.setBackground(new java.awt.Color(0, 128, 0));
        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(0, 128, 0));
        jLabel105.setText("Trang Chủ /");
        pnlProfileSetSize.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, 30));

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEmail_Profile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblEmail_Profile.setForeground(new java.awt.Color(25, 135, 84));
        lblEmail_Profile.setText("manhntps37466@fpt.edu.vn");
        jPanel43.add(lblEmail_Profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 40));

        jPanel41.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 230, 40));

        jLabel107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatarIcon.png"))); // NOI18N
        jPanel41.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThongTin.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblThongTin.setForeground(new java.awt.Color(171, 173, 173));
        lblThongTin.setText("      Thông Tin");
        lblThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongTinMouseClicked(evt);
            }
        });
        jPanel42.add(lblThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 60));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel42.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 0, 60));

        pnlThongTin.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel42.add(pnlThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, 60));

        lblChinhSuaDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblChinhSuaDiaChi.setForeground(new java.awt.Color(171, 173, 173));
        lblChinhSuaDiaChi.setText("      Chỉnh Sửa Địa Chỉ");
        lblChinhSuaDiaChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChinhSuaDiaChiMouseClicked(evt);
            }
        });
        jPanel42.add(lblChinhSuaDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, 60));

        pnlChinhSuaDiaChi.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlChinhSuaDiaChiLayout = new javax.swing.GroupLayout(pnlChinhSuaDiaChi);
        pnlChinhSuaDiaChi.setLayout(pnlChinhSuaDiaChiLayout);
        pnlChinhSuaDiaChiLayout.setHorizontalGroup(
            pnlChinhSuaDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlChinhSuaDiaChiLayout.setVerticalGroup(
            pnlChinhSuaDiaChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel42.add(pnlChinhSuaDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 10, 60));

        pnlLichSuMuaHANG.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlLichSuMuaHANGLayout = new javax.swing.GroupLayout(pnlLichSuMuaHANG);
        pnlLichSuMuaHANG.setLayout(pnlLichSuMuaHANGLayout);
        pnlLichSuMuaHANGLayout.setHorizontalGroup(
            pnlLichSuMuaHANGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlLichSuMuaHANGLayout.setVerticalGroup(
            pnlLichSuMuaHANGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel42.add(pnlLichSuMuaHANG, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 10, 60));

        lblLichSuMuaHANG.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblLichSuMuaHANG.setForeground(new java.awt.Color(171, 173, 173));
        lblLichSuMuaHANG.setText("      Lịch Sử Mua Hàng");
        lblLichSuMuaHANG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLichSuMuaHANGMouseClicked(evt);
            }
        });
        jPanel42.add(lblLichSuMuaHANG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 240, 60));

        pnlDoiMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        pnlDoiMatKhau.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel42.add(pnlDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 10, 60));

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblDoiMatKhau.setForeground(new java.awt.Color(171, 173, 173));
        lblDoiMatKhau.setText("      Đổi Mật Khẩu");
        lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseClicked(evt);
            }
        });
        jPanel42.add(lblDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 240, 60));

        jPanel41.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 260, 350));

        pnlProfileSetSize.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 280, 630));

        pnlTabAll_profile.setLayout(new java.awt.CardLayout());

        pnlProfile_user.setBackground(new java.awt.Color(255, 255, 255));
        pnlProfile_user.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        pnlProfile_user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel176.setBackground(new java.awt.Color(0, 0, 0));
        jLabel176.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel176.setForeground(new java.awt.Color(0, 0, 0));
        jLabel176.setText(" THÔNG TIN CỦA BẠN");
        jLabel176.setAutoscrolls(true);
        pnlProfile_user.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 190, 30));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("ĐỊA CHỈ");
        jPanel33.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 190, 40));

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("SỐ ĐIỆN THOẠI");
        jPanel33.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 190, 40));

        jButton42.setBackground(new java.awt.Color(21, 115, 71));
        jButton42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton42.setForeground(new java.awt.Color(255, 255, 255));
        jButton42.setText("CHỈNH SỬA HỒ SƠ");
        jButton42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton42MouseClicked(evt);
            }
        });
        jPanel33.add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 580, 40));

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("HỌ VÀ TÊN");
        jPanel33.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 150, 40));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel33.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 740, 10));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel33.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 740, 10));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel33.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 740, 10));

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(21, 115, 71));
        jLabel93.setText("\tQTSC 9 Building, Đ. Tô Ký, Tân Chánh Hiệp, Quận 12, Hồ Chí Minh");
        jPanel33.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 580, -1));

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Kiểm tra thông tin của bạn. Nếu có điều gì bất thường hãy nhanh chóng chỉnh sửa hồ sơ ngay. ");
        jPanel33.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 780, -1));

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(21, 115, 71));
        jLabel95.setText("manhntps");
        jPanel33.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 140, -1));

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(21, 115, 71));
        jLabel97.setText("0957397364");
        jPanel33.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 140, -1));

        pnlProfile_user.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 870, 550));

        pnlTabAll_profile.add(pnlProfile_user, "card5");

        pnlEditAddress.setBackground(new java.awt.Color(255, 255, 255));
        pnlEditAddress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(239, 239, 239), 3, true));
        pnlEditAddress.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel211.setBackground(new java.awt.Color(0, 0, 0));
        jLabel211.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel211.setForeground(new java.awt.Color(0, 0, 0));
        jLabel211.setText("SỬA ĐỊA CHỈ CỦA BẠN");
        jLabel211.setAutoscrolls(true);
        pnlEditAddress.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 260, 30));

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel212.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel212.setForeground(new java.awt.Color(0, 0, 0));
        jLabel212.setText("PHƯỜNG - XÃ - QUẬN");
        jPanel37.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 240, 30));

        jButton46.setBackground(new java.awt.Color(21, 115, 71));
        jButton46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton46.setForeground(new java.awt.Color(255, 255, 255));
        jButton46.setText("CHỈNH SỬA HỒ SƠ");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        jPanel37.add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 580, 40));

        jLabel213.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel213.setForeground(new java.awt.Color(0, 0, 0));
        jLabel213.setText("TỈNH - THÀNH PHỐ");
        jPanel37.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 230, 30));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel37.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 740, 10));

        txtEditWard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEditWard.setForeground(new java.awt.Color(21, 115, 71));
        txtEditWard.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel37.add(txtEditWard, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 740, 40));

        txtEditCity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEditCity.setForeground(new java.awt.Color(21, 115, 71));
        txtEditCity.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtEditCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEditCityActionPerformed(evt);
            }
        });
        jPanel37.add(txtEditCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 740, 40));

        jCheckBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(21, 115, 71));
        jCheckBox3.setText("Bạn có chắc với thông tin ?");
        jPanel37.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        jLabel214.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel214.setForeground(new java.awt.Color(0, 0, 0));
        jLabel214.setText("SỐ NHÀ - TÊN ĐƯỜNG");
        jPanel37.add(jLabel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 230, 30));

        txtEditStreet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEditStreet.setForeground(new java.awt.Color(21, 115, 71));
        txtEditStreet.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel37.add(txtEditStreet, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 740, 40));

        pnlEditAddress.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 870, 580));

        pnlTabAll_profile.add(pnlEditAddress, "card8");

        pnlAddress_Profile.setBackground(new java.awt.Color(255, 255, 255));
        pnlAddress_Profile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(231, 231, 233), 3, true));
        pnlAddress_Profile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel207.setBackground(new java.awt.Color(0, 0, 0));
        jLabel207.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel207.setForeground(new java.awt.Color(0, 0, 0));
        jLabel207.setText("CHỈNH SỬA ĐỊA CHỈ");
        jLabel207.setAutoscrolls(true);
        pnlAddress_Profile.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 160, 30));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jLabel208.setBackground(new java.awt.Color(0, 0, 0));
        jLabel208.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel208.setForeground(new java.awt.Color(0, 0, 0));
        jLabel208.setText("ĐỊA CHỈ");
        jLabel208.setAutoscrolls(true);

        jLabel209.setBackground(new java.awt.Color(0, 0, 0));
        jLabel209.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel209.setForeground(new java.awt.Color(0, 0, 0));
        jLabel209.setText("STT");
        jLabel209.setAutoscrolls(true);

        jLabel210.setBackground(new java.awt.Color(0, 0, 0));
        jLabel210.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel210.setForeground(new java.awt.Color(0, 0, 0));
        jLabel210.setText("CHI TIẾT");
        jLabel210.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel208, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 475, Short.MAX_VALUE)
                .addComponent(jLabel210, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel209, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(794, Short.MAX_VALUE)))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel208, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel210, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                    .addContainerGap(8, Short.MAX_VALUE)
                    .addComponent(jLabel209, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)))
        );

        jPanel8.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 920, 40));

        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAddress_User.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlAddress_UserLayout = new javax.swing.GroupLayout(pnlAddress_User);
        pnlAddress_User.setLayout(pnlAddress_UserLayout);
        pnlAddress_UserLayout.setHorizontalGroup(
            pnlAddress_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 914, Short.MAX_VALUE)
        );
        pnlAddress_UserLayout.setVerticalGroup(
            pnlAddress_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );

        jScrollPane14.setViewportView(pnlAddress_User);

        jPanel36.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 590));

        jPanel8.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 920, 590));

        pnlAddress_Profile.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 950, 760));

        pnlTabAll_profile.add(pnlAddress_Profile, "card7");

        pnlEditProfile.setBackground(new java.awt.Color(255, 255, 255));
        pnlEditProfile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        pnlEditProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel177.setBackground(new java.awt.Color(0, 0, 0));
        jLabel177.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel177.setForeground(new java.awt.Color(0, 0, 0));
        jLabel177.setText("Chỉnh Sửa Thông Tin Của Bạn");
        jLabel177.setAutoscrolls(true);
        pnlEditProfile.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 250, 30));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("SỐ ĐIỆN THOẠI");
        jPanel34.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 190, 40));

        jButton43.setBackground(new java.awt.Color(21, 115, 71));
        jButton43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton43.setForeground(new java.awt.Color(255, 255, 255));
        jButton43.setText("CHỈNH SỬA HỒ SƠ");
        jPanel34.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 580, 40));

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("HỌ VÀ TÊN");
        jPanel34.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 150, 40));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel34.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 740, 10));

        jTextField5.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel34.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 740, 40));

        jTextField6.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel34.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 740, 40));

        jCheckBox2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(21, 115, 71));
        jCheckBox2.setText("Bạn có chắc với thông tin ?");
        jPanel34.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        pnlEditProfile.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 870, 550));

        pnlTabAll_profile.add(pnlEditProfile, "card5");

        pnlFogetPass.setBackground(new java.awt.Color(255, 255, 255));
        pnlFogetPass.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        pnlFogetPass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("MẬT KHẨU CŨ");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 190, 40));

        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 740, 40));

        jTextField3.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 740, 40));

        jTextField4.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel7.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 740, 40));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("MẬT KHẨU CŨ");
        jPanel7.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 190, 40));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setText("Hiện mật khẩu");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel7.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        jButton31.setBackground(new java.awt.Color(21, 115, 71));
        jButton31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("XÁC NHẬN");
        jPanel7.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 150, 40));

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("MẬT KHẨU CŨ");
        jPanel7.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 190, 40));

        pnlFogetPass.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 870, 550));

        jLabel178.setBackground(new java.awt.Color(0, 0, 0));
        jLabel178.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel178.setForeground(new java.awt.Color(0, 0, 0));
        jLabel178.setText("Chỉnh Sửa Mật Khẩu Của Bạn");
        jLabel178.setAutoscrolls(true);
        pnlFogetPass.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 310, 30));

        pnlTabAll_profile.add(pnlFogetPass, "card4");

        pnlHistoryOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnlHistoryOrder.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        pnlHistoryOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        jPanel46.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 30, 530));

        jScrollPane8.setBackground(new java.awt.Color(102, 102, 255));
        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlDetail_Order_All.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlDetail_Order_AllLayout = new javax.swing.GroupLayout(pnlDetail_Order_All);
        pnlDetail_Order_All.setLayout(pnlDetail_Order_AllLayout);
        pnlDetail_Order_AllLayout.setHorizontalGroup(
            pnlDetail_Order_AllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 993, Short.MAX_VALUE)
        );
        pnlDetail_Order_AllLayout.setVerticalGroup(
            pnlDetail_Order_AllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        jScrollPane8.setViewportView(pnlDetail_Order_All);

        jPanel46.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 890, 520));

        pnlHistoryOrder.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 910, 540));

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel110.setBackground(new java.awt.Color(0, 0, 0));
        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText(" MÃ HÓA ĐƠN");
        jLabel110.setAutoscrolls(true);
        jPanel48.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 110, 30));

        jLabel111.setBackground(new java.awt.Color(0, 0, 0));
        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("TỔNG TIỀN");
        jLabel111.setAutoscrolls(true);
        jPanel48.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 110, 30));

        jLabel112.setBackground(new java.awt.Color(0, 0, 0));
        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("\tNGÀY MUA HÀNG");
        jLabel112.setAutoscrolls(true);
        jPanel48.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 150, 30));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("\tCHI TIẾT");
        jLabel1.setAutoscrolls(true);
        jPanel48.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 80, 30));

        jPanel47.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 910, 50));

        pnlHistoryOrder.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 960, 670));

        jLabel109.setBackground(new java.awt.Color(0, 0, 0));
        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("LỊCH SỬ MUA HÀNG");
        jLabel109.setAutoscrolls(true);
        pnlHistoryOrder.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 160, 30));

        pnlTabAll_profile.add(pnlHistoryOrder, "card2");

        pnlDetail_Order.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetail_Order.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));
        pnlDetail_Order.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel50.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSdtUser.setBackground(new java.awt.Color(0, 0, 0));
        lblSdtUser.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblSdtUser.setForeground(new java.awt.Color(117, 117, 117));
        lblSdtUser.setText("0116697129");
        lblSdtUser.setAutoscrolls(true);
        jPanel50.add(lblSdtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 190, 20));

        lblAddress_User.setBackground(new java.awt.Color(0, 0, 0));
        lblAddress_User.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblAddress_User.setForeground(new java.awt.Color(117, 117, 117));
        lblAddress_User.setText("QTSC 9 Building, Đ. Tô Ký, Tân Chánh Hiệp, Quận 12, Hồ Chí Minh");
        lblAddress_User.setAutoscrolls(true);
        jPanel50.add(lblAddress_User, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 700, 20));

        jLabel118.setBackground(new java.awt.Color(0, 0, 0));
        jLabel118.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("-");
        jLabel118.setAutoscrolls(true);
        jPanel50.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 10, 20));

        lblNameUser.setBackground(new java.awt.Color(0, 0, 0));
        lblNameUser.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblNameUser.setForeground(new java.awt.Color(117, 117, 117));
        lblNameUser.setText("Nguyễn Thanh Mạnh");
        lblNameUser.setAutoscrolls(true);
        jPanel50.add(lblNameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 190, 20));

        jLabel120.setBackground(new java.awt.Color(0, 0, 0));
        jLabel120.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("-");
        jLabel120.setAutoscrolls(true);
        jPanel50.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 20, 20));

        jLabel121.setBackground(new java.awt.Color(0, 0, 0));
        jLabel121.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("-");
        jLabel121.setAutoscrolls(true);
        jPanel50.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 10, 20));

        jLabel122.setBackground(new java.awt.Color(0, 0, 0));
        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Thông tin giao hàng");
        jLabel122.setAutoscrolls(true);
        jPanel50.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, 30));

        pnlDetail_Order.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 920, 130));

        jLabel114.setBackground(new java.awt.Color(0, 0, 0));
        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText(" THÔNG TIN ĐƠN HÀNG");
        jLabel114.setAutoscrolls(true);
        pnlDetail_Order.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 190, 30));

        jLabel115.setBackground(new java.awt.Color(0, 0, 0));
        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText(" THÔNG TIN ĐƠN HÀNG");
        jLabel115.setAutoscrolls(true);
        pnlDetail_Order.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 190, 30));

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setBackground(new java.awt.Color(0, 0, 0));
        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Thông tin sản phẩm");
        jLabel113.setAutoscrolls(true);
        jPanel49.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 190, 30));

        pnlDetail_Order.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 920, 50));

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, new java.awt.Color(240, 240, 240)));
        jPanel51.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane9.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnlInformation_product_ALL.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformation_product_ALL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 3, true));

        javax.swing.GroupLayout pnlInformation_product_ALLLayout = new javax.swing.GroupLayout(pnlInformation_product_ALL);
        pnlInformation_product_ALL.setLayout(pnlInformation_product_ALLLayout);
        pnlInformation_product_ALLLayout.setHorizontalGroup(
            pnlInformation_product_ALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1023, Short.MAX_VALUE)
        );
        pnlInformation_product_ALLLayout.setVerticalGroup(
            pnlInformation_product_ALLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        jScrollPane9.setViewportView(pnlInformation_product_ALL);

        jPanel51.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 480));

        pnlDetail_Order.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 920, 480));

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel52.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel124.setBackground(new java.awt.Color(0, 0, 0));
        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Phương thức thanh toán");
        jLabel124.setAutoscrolls(true);
        jPanel52.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 190, 30));

        jPanel53.setBackground(new java.awt.Color(204, 255, 204));
        jPanel53.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel53.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel128.setBackground(new java.awt.Color(0, 0, 0));
        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Thành Tiền ");
        jLabel128.setAutoscrolls(true);
        jPanel53.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        jLabel129.setBackground(new java.awt.Color(0, 0, 0));
        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 51, 51));
        jLabel129.setText("200,000đ");
        jLabel129.setAutoscrolls(true);
        jPanel53.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 150, 30));

        jLabel130.setBackground(new java.awt.Color(0, 0, 0));
        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(25, 135, 84));
        jLabel130.setText("SL:");
        jLabel130.setAutoscrolls(true);
        jPanel53.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        jLabel131.setBackground(new java.awt.Color(0, 0, 0));
        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(25, 135, 84));
        jLabel131.setText("SL: 10");
        jLabel131.setAutoscrolls(true);
        jPanel53.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, 30));

        jPanel52.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 920, 50));

        jPanel54.setBackground(new java.awt.Color(204, 255, 204));
        jPanel54.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel54.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel132.setBackground(new java.awt.Color(0, 0, 0));
        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Thành Tiền ");
        jLabel132.setAutoscrolls(true);
        jPanel54.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        jLabel133.setBackground(new java.awt.Color(0, 0, 0));
        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 51, 51));
        jLabel133.setText("200,000đ");
        jLabel133.setAutoscrolls(true);
        jPanel54.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 150, 30));

        jLabel134.setBackground(new java.awt.Color(0, 0, 0));
        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(25, 135, 84));
        jLabel134.setText("SL:");
        jLabel134.setAutoscrolls(true);
        jPanel54.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        jLabel135.setBackground(new java.awt.Color(0, 0, 0));
        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(25, 135, 84));
        jLabel135.setText("SL: 10");
        jLabel135.setAutoscrolls(true);
        jPanel54.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, 30));

        jPanel55.setBackground(new java.awt.Color(204, 255, 204));
        jPanel55.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel55.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel136.setBackground(new java.awt.Color(0, 0, 0));
        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Thành Tiền ");
        jLabel136.setAutoscrolls(true);
        jPanel55.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        jLabel137.setBackground(new java.awt.Color(0, 0, 0));
        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 51, 51));
        jLabel137.setText("200,000đ");
        jLabel137.setAutoscrolls(true);
        jPanel55.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 150, 30));

        jLabel138.setBackground(new java.awt.Color(0, 0, 0));
        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel138.setForeground(new java.awt.Color(25, 135, 84));
        jLabel138.setText("SL:");
        jLabel138.setAutoscrolls(true);
        jPanel55.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        jLabel139.setBackground(new java.awt.Color(0, 0, 0));
        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(25, 135, 84));
        jLabel139.setText("SL: 10");
        jLabel139.setAutoscrolls(true);
        jPanel55.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, 30));

        jPanel54.add(jPanel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 920, 50));

        jPanel52.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 920, 50));

        lblpayment_type_name.setBackground(new java.awt.Color(0, 0, 0));
        lblpayment_type_name.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblpayment_type_name.setForeground(new java.awt.Color(117, 117, 117));
        lblpayment_type_name.setText("Ví Momo");
        lblpayment_type_name.setAutoscrolls(true);
        jPanel52.add(lblpayment_type_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 190, -1));

        jLabel156.setBackground(new java.awt.Color(0, 0, 0));
        jLabel156.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("-");
        jLabel156.setAutoscrolls(true);
        jPanel52.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 10, 20));

        pnlDetail_Order.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 770, 920, 70));

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel56.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel140.setBackground(new java.awt.Color(0, 0, 0));
        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Thành Tiền ");
        jLabel140.setAutoscrolls(true);
        jPanel56.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        lbltotal_amount.setBackground(new java.awt.Color(0, 0, 0));
        lbltotal_amount.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbltotal_amount.setForeground(new java.awt.Color(255, 51, 51));
        lbltotal_amount.setText("200,000đ");
        lbltotal_amount.setAutoscrolls(true);
        jPanel56.add(lbltotal_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 150, 30));

        lblQty.setBackground(new java.awt.Color(0, 0, 0));
        lblQty.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblQty.setForeground(new java.awt.Color(25, 135, 84));
        lblQty.setAutoscrolls(true);
        jPanel56.add(lblQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 40, 30));

        jPanel57.setBackground(new java.awt.Color(204, 255, 204));
        jPanel57.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel57.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel144.setBackground(new java.awt.Color(0, 0, 0));
        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Thành Tiền ");
        jLabel144.setAutoscrolls(true);
        jPanel57.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        jLabel145.setBackground(new java.awt.Color(0, 0, 0));
        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(255, 51, 51));
        jLabel145.setText("200,000đ");
        jLabel145.setAutoscrolls(true);
        jPanel57.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 150, 30));

        jLabel146.setBackground(new java.awt.Color(0, 0, 0));
        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(25, 135, 84));
        jLabel146.setText("SL:");
        jLabel146.setAutoscrolls(true);
        jPanel57.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        jLabel147.setBackground(new java.awt.Color(0, 0, 0));
        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(25, 135, 84));
        jLabel147.setText("SL: 10");
        jLabel147.setAutoscrolls(true);
        jPanel57.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, 30));

        jPanel56.add(jPanel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 920, 50));

        jPanel58.setBackground(new java.awt.Color(204, 255, 204));
        jPanel58.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel58.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel148.setBackground(new java.awt.Color(0, 0, 0));
        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Thành Tiền ");
        jLabel148.setAutoscrolls(true);
        jPanel58.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        jLabel149.setBackground(new java.awt.Color(0, 0, 0));
        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 51, 51));
        jLabel149.setText("200,000đ");
        jLabel149.setAutoscrolls(true);
        jPanel58.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 150, 30));

        jLabel150.setBackground(new java.awt.Color(0, 0, 0));
        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(25, 135, 84));
        jLabel150.setText("SL:");
        jLabel150.setAutoscrolls(true);
        jPanel58.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        jLabel151.setBackground(new java.awt.Color(0, 0, 0));
        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(25, 135, 84));
        jLabel151.setText("SL: 10");
        jLabel151.setAutoscrolls(true);
        jPanel58.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, 30));

        jPanel59.setBackground(new java.awt.Color(204, 255, 204));
        jPanel59.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(240, 240, 240)));
        jPanel59.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel152.setBackground(new java.awt.Color(0, 0, 0));
        jLabel152.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Thành Tiền ");
        jLabel152.setAutoscrolls(true);
        jPanel59.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 30));

        jLabel153.setBackground(new java.awt.Color(0, 0, 0));
        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(255, 51, 51));
        jLabel153.setText("200,000đ");
        jLabel153.setAutoscrolls(true);
        jPanel59.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 150, 30));

        jLabel154.setBackground(new java.awt.Color(0, 0, 0));
        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(25, 135, 84));
        jLabel154.setText("SL:");
        jLabel154.setAutoscrolls(true);
        jPanel59.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, 30));

        jLabel155.setBackground(new java.awt.Color(0, 0, 0));
        jLabel155.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(25, 135, 84));
        jLabel155.setText("SL: 10");
        jLabel155.setAutoscrolls(true);
        jPanel59.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 60, 30));

        jPanel58.add(jPanel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 920, 50));

        jPanel56.add(jPanel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 920, 50));

        pnlDetail_Order.add(jPanel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 720, 920, 50));

        pnlTabAll_profile.add(pnlDetail_Order, "card3");

        pnlProfileSetSize.add(pnlTabAll_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 1040, 900));

        jPanel60.setBackground(new java.awt.Color(248, 249, 250));
        jPanel60.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/twiterter.png"))); // NOI18N
        jPanel60.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 43, 30));

        jLabel116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gg.png"))); // NOI18N
        jPanel60.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 30, 30));

        jLabel117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ig.png"))); // NOI18N
        jPanel60.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, -1, -1));

        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/link.png"))); // NOI18N
        jPanel60.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 43, 30));

        jLabel123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gitH.png"))); // NOI18N
        jPanel60.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 43, 30));

        jLabel125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fb.png"))); // NOI18N
        jPanel60.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, 30));

        pnlProfileSetSize.add(jPanel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1090, 1530, 100));

        jPanel63.setBackground(new java.awt.Color(235, 236, 237));
        jPanel63.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel141.setBackground(new java.awt.Color(255, 255, 255));
        jLabel141.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel141.setToolTipText("");
        jLabel141.setAutoscrolls(true);
        jPanel63.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel64.setBackground(new java.awt.Color(235, 236, 237));
        jPanel64.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel142.setBackground(new java.awt.Color(255, 255, 255));
        jLabel142.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel142.setToolTipText("");
        jLabel142.setAutoscrolls(true);
        jPanel64.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel63.add(jPanel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1185, 1530, 90));

        pnlProfileSetSize.add(jPanel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1185, 1530, 90));

        jScrollPane6.setViewportView(pnlProfileSetSize);

        pnlProfile.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 910));

        jPanel61.setBackground(new java.awt.Color(235, 236, 237));
        jPanel61.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel126.setBackground(new java.awt.Color(255, 255, 255));
        jLabel126.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel126.setToolTipText("");
        jLabel126.setAutoscrolls(true);
        jPanel61.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel62.setBackground(new java.awt.Color(235, 236, 237));
        jPanel62.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel127.setBackground(new java.awt.Color(255, 255, 255));
        jLabel127.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel127.setToolTipText("");
        jLabel127.setAutoscrolls(true);
        jPanel62.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel61.add(jPanel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1185, 1530, 90));

        pnlProfile.add(jPanel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1310, -1, 90));

        pnlTabAll.add(pnlProfile, "card5");

        pnlGioHang.setBackground(new java.awt.Color(255, 51, 153));
        pnlGioHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setPreferredSize(new java.awt.Dimension(1380, 1000));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel190.setText("Trang chủ /");
        jPanel39.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jLabel191.setText("Giỏ hàng");
        jPanel39.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        jLabel192.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel192.setText("GIỎ HÀNG");
        jPanel39.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlitems.setBackground(new java.awt.Color(255, 255, 255));
        pnlitems.setForeground(new java.awt.Color(102, 255, 0));

        javax.swing.GroupLayout pnlitemsLayout = new javax.swing.GroupLayout(pnlitems);
        pnlitems.setLayout(pnlitemsLayout);
        pnlitemsLayout.setHorizontalGroup(
            pnlitemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        pnlitemsLayout.setVerticalGroup(
            pnlitemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        jScrollPane13.setViewportView(pnlitems);

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel78Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel39.add(jPanel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 830, 530));

        jPanel79.setBackground(new java.awt.Color(255, 255, 255));

        jPanel80.setBackground(new java.awt.Color(255, 255, 255));
        jPanel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel80.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel80.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 27, 49, -1));

        jLabel194.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel194.setText("TỔNG ĐƠN HÀNG : ");
        jPanel80.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 33, -1, -1));

        jLabel195.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel195.setText("SẢN PHẨM");
        jPanel80.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        lblTotal_Cart.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotal_Cart.setForeground(new java.awt.Color(255, 153, 153));
        lblTotal_Cart.setText("122");
        jPanel80.add(lblTotal_Cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 106, -1));

        jLabel202.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel202.setText("TỔNG ĐƠN ĐẶT HÀNG ");
        jPanel80.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        lblCartQty.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCartQty.setText("2");
        jPanel80.add(lblCartQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 37, -1));

        jLabel218.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel218.setText("VNĐ");
        jPanel80.add(jLabel218, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 75, -1));

        jLabel204.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel204.setText("Miễn phí giao hàng áp dụng cho đơn hàng");

        jLabel205.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel205.setText("giao tận nơi từ 999.000VND và tất cả các đơn");

        jLabel206.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel206.setText("nhận tại cửa hàng");

        btnThanhToan.setBackground(new java.awt.Color(220, 53, 70));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jButton45.setBackground(new java.awt.Color(220, 53, 70));
        jButton45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton45.setForeground(new java.awt.Color(255, 255, 255));
        jButton45.setText("TIẾP TỤC THANH TOÁN");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel79Layout.createSequentialGroup()
                .addGap(0, 86, Short.MAX_VALUE)
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(jLabel206)
                    .addComponent(jLabel205, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel204, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(jPanel80, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel204)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel205)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel206)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThanhToan)
                .addGap(18, 18, 18)
                .addComponent(jButton45)
                .addGap(0, 183, Short.MAX_VALUE))
        );

        jPanel39.add(jPanel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 220, 480, 570));

        jScrollPane12.setViewportView(jPanel39);

        pnlGioHang.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 820));

        pnlTabAll.add(pnlGioHang, "card5");

        jPanel74.setBackground(new java.awt.Color(255, 255, 255));
        jPanel74.setMinimumSize(new java.awt.Dimension(1600, 1500));
        jPanel74.setPreferredSize(new java.awt.Dimension(1600, 1300));
        jPanel74.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel179.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel179.setForeground(new java.awt.Color(0, 0, 0));
        jLabel179.setText("Tất cả sản phẩm");
        jPanel74.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 150, 30));

        jLabel180.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel180.setForeground(new java.awt.Color(33, 144, 106));
        jLabel180.setText("Trang Chủ /");
        jPanel74.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 90, 30));

        jPanel75.setBackground(new java.awt.Color(248, 249, 250));
        jPanel75.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel181.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/twiterter.png"))); // NOI18N
        jPanel75.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 43, 30));

        jLabel182.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gg.png"))); // NOI18N
        jPanel75.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 30, 30));

        jLabel183.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ig.png"))); // NOI18N
        jPanel75.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, -1, -1));

        jLabel184.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/link.png"))); // NOI18N
        jPanel75.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 40, 43, 30));

        jLabel185.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gitH.png"))); // NOI18N
        jPanel75.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 43, 30));

        jLabel186.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fb.png"))); // NOI18N
        jPanel75.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, 30));

        jPanel74.add(jPanel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 950, 1600, 100));

        jPanel76.setBackground(new java.awt.Color(235, 236, 237));
        jPanel76.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel187.setBackground(new java.awt.Color(255, 255, 255));
        jLabel187.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel187.setForeground(new java.awt.Color(0, 0, 0));
        jLabel187.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel187.setToolTipText("");
        jLabel187.setAutoscrolls(true);
        jPanel76.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel74.add(jPanel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1050, 1540, 90));

        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlWishLists.setBackground(new java.awt.Color(255, 255, 255));
        pnlWishLists.setForeground(new java.awt.Color(102, 255, 0));

        javax.swing.GroupLayout pnlWishListsLayout = new javax.swing.GroupLayout(pnlWishLists);
        pnlWishLists.setLayout(pnlWishListsLayout);
        pnlWishListsLayout.setHorizontalGroup(
            pnlWishListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1368, Short.MAX_VALUE)
        );
        pnlWishListsLayout.setVerticalGroup(
            pnlWishListsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );

        jScrollPane11.setViewportView(pnlWishLists);

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel77Layout.createSequentialGroup()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel74.add(jPanel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 1380, 640));

        jLabel188.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel188.setForeground(new java.awt.Color(0, 0, 0));
        jLabel188.setText("YÊU THÍCH");
        jPanel74.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 140, -1));

        jLabel189.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel189.setForeground(new java.awt.Color(0, 0, 0));
        jLabel189.setText("4 SẢN PHẨM");
        jPanel74.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        jScrollPane10.setViewportView(jPanel74);

        javax.swing.GroupLayout pnlWistLisyRealLayout = new javax.swing.GroupLayout(pnlWistLisyReal);
        pnlWistLisyReal.setLayout(pnlWistLisyRealLayout);
        pnlWistLisyRealLayout.setHorizontalGroup(
            pnlWistLisyRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWistLisyRealLayout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1984, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlWistLisyRealLayout.setVerticalGroup(
            pnlWistLisyRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWistLisyRealLayout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2965, Short.MAX_VALUE))
        );

        pnlTabAll.add(pnlWistLisyReal, "card7");

        detail_product.setPreferredSize(new java.awt.Dimension(1540, 5000));
        detail_product.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlSetSizeDetail_Product.setBackground(new java.awt.Color(255, 255, 255));
        pnlSetSizeDetail_Product.setMinimumSize(new java.awt.Dimension(1530, 3750));
        pnlSetSizeDetail_Product.setPreferredSize(new java.awt.Dimension(1530, 3750));
        pnlSetSizeDetail_Product.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout pnlItemsLayout = new javax.swing.GroupLayout(pnlItems);
        pnlItems.setLayout(pnlItemsLayout);
        pnlItemsLayout.setHorizontalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlItemsLayout.setVerticalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        pnlSetSizeDetail_Product.add(pnlItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 150, 450));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Chia Sẽ");
        jPanel18.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 100, 50));

        lblQtyProduct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblQtyProduct.setForeground(new java.awt.Color(25, 135, 84));
        lblQtyProduct.setText("còn hàng");
        jPanel18.add(lblQtyProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 220, 40));
        jPanel18.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        lblPriceProduct.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPriceProduct.setForeground(new java.awt.Color(0, 0, 0));
        lblPriceProduct.setText("10,000 VNĐ");
        jPanel18.add(lblPriceProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 410, 50));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel18.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 130, 40));

        lblcategory_name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblcategory_name.setForeground(new java.awt.Color(150, 162, 171));
        lblcategory_name.setText("Sản phẩm hữu cơ  ");
        jPanel18.add(lblcategory_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 230, 40));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(150, 162, 171));
        jLabel35.setText("Tình trạng:   ");
        jPanel18.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 110, 40));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("TIẾP TỤC MUA SẮM");
        jPanel18.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 270, 50));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("THÊM VÀO GIỎ HÀNG");
        jPanel18.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 450, 60));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(199, 200, 201));
        jPanel18.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 520, 20));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Số Lượng");
        jPanel18.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 100, 50));

        txtNameProduct.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        txtNameProduct.setForeground(new java.awt.Color(0, 0, 0));
        txtNameProduct.setText("Táo Vàng");
        jPanel18.add(txtNameProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(199, 200, 201));
        jPanel18.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 460, 20));

        jButton5.setBackground(new java.awt.Color(220, 53, 69));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("LIKE");
        jPanel18.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 170, 50));

        pnlSetSizeDetail_Product.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, 450, 550));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("MÔ TẢ SẢN PHẨM");
        pnlSetSizeDetail_Product.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 850, 320, 50));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("- Phương thức canh tác : Thuận tự nhiên, được trồng ở gần biển nên táo có vị hơi mặn, vì vậy rất hợp với nguời bị tiểu đường.");
        pnlSetSizeDetail_Product.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 1020, 970, 20));

        lblDescriptionPr.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblDescriptionPr.setForeground(new java.awt.Color(0, 0, 0));
        lblDescriptionPr.setText("- Mô tả : Táo đỏ ngọt ngọt chua chua");
        pnlSetSizeDetail_Product.add(lblDescriptionPr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 980, 970, 20));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("- Đặc tính : Màu đậm, đẹp");
        pnlSetSizeDetail_Product.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 1040, 450, 20));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("- Vùng nguyên liệu : Ninh Thuận");
        pnlSetSizeDetail_Product.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 1000, 450, 20));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(225, 225, 225)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBoGoc.setBackground(new java.awt.Color(255, 255, 255));
        pnlBoGoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(237, 237, 237)));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("4.9/5");

        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/stars.png"))); // NOI18N

        javax.swing.GroupLayout pnlBoGocLayout = new javax.swing.GroupLayout(pnlBoGoc);
        pnlBoGoc.setLayout(pnlBoGocLayout);
        pnlBoGocLayout.setHorizontalGroup(
            pnlBoGocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
            .addGroup(pnlBoGocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBoGocLayout.createSequentialGroup()
                    .addGap(189, 189, 189)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(231, Short.MAX_VALUE)))
            .addGroup(pnlBoGocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBoGocLayout.createSequentialGroup()
                    .addGap(111, 111, 111)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(150, Short.MAX_VALUE)))
        );
        pnlBoGocLayout.setVerticalGroup(
            pnlBoGocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
            .addGroup(pnlBoGocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBoGocLayout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(119, Short.MAX_VALUE)))
            .addGroup(pnlBoGocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBoGocLayout.createSequentialGroup()
                    .addContainerGap(113, Short.MAX_VALUE)
                    .addComponent(jLabel84)
                    .addGap(72, 72, 72)))
        );

        jPanel16.add(pnlBoGoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 490, 220));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Bạn đánh giá sao về sản phẩm này?");
        jPanel16.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, 350, 30));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("1");
        jPanel17.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 30));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rate.png"))); // NOI18N
        jPanel17.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("5");
        jPanel17.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 30));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("4");
        jPanel17.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, 30));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("3");
        jPanel17.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 30));

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("2");
        jPanel17.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, 30));

        lblRatingOne.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRatingOne.setForeground(new java.awt.Color(0, 0, 0));
        lblRatingOne.setText(" 0 đánh giá");
        jPanel17.add(lblRatingOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, -1, 20));

        lblRatingFive.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRatingFive.setForeground(new java.awt.Color(0, 0, 0));
        lblRatingFive.setText(" 0 đánh giá");
        jPanel17.add(lblRatingFive, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, 40));

        lblRatingFour.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRatingFour.setForeground(new java.awt.Color(0, 0, 0));
        lblRatingFour.setText(" 0 đánh giá");
        jPanel17.add(lblRatingFour, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, 20));

        lblRatingThree.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRatingThree.setForeground(new java.awt.Color(0, 0, 0));
        lblRatingThree.setText(" 0 đánh giá");
        jPanel17.add(lblRatingThree, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, 20));

        lblRatingTwo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRatingTwo.setForeground(new java.awt.Color(0, 0, 0));
        lblRatingTwo.setText(" 0 đánh giá");
        jPanel17.add(lblRatingTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, -1, 20));

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 700, 220));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(199, 200, 201));
        jPanel16.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 840, 1240, 20));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Lọc theo");
        jPanel16.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 860, 100, 30));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(199, 200, 201));
        jPanel16.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 1250, 20));

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jPanel16.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 1250, 130));

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Đánh giá & nhận xét Táo Vàng");
        jPanel16.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 320, 30));

        jButton20.setBackground(new java.awt.Color(220, 53, 69));
        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Gửi Đánh Giá");
        jPanel16.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 730, 180, 50));

        jButton21.setBackground(new java.awt.Color(255, 0, 0));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Tất cả");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 900, 80, 40));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jButton22.setBackground(new java.awt.Color(255, 255, 255));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton22.setForeground(new java.awt.Color(0, 0, 0));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton22.setText("5");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton23.setForeground(new java.awt.Color(0, 0, 0));
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton23.setText("4");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton24.setForeground(new java.awt.Color(0, 0, 0));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton24.setText("3");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(255, 255, 255));
        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton25.setForeground(new java.awt.Color(0, 0, 0));
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton25.setText("2");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton4.setText("1");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 950, 390, -1));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        pnlAllComment.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlAllCommentLayout = new javax.swing.GroupLayout(pnlAllComment);
        pnlAllComment.setLayout(pnlAllCommentLayout);
        pnlAllCommentLayout.setHorizontalGroup(
            pnlAllCommentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1228, Short.MAX_VALUE)
        );
        pnlAllCommentLayout.setVerticalGroup(
            pnlAllCommentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(pnlAllComment);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 114, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1030, 1240, 560));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jButton26.setBackground(new java.awt.Color(255, 255, 255));
        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton26.setForeground(new java.awt.Color(0, 0, 0));
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton26.setText("5");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setBackground(new java.awt.Color(255, 255, 255));
        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton27.setForeground(new java.awt.Color(0, 0, 0));
        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton27.setText("4");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setBackground(new java.awt.Color(255, 255, 255));
        jButton28.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton28.setForeground(new java.awt.Color(0, 0, 0));
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton28.setText("3");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setBackground(new java.awt.Color(255, 255, 255));
        jButton29.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton29.setForeground(new java.awt.Color(0, 0, 0));
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton29.setText("2");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(255, 255, 255));
        jButton30.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton30.setForeground(new java.awt.Color(0, 0, 0));
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/starIcon.png"))); // NOI18N
        jButton30.setText("1");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, -1, -1));

        pnlSetSizeDetail_Product.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1130, 1350, 1640));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("  Chi tiết trước khi sử dụng");
        pnlSetSizeDetail_Product.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 940, 320, 30));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setBackground(new java.awt.Color(255, 255, 255));
        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("SẢN PHẨM MỚI");
        jLabel86.setToolTipText("");
        jLabel86.setAutoscrolls(true);
        jPanel21.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, -1));

        jPanel35.setBackground(new java.awt.Color(248, 249, 250));
        jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/kiwi1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jLabel96))
        );

        jPanel21.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 320, -1));

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel101.setBackground(new java.awt.Color(255, 255, 255));
        jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("VNĐ/kg");
        jLabel101.setToolTipText("");
        jLabel101.setAutoscrolls(true);

        jLabel102.setBackground(new java.awt.Color(255, 255, 255));
        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(62, 137, 84));
        jLabel102.setText("TÁO ĐỎ");
        jLabel102.setToolTipText("");
        jLabel102.setAutoscrolls(true);

        jLabel103.setBackground(new java.awt.Color(255, 255, 255));
        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("10,000 ");
        jLabel103.setToolTipText("");
        jLabel103.setAutoscrolls(true);

        jButton32.setBackground(new java.awt.Color(220, 53, 69));
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(25, 135, 84));
        jButton33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton33.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel103)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel101)
                .addGap(102, 102, 102))
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel102)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel102)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(jLabel103))
                .addGap(18, 18, 18)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 320, -1));

        jPanel65.setBackground(new java.awt.Color(248, 249, 250));
        jPanel65.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel143.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/kiwi1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jLabel143))
        );

        jPanel21.add(jPanel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 320, -1));

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));
        jPanel66.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel157.setBackground(new java.awt.Color(255, 255, 255));
        jLabel157.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(0, 0, 0));
        jLabel157.setText("VNĐ/kg");
        jLabel157.setToolTipText("");
        jLabel157.setAutoscrolls(true);

        jLabel158.setBackground(new java.awt.Color(255, 255, 255));
        jLabel158.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(62, 137, 84));
        jLabel158.setText("TÁO ĐỎ");
        jLabel158.setToolTipText("");
        jLabel158.setAutoscrolls(true);

        jLabel159.setBackground(new java.awt.Color(255, 255, 255));
        jLabel159.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setText("10,000 ");
        jLabel159.setToolTipText("");
        jLabel159.setAutoscrolls(true);

        jButton35.setBackground(new java.awt.Color(220, 53, 69));
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(25, 135, 84));
        jButton37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton37.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel159)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel157)
                .addGap(102, 102, 102))
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel158)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel158)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel157)
                    .addComponent(jLabel159))
                .addGap(18, 18, 18)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 320, -1));

        jPanel67.setBackground(new java.awt.Color(255, 255, 255));
        jPanel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel160.setBackground(new java.awt.Color(255, 255, 255));
        jLabel160.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setText("VNĐ/kg");
        jLabel160.setToolTipText("");
        jLabel160.setAutoscrolls(true);

        jLabel161.setBackground(new java.awt.Color(255, 255, 255));
        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(62, 137, 84));
        jLabel161.setText("TÁO ĐỎ");
        jLabel161.setToolTipText("");
        jLabel161.setAutoscrolls(true);

        jLabel162.setBackground(new java.awt.Color(255, 255, 255));
        jLabel162.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel162.setForeground(new java.awt.Color(0, 0, 0));
        jLabel162.setText("10,000 ");
        jLabel162.setToolTipText("");
        jLabel162.setAutoscrolls(true);

        jButton38.setBackground(new java.awt.Color(220, 53, 69));
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setBackground(new java.awt.Color(25, 135, 84));
        jButton39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton39.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel162)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel160)
                .addGap(102, 102, 102))
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel161)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel161)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel160)
                    .addComponent(jLabel162))
                .addGap(18, 18, 18)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 450, 320, -1));

        jPanel68.setBackground(new java.awt.Color(255, 255, 255));
        jPanel68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel163.setBackground(new java.awt.Color(255, 255, 255));
        jLabel163.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(0, 0, 0));
        jLabel163.setText("VNĐ/kg");
        jLabel163.setToolTipText("");
        jLabel163.setAutoscrolls(true);

        jLabel164.setBackground(new java.awt.Color(255, 255, 255));
        jLabel164.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(62, 137, 84));
        jLabel164.setText("TÁO ĐỎ");
        jLabel164.setToolTipText("");
        jLabel164.setAutoscrolls(true);

        jLabel165.setBackground(new java.awt.Color(255, 255, 255));
        jLabel165.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(0, 0, 0));
        jLabel165.setText("10,000 ");
        jLabel165.setToolTipText("");
        jLabel165.setAutoscrolls(true);

        jButton40.setBackground(new java.awt.Color(220, 53, 69));
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tim.png"))); // NOI18N
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setBackground(new java.awt.Color(25, 135, 84));
        jButton41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton41.setText("Mua Ngay ");

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel165)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel163)
                .addGap(102, 102, 102))
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel164)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel164)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel163)
                    .addComponent(jLabel165))
                .addGap(18, 18, 18)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 450, 320, -1));

        jPanel69.setBackground(new java.awt.Color(248, 249, 250));
        jPanel69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel166.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/kiwi1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jLabel166))
        );

        jPanel21.add(jPanel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, 320, -1));

        jPanel70.setBackground(new java.awt.Color(248, 249, 250));
        jPanel70.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));

        jLabel167.setIcon(new javax.swing.ImageIcon(getClass().getResource("/products/kiwi1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jLabel167))
        );

        jPanel21.add(jPanel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 150, 320, -1));

        pnlSetSizeDetail_Product.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2790, 1530, 600));

        jPanel72.setBackground(new java.awt.Color(235, 236, 237));
        jPanel72.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel174.setBackground(new java.awt.Color(255, 255, 255));
        jLabel174.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel174.setForeground(new java.awt.Color(0, 0, 0));
        jLabel174.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel174.setToolTipText("");
        jLabel174.setAutoscrolls(true);
        jPanel72.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel73.setBackground(new java.awt.Color(235, 236, 237));
        jPanel73.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel175.setBackground(new java.awt.Color(255, 255, 255));
        jLabel175.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel175.setForeground(new java.awt.Color(0, 0, 0));
        jLabel175.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel175.setToolTipText("");
        jLabel175.setAutoscrolls(true);
        jPanel73.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel72.add(jPanel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1185, 1530, 90));

        pnlSetSizeDetail_Product.add(jPanel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3660, -1, 100));

        jPanel71.setBackground(new java.awt.Color(248, 249, 250));
        jPanel71.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel168.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/twiterter.png"))); // NOI18N
        jPanel71.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 43, 30));

        jLabel169.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gg.png"))); // NOI18N
        jPanel71.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 30, 30));

        jLabel170.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ig.png"))); // NOI18N
        jPanel71.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, -1, -1));

        jLabel171.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/link.png"))); // NOI18N
        jPanel71.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 50, 43, 30));

        jLabel172.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gitH.png"))); // NOI18N
        jPanel71.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 43, 30));

        jLabel173.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fb.png"))); // NOI18N
        jPanel71.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, 30));

        pnlSetSizeDetail_Product.add(jPanel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3550, 1530, 110));

        jPanel3.setBackground(new java.awt.Color(248, 249, 250));

        lblImgMain.setPreferredSize(new java.awt.Dimension(650, 560));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(lblImgMain, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(lblImgMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlSetSizeDetail_Product.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 610, 570));

        jScrollPane3.setViewportView(pnlSetSizeDetail_Product);

        detail_product.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1540, 1720));

        pnlTabAll.add(detail_product, "card4");

        pnlAddress.setBackground(new java.awt.Color(255, 51, 153));
        pnlAddress.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1524, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1354, Short.MAX_VALUE)
        );

        jScrollPane7.setViewportView(jPanel40);

        pnlAddress.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1530, 1360));

        pnlTabAll.add(pnlAddress, "card5");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(1600, 1500));
        jPanel4.setPreferredSize(new java.awt.Dimension(1600, 1490));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tất cả sản phẩm");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 150, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(33, 144, 106));
        jLabel26.setText("Trang Chủ /");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 90, 30));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(21, 115, 71));
        jTextField2.setText("Tìm kiếm");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 1070, 40));

        pnlCatalory.setBackground(new java.awt.Color(255, 255, 255));
        pnlCatalory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlCatalory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setBackground(new java.awt.Color(61, 138, 106));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(61, 138, 106));
        jLabel27.setText("      TRÁI CÂY");
        jLabel27.setAutoscrolls(true);
        pnlCatalory.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 70));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("          Việt Quất");
        jLabel28.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(222, 226, 230)));
        pnlCatalory.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 320, 330, 50));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("          Táo");
        jLabel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 226, 230)));
        pnlCatalory.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 70, 330, 50));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("          Chuối");
        jLabel30.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(222, 226, 230)));
        pnlCatalory.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 120, 330, 50));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("          Kiwi");
        jLabel31.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(222, 226, 230)));
        pnlCatalory.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 170, 330, 50));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("          Nho");
        jLabel32.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(222, 226, 230)));
        pnlCatalory.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 220, 330, 50));

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("          Cherry");
        jLabel33.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(222, 226, 230)));
        pnlCatalory.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 270, 330, 50));

        jPanel4.add(pnlCatalory, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 340, 370));

        jButton1.setBackground(new java.awt.Color(25, 135, 84));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sapxep.png"))); // NOI18N
        jButton1.setText("SẮP XẾP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 150, 150, 40));

        pnl_Products.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_ProductsLayout = new javax.swing.GroupLayout(pnl_Products);
        pnl_Products.setLayout(pnl_ProductsLayout);
        pnl_ProductsLayout.setHorizontalGroup(
            pnl_ProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        pnl_ProductsLayout.setVerticalGroup(
            pnl_ProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(pnl_Products, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 920, 800));

        jPanel15.setBackground(new java.awt.Color(248, 249, 250));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/twiterter.png"))); // NOI18N
        jPanel15.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 43, 30));

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gg.png"))); // NOI18N
        jPanel15.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 30, 30));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ig.png"))); // NOI18N
        jPanel15.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, -1, -1));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/link.png"))); // NOI18N
        jPanel15.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 40, 43, 30));

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gitH.png"))); // NOI18N
        jPanel15.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 43, 30));

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fb.png"))); // NOI18N
        jPanel15.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, 30));

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1080, 1600, 100));

        jPanel26.setBackground(new java.awt.Color(235, 236, 237));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel63.setBackground(new java.awt.Color(255, 255, 255));
        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("FPT Polytechnic © 2017. All rights reserved. ");
        jLabel63.setToolTipText("");
        jLabel63.setAutoscrolls(true);
        jPanel26.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jPanel4.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1180, 1540, 90));

        jScrollPane1.setViewportView(jPanel4);

        javax.swing.GroupLayout productsLayout = new javax.swing.GroupLayout(products);
        products.setLayout(productsLayout);
        productsLayout.setHorizontalGroup(
            productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 445, Short.MAX_VALUE))
        );
        productsLayout.setVerticalGroup(
            productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2955, Short.MAX_VALUE))
        );

        pnlTabAll.add(products, "card3");

        getContentPane().add(pnlTabAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 86, 1990, 3830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lblIconSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconSearchMouseEntered
        lblIconSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblIconSearchMouseEntered

    private void lblIconUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconUserMouseEntered
        lblIconUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblIconUserMouseEntered

    private void lblIconHeaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconHeaMouseEntered
        lblIconHea.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblIconHeaMouseEntered

    private void lblIconShopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconShopMouseEntered
        lblIconShop.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }//GEN-LAST:event_lblIconShopMouseEntered

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void lblLichSuMuaHANGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuMuaHANGMouseClicked
        pnlTabAll_profile.removeAll();
        pnlTabAll_profile.add(pnlHistoryOrder);
        pnlTabAll_profile.repaint();
        pnlTabAll_profile.revalidate();
    }//GEN-LAST:event_lblLichSuMuaHANGMouseClicked

    private void lblIconUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconUserMouseClicked
        pnlBtnNav.setVisible(true);
    }//GEN-LAST:event_lblIconUserMouseClicked

    private void pnlBtnNavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBtnNavMouseExited
        pnlBtnNav.setVisible(false);
    }//GEN-LAST:event_pnlBtnNavMouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        pnlTabAll.removeAll();
        pnlTabAll.add(pnlMain);
        pnlTabAll.repaint();
        pnlTabAll.revalidate();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        pnlTabAll.removeAll();
        pnlTabAll.add(pnlMain);
        pnlTabAll.repaint();
        pnlTabAll.revalidate();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel108MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseClicked
        pnlTabAll.removeAll();
        pnlTabAll.add(products);
        pnlTabAll.repaint();
        pnlTabAll.revalidate();

    }//GEN-LAST:event_jLabel108MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        pnlTabAll.removeAll();
        pnlTabAll.add(pnlProfile);
        pnlTabAll.repaint();
        pnlTabAll.revalidate();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked
        pnlTabAll_profile.removeAll();
        pnlTabAll_profile.add(pnlFogetPass);
        pnlTabAll_profile.repaint();
        pnlTabAll_profile.revalidate();
    }//GEN-LAST:event_lblDoiMatKhauMouseClicked

    private void jButton42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton42MouseClicked
        pnlTabAll_profile.removeAll();
        pnlTabAll_profile.add(pnlEditProfile);
        pnlTabAll_profile.repaint();
        pnlTabAll_profile.revalidate();

    }//GEN-LAST:event_jButton42MouseClicked

    private void lblThongTinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongTinMouseClicked
        pnlTabAll_profile.removeAll();
        pnlTabAll_profile.add(pnlProfile_user);
        pnlTabAll_profile.repaint();
        pnlTabAll_profile.revalidate();
    }//GEN-LAST:event_lblThongTinMouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void lblIconShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconShopMouseClicked
        if (user == null) {
            JOptionPane.showMessageDialog(null, "bạn phải đăng nhập mới xem được giỏ hàng");
        } else {

            pnlTabAll.removeAll();
            pnlTabAll.add(pnlGioHang);
            pnlTabAll.repaint();
            pnlTabAll.revalidate();

            renderGioHang();
        }


    }//GEN-LAST:event_lblIconShopMouseClicked

    private void lblIconHeaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconHeaMouseClicked
        pnlTabAll.removeAll();
        pnlTabAll.add(pnlWistLisyReal);
        pnlTabAll.repaint();
        pnlTabAll.revalidate();
    }//GEN-LAST:event_lblIconHeaMouseClicked

    private void lblChinhSuaDiaChiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChinhSuaDiaChiMouseClicked
        pnlTabAll_profile.removeAll();
        pnlTabAll_profile.add(pnlAddress_Profile);
        pnlTabAll_profile.repaint();
        pnlTabAll_profile.revalidate();
    }//GEN-LAST:event_lblChinhSuaDiaChiMouseClicked

    private void txtEditCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEditCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEditCityActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton46ActionPerformed

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked
        pnlTabAll.removeAll();
        fillUPM(Integer.parseInt(user.getId()));
        renderAddress();
        renderChiTietThanhToan();
        pnlTabAll.add(pnlChiTietThanhToan);
        pnlTabAll.repaint();
        pnlTabAll.revalidate();
    }//GEN-LAST:event_btnThanhToanMouseClicked

    private void btnDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseClicked
        DangNhap dn = new DangNhap(this, true);
        this.setVisible(false);
        dn.setVisible(true);

    }//GEN-LAST:event_btnDangNhapMouseClicked

    private void btnDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangKyMouseClicked
        DangKy dn = new DangKy(this, true);
        dn.setVisible(true);

    }//GEN-LAST:event_btnDangKyMouseClicked

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed

    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDangKyActionPerformed

    private void JcbDiaChiGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcbDiaChiGiaoHangActionPerformed

    }//GEN-LAST:event_JcbDiaChiGiaoHangActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

    }//GEN-LAST:event_btnThanhToanActionPerformed
///ádsadsadsa
    private void btnSelectDiaChiGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectDiaChiGiaoHangActionPerformed

    }//GEN-LAST:event_btnSelectDiaChiGiaoHangActionPerformed

    private void btnSelectDiaChiGiaoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectDiaChiGiaoHangMouseClicked
        NewAddress ad = new NewAddress(this, true);
        ad.setVisible(true);
    }//GEN-LAST:event_btnSelectDiaChiGiaoHangMouseClicked

    private void JcbDiaChiGiaoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JcbDiaChiGiaoHangMouseClicked
        renderAddress();
    }//GEN-LAST:event_JcbDiaChiGiaoHangMouseClicked

    private void bntThanhToanDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntThanhToanDonHangMouseClicked
        ThanhToan();

    }//GEN-LAST:event_bntThanhToanDonHangMouseClicked
    public void renderAddress() {
        AddressDAO addressDAO = new AddressDAO();
        List<Address> ListAddresses = addressDAO.selectByUserId(Integer.parseInt(user.getId()));

        System.out.println(ListAddresses.toString());

        DefaultComboBoxModel model = new DefaultComboBoxModel<>();
        for (Address ListAddresse : ListAddresses) {
            model.addElement(ListAddresse);
        }
        JcbDiaChiGiaoHang.setModel(model);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        UIManager.put("TextComponent.arc", 13);
        UIManager.put("Button.arc", 10);
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new mainform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JcbDiaChiGiaoHang;
    private javax.swing.JComboBox<String> JcbPhuongThucThanhToan;
    private javax.swing.JButton bntThanhToanDonHang;
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnSelectDiaChiGiaoHang;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JPanel detail_product;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lblAddress_User;
    private javax.swing.JLabel lblCartQty;
    private javax.swing.JLabel lblChinhSuaDiaChi;
    private javax.swing.JLabel lblDescriptionPr;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblEmail_Profile;
    private javax.swing.JLabel lblIconHea;
    private javax.swing.JLabel lblIconSearch;
    private javax.swing.JLabel lblIconShop;
    private javax.swing.JLabel lblIconUser;
    private javax.swing.JLabel lblImgMain;
    private javax.swing.JLabel lblLichSuMuaHANG;
    private javax.swing.JLabel lblNameUser;
    private javax.swing.JLabel lblPriceProduct;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblQtyCart;
    private javax.swing.JLabel lblQtyProduct;
    private javax.swing.JLabel lblRatingFive;
    private javax.swing.JLabel lblRatingFour;
    private javax.swing.JLabel lblRatingOne;
    private javax.swing.JLabel lblRatingThree;
    private javax.swing.JLabel lblRatingTwo;
    private javax.swing.JLabel lblSdtUser;
    private javax.swing.JLabel lblThongTin;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal_Cart;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblcategory_name;
    private javax.swing.JLabel lblpayment_type_name;
    private javax.swing.JLabel lbltotal_amount;
    private javax.swing.JPanel pnlAddress;
    private javax.swing.JPanel pnlAddress_Profile;
    private javax.swing.JPanel pnlAddress_User;
    private javax.swing.JPanel pnlAllComment;
    private javax.swing.JPanel pnlBoGoc;
    private javax.swing.JPanel pnlBtnNav;
    private javax.swing.JPanel pnlCatalory;
    private javax.swing.JPanel pnlChiTietThanhToan;
    private javax.swing.JPanel pnlChinhSuaDiaChi;
    private javax.swing.JPanel pnlDetail_Order;
    private javax.swing.JPanel pnlDetail_Order_All;
    private javax.swing.JPanel pnlDoiMatKhau;
    private javax.swing.JPanel pnlEditAddress;
    private javax.swing.JPanel pnlEditProfile;
    private javax.swing.JPanel pnlFogetPass;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlHistoryOrder;
    private javax.swing.JPanel pnlInformation_product_ALL;
    private javax.swing.JPanel pnlItems;
    private javax.swing.JPanel pnlLichSuMuaHANG;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlProfileSetSize;
    private javax.swing.JPanel pnlProfile_user;
    private javax.swing.JPanel pnlSanPhamThanhToan_ChiTiet;
    private javax.swing.JPanel pnlScroll_SanPhamThanhToanChioTiet;
    private javax.swing.JPanel pnlSetSizeDetail_Product;
    private javax.swing.JPanel pnlTabAll;
    private javax.swing.JPanel pnlTabAll_profile;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JPanel pnlWishLists;
    private javax.swing.JPanel pnlWistLisyReal;
    private javax.swing.JPanel pnl_Products;
    private javax.swing.JPanel pnlitems;
    private javax.swing.JPanel products;
    private javax.swing.JTextField txtEditCity;
    private javax.swing.JTextField txtEditStreet;
    private javax.swing.JTextField txtEditWard;
    private javax.swing.JLabel txtNameProduct;
    // End of variables declaration//GEN-END:variables

    private static class lblgiaChiTietThanhToan {

        public lblgiaChiTietThanhToan() {
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Admin
 */
public class OrderDetail {
    private String idrOder,dateOrder;
    private double orderTotal;

    public OrderDetail(String idrOder, String dateOrder, double orderTotal) {
        this.idrOder = idrOder;
        this.dateOrder = dateOrder;
        this.orderTotal = orderTotal;
    }

    public OrderDetail() {
    }
    

    public String getIdrOder() {
        return idrOder;
    }

    public void setIdrOder(String idrOder) {
        this.idrOder = idrOder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
    
    
    
    
    
    
}

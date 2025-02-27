/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author PC
 */
public class Order_Item {
    int id;
    int order_id;
    int product_item_id;
    int qty;
    float price;

    public Order_Item() {
    }

    public Order_Item(int id, int order_id, int product_item_id, int qty, float price) {
        this.id = id;
        this.order_id = order_id;
        this.product_item_id = product_item_id;
        this.qty = qty;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_item_id() {
        return product_item_id;
    }

    public void setProduct_item_id(int product_item_id) {
        this.product_item_id = product_item_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order_Item{" + "id=" + id + ", order_id=" + order_id + ", product_item_id=" + product_item_id + ", qty=" + qty + ", price=" + price + '}';
    }
    
}

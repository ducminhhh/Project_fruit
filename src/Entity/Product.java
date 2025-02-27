/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Product {
    private int id; 
    private int category_id; 
    private String name; 
    private String description;
    private Date create_at; 
    private Date update_at;

    public Product() {
    }

    public Product(int id, int category_id, String name, String description, Date create_at, Date update_at) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", category_id=" + category_id + ", name=" + name + ", description=" + description + ", create_at=" + create_at + ", update_at=" + update_at + '}';
    }
    
    
}

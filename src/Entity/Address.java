/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author PC
 */
public class Address {
    private String id;
    private String city;
    private String ward;
    private String street;
    private boolean isDefault;
    public Address() {
    }

    public Address(String id, String city, String ward, String street, boolean isDefault) {
        this.id = id;
        this.city = city;
        this.ward = ward;
        this.street = street;
        this.isDefault = isDefault;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    
    @Override
    public String toString() {
        return this.city + ", " + this.ward + ", " + this.street;
    }
    
    
}

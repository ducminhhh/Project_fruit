/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Admin
 */
public class comment {
    private String tenUser, ngayDanhGia,danhGia_rate;
    private int numberRate;

    public comment(String tenUser, String ngayDanhGia, String danhGia_rate, int numberRate) {
        this.tenUser = tenUser;
        this.ngayDanhGia = ngayDanhGia;
        this.danhGia_rate = danhGia_rate;
        this.numberRate = numberRate;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(String ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    public String getDanhGia_rate() {
        return danhGia_rate;
    }

    public void setDanhGia_rate(String danhGia_rate) {
        this.danhGia_rate = danhGia_rate;
    }

    public int getNumberRate() {
        return numberRate;
    }

    public void setNumberRate(int numberRate) {
        this.numberRate = numberRate;
    }
    
    
}
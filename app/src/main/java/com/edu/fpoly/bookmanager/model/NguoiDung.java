package com.edu.fpoly.bookmanager.model;

public class NguoiDung {
    private String userName;
    private String passWord;
    private String phone;
    private String hoTen;
    public NguoiDung(){};
    public NguoiDung(String userName, String passWord, String phone, String hoTen) {
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.hoTen = hoTen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public String toString(){
        return "NguoiDung{" +
                "  userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", passWord='" + passWord + '\'' +
                ", hoTen ='" + hoTen + '\'' +
                '}';
    }
}

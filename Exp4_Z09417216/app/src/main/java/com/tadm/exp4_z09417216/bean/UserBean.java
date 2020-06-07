package com.tadm.exp4_z09417216.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class UserBean extends LitePalSupport implements Serializable {
    private int id;
    private int avatar;
    private String name;
    private String phone;
    private String address;
    private String email;

//    public UserBean(int avatar, String name, String phone, String address, String email) {
//        this.avatar = avatar;
//        this.name = name;
//        this.phone = phone;
//        this.address = address;
//        this.email = email;
//    }

    public int getId() {
        return id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

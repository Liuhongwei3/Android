package com.tadm.framelayout;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class UserBean extends LitePalSupport implements Serializable {
    private int avatar;
    private String name;
    private String pwd;
    private String sex;
    private String dept;
    private String sdept;

    public UserBean(int avatar, String name, String pwd, String sex, String dept, String sdept) {
        this.avatar = avatar;
        this.name = name;
        this.pwd = pwd;
        this.sex = sex;
        this.dept = dept;
        this.sdept = sdept;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }
}

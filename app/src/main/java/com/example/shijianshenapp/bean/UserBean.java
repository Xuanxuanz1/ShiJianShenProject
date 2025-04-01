package com.example.shijianshenapp.bean;

import org.litepal.crud.LitePalSupport;

public class UserBean extends LitePalSupport {
    private String userName;
    private String password;
    private String phone;
    private int age;
    private String birthday;
    private double hight;
    private double weight;
    private String petName;
    private int integration;

    public static String loginName = "";
    public static String userPetName = "";
    public static boolean isClock = false;

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        UserBean.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getHight() {
        return hight;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

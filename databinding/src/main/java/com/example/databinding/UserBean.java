package com.example.databinding;

/**
 * Created by steam_lb on 2018/8/6/006.
 */

public class UserBean {
    private String name;
    private String age;
    private String address;

    public UserBean(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public UserBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

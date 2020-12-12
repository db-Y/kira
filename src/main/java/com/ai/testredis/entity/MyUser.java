package com.ai.testredis.entity;

import java.io.Serializable;

public class MyUser implements Serializable {

    static final long serialVersionUID = 1L;
    private Integer id;
    private String userName;
    private String sex;

    public MyUser(){}
    public MyUser(Integer id, String userName, String sex){
        this.id=id;
        this.userName=userName;
        this.sex=sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

package com.cyx.entity;

/**
 * @Title: Tables
 * @Author 曦
 * @Date 2025/6/5 18:01
 * @description:
 */

public class Tables {
    private Integer id;
    private String no;
    private String unit;
    private String free;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }


}

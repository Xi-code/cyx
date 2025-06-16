package com.cyx.entity;

import java.math.BigDecimal;

/**
 * @Title: Foods
 * @Author 曦
 * @Date 2025/6/6 20:45
 * @description:
 */

public class Foods {
    private Integer id;
    private String name;
    private String descr;
    private BigDecimal  price;
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

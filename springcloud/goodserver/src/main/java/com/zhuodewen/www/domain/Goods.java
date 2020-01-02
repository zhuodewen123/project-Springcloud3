package com.zhuodewen.www.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品类
 */
@Entity
@Table(name = "goods")
public class Goods implements Serializable {

    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Mysql自增长
    private Integer id;

    //商品名称
    @Column(name = "good_name")
    private String goodName;

    //商品编码
    @Column(name = "good_code")
    private String goodCode;

    //商品标题
    @Column(name = "title")
    private String title;

    //价格
    @Column(name = "price")
    private BigDecimal price;

    //折扣
    @Column(name = "discount")
    private BigDecimal discount;

    //商品描述
    @Column(name = "context")
    private String context;

    //商品链接
    @Column(name = "url")
    private String url;

    //图片地址
    @Column(name = "pic_url")
    private String picUrl;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
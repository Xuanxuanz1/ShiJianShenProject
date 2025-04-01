package com.example.shijianshenapp.bean;

import org.litepal.crud.LitePalSupport;

public class ShopBean extends LitePalSupport {
    private int img;
    private String goodsName;
    private int goodsQty;//总数量
    private int sold;//已售数量
    private int goodsPrice;
    private String typeName;//分类名称
    private boolean isSales;//今日是否已销售
    private String goodsDrtail;

    public String getGoodsDrtail() {
        return goodsDrtail;
    }

    public void setGoodsDrtail(String goodsDrtail) {
        this.goodsDrtail = goodsDrtail;
    }

    public boolean isSales() {
        return isSales;
    }

    public void setSales(boolean sales) {
        isSales = sales;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(int goodsQty) {
        this.goodsQty = goodsQty;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}

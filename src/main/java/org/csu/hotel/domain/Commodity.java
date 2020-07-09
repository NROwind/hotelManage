package org.csu.hotel.domain;

import java.util.Date;

//商品类
public class Commodity {
    private String name;
    private double price;
    private int shelfQuantity;
    private int stockQuantity;
    private Date beginprovidetime;
    private Date stopprovidetime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShelfQuantity() {
        return shelfQuantity;
    }

    public void setShelfQuantity(int shelfQuantity) {
        this.shelfQuantity = shelfQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Date getBeginprovidetime() {
        return beginprovidetime;
    }

    public void setBeginprovidetime(Date beginprovidetime) {
        this.beginprovidetime = beginprovidetime;
    }

    public Date getStopprovidetime() {
        return stopprovidetime;
    }

    public void setStopprovidetime(Date stopprovidetime) {
        this.stopprovidetime = stopprovidetime;
    }
}

package org.csu.hotel.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//商品类
@Data
public class Commodity {
    private int id;
    private String name;
    private double price;
    private int shelfQuantity;
    private int stockQuantity;
    private Date beginProvideTime;
    private Date stopProvideTime;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return beginProvideTime;
    }

    public void setBeginprovidetime(Date beginprovidetime) {
        this.beginProvideTime = beginprovidetime;
    }

    public Date getStopprovidetime() {
        return stopProvideTime;
    }

    public void setStopprovidetime(Date stopprovidetime) {
        this.stopProvideTime = stopprovidetime;
    }
}

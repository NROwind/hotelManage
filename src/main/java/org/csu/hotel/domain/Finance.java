package org.csu.hotel.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Finance {
    private int financeId;
    private String date;
    private int roomType;
    private double commodityPrice;
    private double totalPrice;
    private double replacePrice;
    private double roomPrice;

    public double getReplacePrice() {
        return replacePrice;
    }

    public void setReplacePrice(double replacePrice) {
        this.replacePrice = replacePrice;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

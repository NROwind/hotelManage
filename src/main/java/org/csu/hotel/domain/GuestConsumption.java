package org.csu.hotel.domain;

import lombok.Data;

import java.util.Date;

@Data
public class GuestConsumption {
    //房客
    private Tenant tenant;
    private Commodity commodity;
    private int quantity;
    private Date date;
    private double price;
    private int stay_id;

    public int getStay_id() {
        return stay_id;
    }

    public void setStay_id(int stay_id) {
        this.stay_id = stay_id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package org.csu.hotel.domain;

import java.util.Date;

public class Stay {
    private int tenantId;
    private int roomId;
    private Date stayStartTime;
    private Date stayEndTime;
    private double money;
    private double paidMoney;
    private double deposit;
    private double paidDeposit;
    private Date orderTime;
    private int inStay;
    private int isCancel;

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getStayStartTime() {
        return stayStartTime;
    }

    public void setStayStartTime(Date stayStartTime) {
        this.stayStartTime = stayStartTime;
    }

    public Date getStayEndTime() {
        return stayEndTime;
    }

    public void setStayEndTime(Date stayEndTime) {
        this.stayEndTime = stayEndTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getPaidDeposit() {
        return paidDeposit;
    }

    public void setPaidDeposit(double paidDeposit) {
        this.paidDeposit = paidDeposit;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getInStay() {
        return inStay;
    }

    public void setInStay(int inStay) {
        this.inStay = inStay;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }
}

package org.csu.hotel.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Stay {
    @TableId
    private int stayId;
    private Tenant tenant;
    private int roomId;
    private Date stayStartTime;
    private Date stayEndTime;
    private double money;
    private double paidMoney;
    private double deposit;
    private double paidDeposit;
    private Date orderTime;
    private int isStay;
    private int isCancel;

    public int getStayId() {
        return stayId;
    }

    public void setStayId(int stayId) {
        this.stayId = stayId;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
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
        return isStay;
    }

    public void setInStay(int inStay) {
        this.isStay = inStay;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }
}

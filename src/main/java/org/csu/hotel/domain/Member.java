package org.csu.hotel.domain;

import java.util.Date;

public class Member {
    private int id;
    private String name;
    private long phone;
    private String email;
    private Date birthday;
    private int memberShipLevel;
    private double discount;
    private double integral;
    private String favoriteAffairs;

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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getMemberShipLevel() {
        return memberShipLevel;
    }

    public void setMemberShipLevel(int memberShipLevel) {
        this.memberShipLevel = memberShipLevel;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public String getFavoriteAffairs() {
        return favoriteAffairs;
    }

    public void setFavoriteAffairs(String favoriteAffairs) {
        this.favoriteAffairs = favoriteAffairs;
    }
}

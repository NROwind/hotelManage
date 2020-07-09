package org.csu.hotel.domain;

public class Replace {
    private Financial financial;
    private Commodity commodity;
    private int commodityNum;
    private double totalPrice;


    public Financial getFinancial() {
        return financial;
    }

    public void setFinancial(Financial financial) {
        this.financial = financial;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(int commodityNum) {
        this.commodityNum = commodityNum;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

package org.csu.hotel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@TableName("room_type")
@NoArgsConstructor
public class RoomType {
    private int id;
    private String name;
    private double price;
    private int numOfPerson;
    private int numOfBed;
    private int totalQuantity;
    private String airConditioner;
    private double lengthOfBed;
    private double widthOfBed;
    private int remainingQuantity;
    private String roomService;
    private String internet;
    private String hasBreakfast;
    private String hasReserve;
    private String hasDiscount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumOfPerson() {
        return numOfPerson;
    }

    public void setNumOfPerson(int numOfPerson) {
        this.numOfPerson = numOfPerson;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(String airConditioner) {
        this.airConditioner = airConditioner;
    }

    public double getLengthOfBed() {
        return lengthOfBed;
    }

    public void setLengthOfBed(double lengthOfBed) {
        this.lengthOfBed = lengthOfBed;
    }

    public double getWidthOfBed() {
        return widthOfBed;
    }

    public void setWidthOfBed(double widthOfBed) {
        this.widthOfBed = widthOfBed;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getRoomService() {
        return roomService;
    }

    public void setRoomService(String roomService) {
        this.roomService = roomService;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getHasBreakfast() {
        return hasBreakfast;
    }

    public void setHasBreakfast(String hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public String getHasReserve() {
        return hasReserve;
    }

    public void setHasReserve(String hasReserve) {
        this.hasReserve = hasReserve;
    }

    public String getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(String hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

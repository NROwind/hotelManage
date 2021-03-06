package org.csu.hotel.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Floor {
    @TableId
    private int numOfFloor;
    private Date cleanTime;
    private int roomQuantity;
    private String supportingRoom;

    public int getNumOfFloor() {
        return numOfFloor;
    }

    public void setNumOfFloor(int numOfFloor) {
        this.numOfFloor = numOfFloor;
    }

    public Date getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(Date cleanTime) {
        this.cleanTime = cleanTime;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public String getSupportingRoom() {
        return supportingRoom;
    }

    public void setSupportingRoom(String supportingRoom) {
        this.supportingRoom = supportingRoom;
    }
}

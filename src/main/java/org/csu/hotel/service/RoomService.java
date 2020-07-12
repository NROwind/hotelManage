package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.Room;

import java.util.List;

public interface RoomService extends IService<Room>{

    public List<Room> getAllRooms();
}

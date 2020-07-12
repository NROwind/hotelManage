package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.Room;
import org.csu.hotel.persistence.RoomMapper;
import org.csu.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Autowired
    private RoomMapper roomMapper;
    public List<Room> getRoomandRoomtype(int id){
        return this.baseMapper.getRoomandRoomtype(id);
    }
    public List<Room> getAllRooms(){
        return roomMapper.getAllRooms();
    }

}

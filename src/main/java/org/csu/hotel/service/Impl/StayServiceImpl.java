package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.Room;
import org.csu.hotel.domain.Stay;
import org.csu.hotel.persistence.RoomMapper;
import org.csu.hotel.persistence.StayMapper;
import org.csu.hotel.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class StayServiceImpl extends ServiceImpl<StayMapper, Stay> implements StayService {

    @Autowired
    private StayMapper stayMapper;

    public List<Stay> getAllStays(){
        return stayMapper.getAllStays();
    }

    public Stay getStayByRoomId(int roomId){
        return stayMapper.getStayByRoomId(roomId);
    }
}

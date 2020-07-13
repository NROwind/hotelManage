package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.RoomType;
import org.csu.hotel.domain.Stay;
import org.csu.hotel.persistence.RoomTypeMapper;
import org.csu.hotel.persistence.StayMapper;
import org.csu.hotel.service.RoomTypeService;
import org.csu.hotel.service.StayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoomTypeServiceImpl extends ServiceImpl<RoomTypeMapper, RoomType> implements RoomTypeService {
    public List<RoomType> getAllroomType(){
        return this.baseMapper.getAllroomType();
    }

}

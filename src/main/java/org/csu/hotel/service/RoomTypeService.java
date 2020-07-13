package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.RoomType;

import java.util.List;

public interface RoomTypeService extends IService<RoomType> {
    public List<RoomType> getAllroomType();
}

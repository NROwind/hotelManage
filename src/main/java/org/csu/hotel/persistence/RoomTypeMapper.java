package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.csu.hotel.domain.RoomType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeMapper extends BaseMapper<RoomType> {

    @Select("select * from room_type")
    public List<RoomType> getAllroomType();
}

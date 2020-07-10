package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.hotel.domain.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomMapper extends BaseMapper<Room> {
}

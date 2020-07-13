package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.csu.hotel.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMapper extends BaseMapper<Room> {

    @Select("select * from room")
    @Results(id = "room",value =
                @Result(column = "type_id", property = "roomType",
            one = @One(select = "org.csu.hotel.persistence.RoomTypeMapper.selectById"))
    )
    List<Room> getAllRooms();

    @Select("select * from room where room_id=#{id}")
    @Results(id="room_id",value=
        @Result(column = "type_id",property = "roomType",
                one=@One(select = "org.csu.hotel.persistence.RoomTypeMapper.selectById")))
    List<Room> getRoomandRoomtype(int id);

//    @Insert("insert into room value(#{id},#{typeId},#{floor},#{status})")
//    Boolean insertRoom(int id,int floor,String status,int typeId);


}

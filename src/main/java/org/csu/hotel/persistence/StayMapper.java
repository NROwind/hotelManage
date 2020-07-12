package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.csu.hotel.domain.Room;
import org.csu.hotel.domain.Stay;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayMapper extends BaseMapper<Stay> {

    @Select("select * from stay")
    @Results(id = "stayList",value =
    @Result(column = "tenant_id", property = "tenant",
            one = @One(select = "org.csu.hotel.persistence.TenantMapper.selectById"))
    )
    List<Stay> getAllStays();

    @Select("select * from stay where room_id=#{roomId}")
    @Results(id = "stayByroom",value =
    @Result(column = "tenant_id", property = "tenant",
            one = @One(select = "org.csu.hotel.persistence.TenantMapper.selectById"))
    )
    Stay getStayByRoomId(int roomId);


}

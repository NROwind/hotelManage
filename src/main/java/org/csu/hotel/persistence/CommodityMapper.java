package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.csu.hotel.domain.Commodity;
import org.csu.hotel.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityMapper extends BaseMapper<Commodity> {
    @Select("select * from commodity")
    List<Room> getAllCommodities();
}

package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.csu.hotel.domain.Room;
import org.csu.hotel.domain.Stay;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    List<Stay> getStaysByRoomId(int roomId);


    @Select("select * from stay where tenant_id = #{tenantId}")
    @Results(id = "stayListTenant",value =
    @Result(column = "tenant_id", property = "tenant",
            one = @One(select = "org.csu.hotel.persistence.TenantMapper.selectById"))
    )
    List<Stay> getStaysByTenantId(int tenantId);


    @Insert("insert into stay values (#{stayId},#{tenantId},#{roomId},#{stayStartTime}," +
            "#{stayEndTime},#{money},#{paidMoney},#{deposit},#{paidDeposit},#{orderTime},#{isStay},#{isCancel})")
    public int insertStay(int stayId, int tenantId, int roomId,Date stayStartTime,Date stayEndTime,double money,
                              double paidMoney,double deposit,double paidDeposit,Date orderTime,int isStay,int isCancel);

    @Update("update stay set " +
            "tenant_id = #{tenantId}," +
            "room_id = #{roomId}," +
            "stay_start_time = #{stayStartTime}," +
            "stay_end_time = #{stayEndTime}," +
            "money = #{money}," +
            "paid_money = #{paidMoney}," +
            "deposit = #{deposit}," +
            "paid_deposit = #{paidDeposit}," +
            "order_time = #{orderTime}," +
            "is_stay = #{isStay}," +
            "is_cancel = #{isCancel}" +
            " where stay_id = #{stayId}")
    public int updateStay(int tenantId, int roomId,Date stayStartTime,Date stayEndTime,double money,
                              double paidMoney,double deposit,double paidDeposit,Date orderTime,int isStay,int isCancel,int stayId);
}

package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.csu.hotel.domain.GuestConsumption;
import org.csu.hotel.domain.Stay;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GuestConsumptionMapper extends BaseMapper<GuestConsumption> {

    @Select("select consumption_id,tenant_id ,commodity_id,sum(quantity) as quantity,sum(price) as price from guestconsumption " +
            "where tenant_id = #{tenantId} and stay_id = #{stayId} group by commodity_id")
    @Results(id = "consumptionTenant",value ={
        @Result(column = "tenant_id", property = "tenant",
            one = @One(select = "org.csu.hotel.persistence.TenantMapper.selectById")),
            @Result(column = "commodity_id", property = "commodity",
                    one = @One(select = "org.csu.hotel.persistence.CommodityMapper.selectById"))}
    )
    List<GuestConsumption> getAllConsumptionsByTenantId(int tenantId,int stayId);

    @Select("select * from guestconsumption where tenant_id=#{tenantId}")
    @Results(id = "consumptionTenant2",value ={
            @Result(column = "tenant_id", property = "tenant",
                    one = @One(select = "org.csu.hotel.persistence.TenantMapper.selectById")),
            @Result(column = "commodity_id", property = "commodity",
                    one = @One(select = "org.csu.hotel.persistence.CommodityMapper.selectById"))})
    List<GuestConsumption> getAllConsumptionsByTenantId2(int tenantId);

    @Select("select * from guestconsumption")
    @Results(id = "allconsumption",value ={
            @Result(column = "tenant_id", property = "tenant",
                    one = @One(select = "org.csu.hotel.persistence.TenantMapper.selectById")),
            @Result(column = "commodity_id", property = "commodity",
                    one = @One(select = "org.csu.hotel.persistence.CommodityMapper.selectById"))})
    List<GuestConsumption> getAllConsumptions();

    @Insert("insert into guestconsumption values(#{id},#{tenantId},#{commodityId},#{quantity},#{date},#{price},#{stayId})")
    Boolean insertConsumption(int id,int tenantId, int commodityId, int quantity, Date date,double price,int stayId);
}

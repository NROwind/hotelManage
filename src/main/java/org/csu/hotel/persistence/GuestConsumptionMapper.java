package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.csu.hotel.domain.GuestConsumption;
import org.csu.hotel.domain.Stay;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestConsumptionMapper extends BaseMapper<GuestConsumption> {

    @Select("select tenant_id ,commodity_id,sum(quantity) as quantity,sum(price) as price from guestconsumption " +
            "where tenant_id = #{tenantId} and stay_id = #{stayId} group by commodity_id")
    @Results(id = "consumptionTenant",value ={
        @Result(column = "tenant_id", property = "tenant",
            one = @One(select = "org.csu.hotel.persistence.TenantMapper.selectById")),
            @Result(column = "commodity_id", property = "commodity",
                    one = @One(select = "org.csu.hotel.persistence.CommodityMapper.selectById"))}
    )
    List<GuestConsumption> getAllConsumptionsByTenantId(int tenantId,int stayId);
}

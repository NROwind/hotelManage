package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.hotel.domain.GuestConsumption;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestConsumptionMapper extends BaseMapper<GuestConsumption> {
}

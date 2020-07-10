package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.hotel.domain.Tenant;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantMapper extends BaseMapper<Tenant> {

}

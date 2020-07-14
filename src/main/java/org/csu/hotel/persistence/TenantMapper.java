package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.csu.hotel.domain.Tenant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantMapper extends BaseMapper<Tenant> {
    @Select("select * from tenant")
    List<Tenant> getAllTenant();
}

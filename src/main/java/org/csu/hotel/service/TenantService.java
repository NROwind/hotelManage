package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.Tenant;

import java.util.List;

public interface TenantService extends IService<Tenant> {
    public List<Tenant> getAllTenant();
}

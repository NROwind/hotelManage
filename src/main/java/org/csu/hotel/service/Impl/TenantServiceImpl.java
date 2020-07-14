package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.Tenant;
import org.csu.hotel.persistence.TenantMapper;
import org.csu.hotel.service.TenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {
    public List<Tenant> getAllTenant(){
        return this.baseMapper.getAllTenant();
    }
}

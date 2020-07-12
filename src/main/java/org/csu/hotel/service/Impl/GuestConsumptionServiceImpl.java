package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.GuestConsumption;
import org.csu.hotel.persistence.GuestConsumptionMapper;
import org.csu.hotel.service.GuestConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GuestConsumptionServiceImpl extends ServiceImpl<GuestConsumptionMapper, GuestConsumption> implements GuestConsumptionService {

    @Autowired
    private GuestConsumptionMapper guestConsumptionMapper;

    public List<GuestConsumption> getAllConsumptionsByTenantId(int tenantId,int stayId){return guestConsumptionMapper.getAllConsumptionsByTenantId(tenantId,stayId);}
}

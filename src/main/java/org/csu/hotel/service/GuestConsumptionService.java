package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.GuestConsumption;

import java.util.List;

public interface GuestConsumptionService extends IService<GuestConsumption> {

    List<GuestConsumption> getAllConsumptionsByTenantId(int tenantId,int stayId);
}

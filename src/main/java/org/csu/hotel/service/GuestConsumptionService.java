package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.GuestConsumption;

import java.util.Date;
import java.util.List;

public interface GuestConsumptionService extends IService<GuestConsumption> {

    List<GuestConsumption> getAllConsumptionsByTenantId(int tenantId,int stayId);
    List<GuestConsumption> getAllConsumptionsByTenantId2(int tenantId);
    List<GuestConsumption> getAllConsumptions();
    Boolean insertConsumption(int id,int tenantId,int commodityId, int quantity, Date date, double price, int stayId);

}

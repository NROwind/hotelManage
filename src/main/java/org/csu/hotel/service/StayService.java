package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.Stay;

import java.util.List;

public interface StayService extends IService<Stay> {

    public List<Stay> getAllStays();

    public List<Stay> getStaysByRoomId(int roomId);

    public List<Stay> getStaysByTenantId(int tenantId);
}

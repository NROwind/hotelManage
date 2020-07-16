package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.Stay;
import org.csu.hotel.domain.Tenant;

import java.util.List;

public interface StayService extends IService<Stay> {

    public List<Stay> getAllStays();

    public List<Stay> getStaysByRoomId(int roomId);

    public List<Stay> getStaysByTenantId(int tenantId);

    public int insertStay(Stay stay, int tenantId);

    int updateStay(Stay stay);
}

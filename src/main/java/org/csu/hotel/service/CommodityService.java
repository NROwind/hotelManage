package org.csu.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.csu.hotel.domain.Commodity;

import java.util.List;

public interface CommodityService extends IService<Commodity> {
    public List<Commodity> getAllCommodities();

}

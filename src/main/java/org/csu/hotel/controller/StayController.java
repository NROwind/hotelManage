package org.csu.hotel.controller;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Stay;
import org.csu.hotel.service.StayService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StayController {
    
    @Autowired
    private StayService stayService;
    
    @GetMapping("stay")
    @SysLog("获取入住信息")
    public LayerData<Stay> getStayList() {
        LayerData<Stay> layerData = new LayerData<>();
        List<Stay> Stays= stayService.getAllStays();
        layerData.setData(Stays);
        layerData.setCode(200);
        layerData.setMsg("获取入住信息成功");
        return layerData;
    }

    @CacheEvict(value = "stay", allEntries=true)
    @PutMapping("stay")
    @SysLog("修改入住信息")
    public RestResponse updateStay(@RequestBody Stay Stay){
        String name=String.valueOf(Stay.getStayId());
        if(!StringUtils.isNoneBlank(name)){
            System.out.println("失败");
            return RestResponse.failure("修改入住信息失败");
        }
        stayService.updateStay(Stay);
        System.out.println("成功");
        return RestResponse.success("修改入住信息成功");
    }

    @CacheEvict(value = "stay", allEntries=true)
    @PostMapping("stay")
    @SysLog("新增入住信息")
    public RestResponse insertStay(@RequestBody Stay stay){
        int tenantId = stay.getTenant().getId();
        String name=String.valueOf(stay.getStayId());
        List<Stay> stays = stayService.getStaysByRoomId(stay.getRoomId());
        for (int i = 0;i<stays.size();i++){
            if (stays.get(i).getInStay()!=1 && stay.getStayStartTime().before(stays.get(i).getStayEndTime()) && stay.getStayStartTime().after(stays.get(i).getStayStartTime())){
                System.out.println(stay.getStayStartTime() + "在");
                System.out.println(stays.get(i).getStayEndTime() + "之前");

                return RestResponse.failure("时间冲突，新增入住信息失败");
            } else if (stay.getStayEndTime().after(stays.get(i).getStayStartTime())&&stay.getStayEndTime().before(stays.get(i).getStayEndTime())){
                System.out.println(stay.getStayEndTime() + "在");
                System.out.println(stays.get(i).getStayStartTime() + "之后");
                return RestResponse.failure("时间冲突，新增入住信息失败");
            }
        }
        if(!StringUtils.isNoneBlank(name)){
            return RestResponse.failure("新增入住信息失败");
        }
        stayService.insertStay(stay,tenantId);
        return RestResponse.success("新增入住信息成功");
    }
    
}

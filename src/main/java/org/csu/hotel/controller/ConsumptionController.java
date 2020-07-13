package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Update;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Commodity;
import org.csu.hotel.domain.GuestConsumption;
import org.csu.hotel.domain.Room;
import org.csu.hotel.domain.Stay;
import org.csu.hotel.service.CommodityService;
import org.csu.hotel.service.GuestConsumptionService;
import org.csu.hotel.service.StayService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ConsumptionController {
    @Autowired
    private GuestConsumptionService guestConsumptionService;
    @Autowired
    private CommodityService commodityService;
    @GetMapping("consumption")
    @SysLog("查看用户消费情况")
    public LayerData<GuestConsumption> getConsumptionForm(@RequestParam int tenantId){
        LayerData<GuestConsumption>layerData =new LayerData<>();
        List<GuestConsumption> guestConsumptionList = guestConsumptionService.getAllConsumptionsByTenantId2(tenantId);

        layerData.setData(guestConsumptionList);
        layerData.setCode(200);
        layerData.setMsg("欧克");

        return layerData;
    }

    @GetMapping("consumptions")
    @SysLog("查看所有消费记录")
    public LayerData<GuestConsumption> getConsumptions(){
        LayerData<GuestConsumption>layerData =new LayerData<>();
        List<GuestConsumption> guestConsumptionList = guestConsumptionService.getAllConsumptions();

        layerData.setData(guestConsumptionList);
        layerData.setCode(200);
        layerData.setMsg("欧克");

        return layerData;
    }

    @PutMapping("consumption")
    @SysLog("更新消费记录")
    public RestResponse updateConsumption(@RequestParam Map<String,String>map){
        int consumptionId=Integer.parseInt(map.get("consumption_id"));
        int tenantId = Integer.parseInt(map.get("tenant_id"));
        int commodityId = Integer.parseInt(map.get("commodity_id"));
        int quantity = Integer.parseInt(map.get("quantity"));
        int stayId = Integer.parseInt(map.get("stay_id"));

        Commodity commodity=commodityService.getById(commodityId);
        double price = quantity*commodity.getPrice();
        System.out.println(price);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(map.get("date"));

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        UpdateWrapper<GuestConsumption> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("stay_id",stayId);
        updateWrapper.set("quantity",quantity);
        updateWrapper.set("price",price);
        updateWrapper.eq("consumption_id",commodityId);

        if (tenantId==0 || !guestConsumptionService.update(updateWrapper)) {
            return RestResponse.failure("更新消费记录失败");
        }
        return RestResponse.success("更新消费记录成功");

    }

    @PostMapping("consumption")
    @SysLog("新增消费记录")
    public RestResponse insertConsumption(@RequestParam Map<String,String>map){
        int consumptionId= StringUtils.isNoneBlank(map.get("consumption_id"))?Integer.parseInt(map.get("consumption_id")):0;
        int tenantId = Integer.parseInt(map.get("tenant_id"));
        int commodityId = Integer.parseInt(map.get("commodity_id"));
        int quantity = Integer.parseInt(map.get("quantity"));
        int stayId = Integer.parseInt(map.get("stay_id"));
        Commodity commodity=commodityService.getById(commodityId);
        double price = quantity*commodity.getPrice();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(map.get("date"));

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(tenantId==0||!guestConsumptionService.insertConsumption(consumptionId,tenantId,commodityId,quantity,date,price,stayId)){
            return RestResponse.failure("新增消费记录失败");
        }
        else
            return RestResponse.success("新增消费记录成功");

    }
    @DeleteMapping("consumption")
    @SysLog("删除消费记录")
    public RestResponse deleteConsumption(@RequestParam int id){
        QueryWrapper<GuestConsumption> queryWrapper = new QueryWrapper<>();
        if (id != 0) {
            queryWrapper.eq("consumption_id", id);
        } else
            return RestResponse.failure("删除消费记录失败");
        if (!guestConsumptionService.remove(queryWrapper)) {
            return RestResponse.failure("删除消费记录失败");
        }
        return RestResponse.success("删除消费记录成功");
    }


}

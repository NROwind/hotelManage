package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Update;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.*;
import org.csu.hotel.service.CommodityService;
import org.csu.hotel.service.GuestConsumptionService;
import org.csu.hotel.service.StayService;
import org.csu.hotel.service.TenantService;
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
    @Autowired
    private TenantService tenantService;
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
    public LayerData<GuestConsumption> getConsumptions(@RequestParam(value="page",defaultValue = "1")Integer page,
                                                       @RequestParam(value="limit",defaultValue = "5")Integer limit,
                                                       @RequestParam Map<String,String>map){
        LayerData<GuestConsumption> layerData = new LayerData<>();
        //所有查询
        if(map.size()<=0) {
            Page<GuestConsumption> guestConsumptionPage = new Page<>(page, limit);
            List<GuestConsumption> guestConsumptionList = guestConsumptionService.getAllConsumptions();
            for(GuestConsumption guestConsumption:guestConsumptionList){
                System.out.println(guestConsumption.getDate());
            }
            guestConsumptionPage.setRecords(guestConsumptionList);
            layerData.setData(guestConsumptionPage.getRecords());
            layerData.setCount(guestConsumptionList.size());
            layerData.setCode(200);
            layerData.setMsg("欧克");
            return layerData;
        }
        //模糊查询
        int commodityId = StringUtils.isNoneBlank(map.get("commodityId")) ? Integer.parseInt(map.get("commodityId")) : 0;
        int tenantId = StringUtils.isNoneBlank(map.get("tenantId")) ? Integer.parseInt(map.get("tenantId")) : 0;
        int consumptionId=StringUtils.isNoneBlank(map.get("consumptionId")) ? Integer.parseInt(map.get("consumptionId")) : 0;
        String date=map.get("date");
        QueryWrapper<GuestConsumption> queryWrapper = new QueryWrapper<>();
        if (consumptionId != 0) {
            queryWrapper.like("consumption_id", consumptionId);
        }
        if (tenantId != 0) {
            queryWrapper.like("tenant_id", tenantId);
        }
        if (commodityId!=0) {
            queryWrapper.like("commodity_id", commodityId);
        }
        if (StringUtils.isNoneBlank(date)) {
            queryWrapper.like("date", date);
        }
        List<GuestConsumption> guestConsumptions = guestConsumptionService.list(queryWrapper);
        Page<GuestConsumption> consumptionPage = (Page<GuestConsumption>) guestConsumptionService.page(new Page<>(page, limit), queryWrapper);
        for(GuestConsumption guestConsumption:guestConsumptions){
            guestConsumption.setCommodity(commodityService.getById(guestConsumption.getCommodityId()));
            guestConsumption.setTenant(tenantService.getById(guestConsumption.getTenantId()));
        }
        consumptionPage.setRecords(guestConsumptions);
        layerData.setData(consumptionPage.getRecords());
        layerData.setCount((int) consumptionPage.getTotal());
        layerData.setCode(200);
        layerData.setMsg("欧克");

        return layerData;
    }

    @GetMapping("consumption/month")
    @SysLog("一月内商品销量")
    public LayerData<Map> getMonthSalesVolume(@RequestParam(value="page",defaultValue = "1")Integer page,
                                         @RequestParam(value="limit",defaultValue = "5")Integer limit)
    {
        LayerData<Map>layerData =new LayerData<>();
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        List<GuestConsumption> guestConsumptionList = guestConsumptionService.getMonthConsumptions(date);
        List<Map> returnList=new ArrayList<>();
        HashSet<Commodity> commodityList=new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        Page<Map> consumptionPage = new Page<>(page,limit);
        for(GuestConsumption guestConsumption:guestConsumptionList){
            Commodity commodity=guestConsumption.getCommodity();
            String commodityName=commodity.getName();
            int quantity=guestConsumption.getQuantity();
            if(commodityList.add(commodity)){
                map.put(commodityName,quantity);
            }
            else
                map.put(commodityName,map.get(commodityName)+quantity);
        }
        for(Commodity commodity:commodityList){
            Map<String, Object> weekCommodity = new HashMap<>();
            String commodityName=commodity.getName();
            weekCommodity.put("commodityName",commodityName);
            weekCommodity.put("salesVolume",map.get(commodityName));
            returnList.add(weekCommodity);
        }
        consumptionPage.setRecords(returnList);
        layerData.setData(consumptionPage.getRecords());
        layerData.setCount((int)returnList.size());
        layerData.setCode(200);
        layerData.setMsg("欧克");
        return layerData;
    }
    @GetMapping("consumption/week")
    @SysLog("一周消费商品销量最高的三个")
    public LayerData<Map> getWeekSalesVolume(@RequestParam(value="page",defaultValue = "1")Integer page,
                                            @RequestParam(value="limit",defaultValue = "5")Integer limit)
    {
        LayerData<Map>layerData =new LayerData<>();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        List<GuestConsumption> guestConsumptionList = guestConsumptionService.getWeekConsumptions(date);
        List<Map> returnList=new ArrayList<>();
        HashSet<Commodity> commodityList=new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        Page<Map> consumptionPage = new Page<>(page,limit);
        int maxquantity=0;
        //找到当日所有并去重
        for(GuestConsumption guestConsumption:guestConsumptionList){
            Commodity commodity=guestConsumption.getCommodity();
            String commodityName=commodity.getName();
            int quantity=guestConsumption.getQuantity();
            if(commodityList.add(commodity)){
                map.put(commodityName,quantity);
            }
            else
                map.put(commodityName,map.get(commodityName)+quantity);
        }
        //找到当日最高并返回
        List<Map.Entry<String,Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));//升序
        for(int i=0;i<Math.min(3,list.size());i++){
            Map<String,String>finalmap=new HashMap<>();
            finalmap.put("salesVolume", String.valueOf(list.get(i).getValue()));
            finalmap.put("commodityName",list.get(i).getKey());
            returnList.add(finalmap);
        }


        consumptionPage.setRecords(returnList);
        layerData.setData(consumptionPage.getRecords());
        layerData.setCount((int)returnList.size());
        layerData.setCode(200);
        layerData.setMsg("欧克");
        return layerData;
    }
    @GetMapping("consumption/day")
    @SysLog("当日最高销量商品")
    public LayerData<Map> getDaySalesVolume(@RequestParam(value="page",defaultValue = "1")Integer page,
                                             @RequestParam(value="limit",defaultValue = "5")Integer limit)
    {
        LayerData<Map>layerData =new LayerData<>();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        List<GuestConsumption> guestConsumptionList = guestConsumptionService.getDayConsumptions(date);
        List<Map> returnList=new ArrayList<>();
        HashSet<Commodity> commodityList=new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        Page<Map> consumptionPage = new Page<>(page,limit);
        int maxquantity=0;
        //找到当日所有并去重
        for(GuestConsumption guestConsumption:guestConsumptionList){
            Commodity commodity=guestConsumption.getCommodity();
            String commodityName=commodity.getName();
            int quantity=guestConsumption.getQuantity();
            if(commodityList.add(commodity)){
                map.put(commodityName,quantity);
            }
            else
                map.put(commodityName,map.get(commodityName)+quantity);
        }
        //找到当日最高并返回
        List<Map.Entry<String,Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));//升序
        if(list.size()<=0){
            layerData.setCode(400);
            layerData.setMsg("无当日商品");
            return layerData;
        }
        Map<String,String>finalmap=new HashMap<>();
        finalmap.put("salesVolume", String.valueOf(list.get(0).getValue()));
        finalmap.put("commodityName",list.get(0).getKey());

        returnList.add(finalmap);
        consumptionPage.setRecords(returnList);
        layerData.setData(consumptionPage.getRecords());
        layerData.setCount((int)returnList.size());
        layerData.setCode(200);
        layerData.setMsg("欧克");
        return layerData;
    }
    @PutMapping("consumption")
    @SysLog("更新消费记录")
    public RestResponse updateConsumption(@RequestParam Map<String,String>map){
        int consumptionId=Integer.parseInt(map.get("consumptionId"));
        int tenantId = Integer.parseInt(map.get("tenantId"));
        int commodityId = Integer.parseInt(map.get("commodityId"));
        int quantity = Integer.parseInt(map.get("quantity"));
        int stayId = Integer.parseInt(map.get("stayId"));

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
        updateWrapper.set("tenant_id",tenantId);
        updateWrapper.set("commodity_id",commodityId);
        updateWrapper.set("stay_id",stayId);
        updateWrapper.set("quantity",quantity);
        updateWrapper.set("date",date);
        updateWrapper.set("price",price);
        updateWrapper.eq("consumption_id",consumptionId);

        if (tenantId==0 || !guestConsumptionService.update(updateWrapper)) {
            return RestResponse.failure("更新消费记录失败");
        }
        return RestResponse.success("更新消费记录成功");

    }

    @PostMapping("consumption")
    @SysLog("新增消费记录")
    public RestResponse insertConsumption(@RequestParam Map<String,String>map){
        int tenantId = Integer.parseInt(map.get("tenantId"));
        int commodityId = Integer.parseInt(map.get("commodityId"));
        int quantity = Integer.parseInt(map.get("quantity"));
        int stayId = Integer.parseInt(map.get("stayId"));
        Commodity commodity=commodityService.getById(commodityId);
        double price = quantity*commodity.getPrice();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(map.get("date"));

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(tenantId==0||!guestConsumptionService.insertConsumption(tenantId,commodityId,quantity,date,price,stayId)){
            return RestResponse.failure("新增消费记录失败");
        }
        else
            return RestResponse.success("新增消费记录成功");

    }
    @DeleteMapping("consumption")
    @SysLog("删除消费记录")
    public RestResponse deleteConsumption(@RequestParam("consumtionIds[]") List<Integer> consumptionIds) {
            if (consumptionIds.size() <= 0)
                return RestResponse.failure("删除消费记录失败");
            if (!guestConsumptionService.removeByIds(consumptionIds)) {
                return RestResponse.failure("删除消费记录失败");
            }
            return RestResponse.success("删除消费记录成功");
        }


    }



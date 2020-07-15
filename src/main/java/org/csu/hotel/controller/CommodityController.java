package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Commodity;
import org.csu.hotel.domain.Log;
import org.csu.hotel.persistence.CommodityMapper;
import org.csu.hotel.service.CommodityService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    //单一查询和修改
    @GetMapping("commodity")
    @SysLog("获取商品")
    public LayerData<Commodity> getCommodity(@RequestParam(value="name") String name){
        LayerData<Commodity> layerData = new LayerData<>();
        QueryWrapper<Commodity>queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNoneBlank(name)){
            queryWrapper.eq("name",name);
        }
        List<Commodity> commodities= commodityService.list(queryWrapper);
        layerData.setData(commodities);
        layerData.setCode(200);
        layerData.setMsg("获取成功");
        return layerData;
    }
    @PutMapping("commodity")
    @SysLog("修改商品")
    public RestResponse updateCommodity(@RequestBody Commodity commodity){
        String name=commodity.getName();
        if(!StringUtils.isNoneBlank(name)||!commodityService.updateById(commodity)){
            return RestResponse.failure("修改商品失败");
        }
        return RestResponse.success("修改商品成功");
    }
    @PostMapping("commodity")
    @SysLog("新增商品")
    public RestResponse insertCommodity(@RequestBody Commodity commodity){
        String name=commodity.getName();
        if(!StringUtils.isNoneBlank(name)||!commodityService.save(commodity)){
            return RestResponse.failure("新增商品失败");
        }
        return RestResponse.success("新增商品成功");
    }
    @DeleteMapping("commodity")
    @SysLog("删除商品")
    public RestResponse deleteCommodity(@RequestParam(value="name") String name){
        QueryWrapper<Commodity>queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNoneBlank(name)){
            queryWrapper.eq("name",name);
        }
        else
            return RestResponse.failure("删除商品失败");

        if(!commodityService.remove(queryWrapper)){
            return RestResponse.failure("删除商品失败");
        }

        return RestResponse.success("删除商品成功");
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("commodities")
    @SysLog("获取商品id和name")
    public LayerData<Map> getAllCommodities(){
        LayerData<Map>layerData=new LayerData<>();
        List<Map> returnList=new ArrayList<>();
        List<Commodity> commodities= commodityService.getAllCommodities();
        for(Commodity commodity:commodities){
            Map<String,Object>map=new HashMap<>();
            map.put("commodityId",commodity.getId());
            map.put("commodityName",commodity.getName());
            returnList.add(map);
        }
        layerData.setData(returnList);
        layerData.setCode(200);
        layerData.setMsg("有货了");
        return layerData;
    }

}

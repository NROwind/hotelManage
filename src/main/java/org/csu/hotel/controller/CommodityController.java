package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.csu.hotel.domain.Commodity;
import org.csu.hotel.domain.Log;
import org.csu.hotel.persistence.CommodityMapper;
import org.csu.hotel.service.CommodityService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    //mybatisplus不是很熟悉,之后再改一下
    //单一查询和修改
    @GetMapping("commodity")
    public LayerData<Commodity> getCommodity(@RequestParam(value="name") String name){
        LayerData<Commodity> layerData = new LayerData<>();
        QueryWrapper<Commodity>queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNoneBlank(name)){
            queryWrapper.eq("name",name);
        }
        List<Commodity> commoditie= commodityService.list(queryWrapper);
        layerData.setData(commoditie);
        return layerData;
    }
    @PutMapping("commodity")
    public RestResponse updateCommodity(@RequestParam String name){
        QueryWrapper<Commodity>queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNoneBlank(name)){
            queryWrapper.eq("name",name);
        }
        UpdateWrapper<Commodity>updateWrapper=new UpdateWrapper<>();
//        updateWrapper.eq();
//        List<Commodity> commoditie= commodityService.update(queryWrapper);

        return RestResponse.success("成功");
    }
    @PostMapping("commodity")
    public RestResponse insertCommodity(@RequestParam Map<String,Object> params){
        return RestResponse.success("成功");
    }
    @DeleteMapping("commodity")
    public RestResponse deleteCommodity(@RequestParam String name){
        return RestResponse.success("成功");
    }
}

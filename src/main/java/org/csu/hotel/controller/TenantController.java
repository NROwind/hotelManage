package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Member;
import org.csu.hotel.domain.Tenant;
import org.csu.hotel.service.TenantService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TenantController {

    @Autowired
    private TenantService tenantService;
    //单一查询和修改
    @GetMapping("tenant")
    @SysLog("获取单一房客信息")
    public LayerData<Tenant> getTenant(@RequestParam(value="id") int id){
        LayerData<Tenant> layerData = new LayerData<>();
        QueryWrapper<Tenant> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNoneBlank(String.valueOf(id))){
            queryWrapper.eq("id",id);
        }
        List<Tenant> tenants= tenantService.list(queryWrapper);
        layerData.setData(tenants);
        layerData.setCode(200);
        layerData.setMsg("获取房客信息成功");
        return layerData;
    }

    @GetMapping("tenants")
    @SysLog("获取全部房客信息")
    public LayerData<Tenant> getTenantList() {
        LayerData<Tenant> layerData = new LayerData<>();
        List<Tenant> tenants= tenantService.getAllTenant();
        layerData.setData(tenants);
        layerData.setCode(200);
        layerData.setMsg("获取全部房客信息成功");
        return layerData;
    }

    @PutMapping("tenant")
    @SysLog("修改房客信息")
    public RestResponse updateTenant(@RequestBody Tenant tenant){
        String name=tenant.getName();
        if(!StringUtils.isNoneBlank(name)||!tenantService.updateById(tenant)){
            System.out.println("失败");
            return RestResponse.failure("修改房客信息失败");
        }
        System.out.println("成功");
        return RestResponse.success("修改房客信息成功");
    }

    @PostMapping("tenant")
    @SysLog("新增房客信息")
    public RestResponse insertTenant(@RequestBody Tenant tenant){
        String name=tenant.getName();
        if(!StringUtils.isNoneBlank(name)||!tenantService.save(tenant)){
            return RestResponse.failure("新增房客信息失败");
        }
        return RestResponse.success("新增房客信息成功");
    }

    @DeleteMapping("tenant")
    @SysLog("删除房客信息")
    public RestResponse deleteTenant(@RequestParam(value="id") int id){
        QueryWrapper<Tenant>queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNoneBlank(String.valueOf(id))){
            queryWrapper.eq("id",id);
        }
        else
            return RestResponse.failure("删除房客信息失败");
        if(!tenantService.remove(queryWrapper)){
            return RestResponse.failure("删除房客信息失败");
        }
        return RestResponse.success("删除房客信息成功");
    }
    
}

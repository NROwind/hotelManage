package org.csu.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.RoomType;
import org.csu.hotel.service.RoomTypeService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("baseinfo")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("RoomType")
    @SysLog("获取房间类型")
    public LayerData<RoomType> getRoomType(){
        LayerData<RoomType> layerData = new LayerData<>();
        List<RoomType> RoomType= roomTypeService.getAllroomType();
        layerData.setData(RoomType);
        layerData.setCode(200);
        layerData.setMsg("获取成功");
        return layerData;
    }

    @PostMapping("RoomType")
    @SysLog("加入房间类型")
    public RestResponse insertRoomType(@RequestBody RoomType roomType){
        String name=roomType.getName();
        if(!StringUtils.isNoneBlank(name)||!roomTypeService.save(roomType)){
            return RestResponse.failure("新增房间类型失败");
        }
        return RestResponse.success("新增房间类型成功");
    }

    @DeleteMapping("RoomType")
    @SysLog("删除房间类型")
    public RestResponse deleteRoomType(@RequestParam int id){
        QueryWrapper<RoomType> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNoneBlank(String.valueOf(id))){
            queryWrapper.eq("id",id);
        }
        else
            return RestResponse.failure("删除房间类型失败");
        if(!roomTypeService.remove(queryWrapper)){
            return RestResponse.failure("删除房间类型失败");
        }
        return RestResponse.success("删除房间类型成功");
    }

    @PutMapping("RoomType")
    @SysLog("修改房间类型")
    public RestResponse updateRoomTypeById(@RequestBody RoomType roomType){
        String name=roomType.getName();
        if(!StringUtils.isNoneBlank(name)||!roomTypeService.updateById(roomType)){
            System.out.println("失败");
            return RestResponse.failure("修改房间类型失败");
        }
        System.out.println("成功");
        return RestResponse.success("修改房间类型成功");
    }

}

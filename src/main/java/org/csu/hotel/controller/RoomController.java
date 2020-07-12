package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Commodity;
import org.csu.hotel.domain.Room;
import org.csu.hotel.domain.RoomType;
import org.csu.hotel.service.RoomService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping("room")
    public LayerData<Room> getCommodity(@RequestParam int id){
        LayerData<Room> layerData = new LayerData<>();
        List<Room> room =roomService.getRoomandRoomtype(id);

        layerData.setData(room);
        layerData.setCode(200);
        layerData.setMsg("获取成功");
        return layerData;
    }

    @PutMapping("room")
    public RestResponse updateCommodity(@RequestBody Room room){
        int id=room.getId();
        if(id!=0||!roomService.updateById(room)){
            return RestResponse.failure("修改房间失败");
        }
        return RestResponse.success("修改房间成功");
    }
    @PostMapping("room")
    public RestResponse insertCommodity(@RequestBody Room room){
        int id=room.getId();
        try{
            roomService.save(room);
        }catch (Exception io){
            io.getMessage();
        }
        if(id!=0||!roomService.save(room)){
            return RestResponse.failure("新增房间失败");
        }
        return RestResponse.success("新增房间成功");
    }
    @DeleteMapping("room")
    public RestResponse deleteCommodity(@RequestParam int id){
        QueryWrapper<Room>queryWrapper=new QueryWrapper<>();
        if(id!=0){
            queryWrapper.eq("room_id",id);
        }
        else
            return RestResponse.failure("删除房间失败");
        if(!roomService.remove(queryWrapper)){
            return RestResponse.failure("删除房间失败");
        }
        return RestResponse.success("删除房间成功");
    }
//    @GetMapping("roomtypes")


}

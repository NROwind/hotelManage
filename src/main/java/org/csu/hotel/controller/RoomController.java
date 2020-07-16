package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.*;
import org.csu.hotel.service.RoomService;
import org.csu.hotel.service.RoomTypeService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("room")
    @SysLog("查询房间")
    public LayerData<Room> getCommodity(@RequestParam int id) {
        LayerData<Room> layerData = new LayerData<>();
        List<Room> room = roomService.getRoomandRoomtype(id);

        layerData.setData(room);
        layerData.setCode(200);
        layerData.setMsg("获取成功");
        return layerData;
    }

    @PutMapping("room")
    @SysLog("更新房间信息")
    public RestResponse updateCommodity(@RequestParam Map<String, String> map) {
        int roomId = Integer.parseInt(map.get("roomId"));
        int floor = Integer.parseInt(map.get("floor"));
        int typeId = Integer.parseInt(map.get("typeId"));
        String status = map.get("status");
        UpdateWrapper<Room> updateWrapper = new UpdateWrapper<Room>();
        //修改语句
        updateWrapper.set("status", status);
        updateWrapper.set("floor", floor);
        updateWrapper.set("type_id", typeId);
        //条件
        updateWrapper.eq("room_id", roomId);

        if (roomId == 0 || !roomService.update(updateWrapper)) {
            return RestResponse.failure("更新房间失败");
        }
        return RestResponse.success("更新房间成功");
    }
    @PatchMapping("room")
    @SysLog("更新房间状态")
    public RestResponse updateCommodityStatus(@RequestParam Map<String, String> map) {
        int roomId = Integer.parseInt(map.get("roomId"));

        String status = map.get("status");
        UpdateWrapper<Room> updateWrapper = new UpdateWrapper<Room>();
        //修改语句
        updateWrapper.set("status", status);

        //条件
        updateWrapper.eq("room_id", roomId);

        if (roomId == 0 || !roomService.update(updateWrapper)) {
            return RestResponse.failure("更新房间状态失败");
        }
        return RestResponse.success("更新房间状态成功");
    }

    @PostMapping("room")
    @SysLog("新增房间")
    public RestResponse insertCommodity(@RequestParam Map<String, String> map) {
        int roomId = Integer.parseInt(map.get("roomId"));
        int floor = Integer.parseInt(map.get("floor"));
        int typeId = Integer.parseInt(map.get("typeId"));
        String status = map.get("status");
        Room room = new Room(roomId, floor, typeId, status);
        if (roomId == 0 || !roomService.save(room)) {
            return RestResponse.failure("新增房间失败");
        }
        return RestResponse.success("新增房间成功");
    }

    @DeleteMapping("room")
    @SysLog("删除房间")
    public RestResponse deleteCommodity(@RequestParam("roomIds[]") List<Integer> roomIds) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        if (roomIds.size()<=0)
            return RestResponse.failure("删除房间失败");

        if (!roomService.removeByIds(roomIds)) {
            return RestResponse.failure("删除房间失败");
        }
        return RestResponse.success("删除房间成功");
    }

    @GetMapping("rooms")
    public LayerData<Room> getLikelyRooms(@RequestParam(value="page",defaultValue = "1")Integer page,
                                          @RequestParam(value="limit",defaultValue = "5")Integer limit,
            @RequestParam Map<String, String> map) {
        if(map.size()<=0){
            List<Room> roomList = roomService.getAllRooms();
            LayerData<Room>layerData=new LayerData<>();

            Page<Room> roomPage = new Page<>(page,limit);
            //@TODO
            //改动
            roomPage.setRecords(roomList);
            layerData.setData(roomPage.getRecords());
            layerData.setCount((int)roomList.size());
            layerData.setMsg("成了");
            layerData.setCode(200);
            return layerData;
        }
        LayerData<Room> layerData = new LayerData<>();

        String roomSId = map.get("roomId");
        int roomId =StringUtils.isNoneBlank(map.get("roomId"))?Integer.parseInt(map.get("roomId")):0 ;
        int floor = StringUtils.isNoneBlank(map.get("floor")) ? Integer.parseInt(map.get("floor")) : 0;
        String typeName = map.get("typeName");
        String status = map.get("status");

        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        if (roomId != 0) {
            queryWrapper.like("room_id", roomId);
        }
        if (floor != 0) {
            queryWrapper.like("floor", floor);
        }
        if (StringUtils.isNoneBlank(status)) {
            queryWrapper.like("status", status);
        }

        List<Room> rooms = roomService.list(queryWrapper);
        Page<Room> roomPage=(Page<Room>)roomService.page(new Page<>(page,limit),queryWrapper);
        Iterator<Room> iterator = rooms.iterator();
            while (iterator.hasNext()) {
                Room room = iterator.next();
                System.out.println(room.getRoomId());
                RoomType roomType = roomTypeService.getById(room.getTypeId());
                if(StringUtils.isNoneBlank(typeName))
                    if (!roomType.getName().contains(typeName)) {
                        System.out.println(room.getRoomId() + roomType.getName());
                        iterator.remove();
                        continue;
                    }
                room.setRoomType(roomType);

            }
        roomPage.setRecords(rooms);
        layerData.setData(roomPage.getRecords());
        layerData.setCount((int)roomPage.getTotal());
        layerData.setCode(200);
        return layerData;

    }


    @Cacheable(key = "'room_types'",value = "roomType")
    @GetMapping("roomtypes")
    @SysLog("获取所有房间类型")
    public LayerData<Map> getAllRoomTypes() {

        List<RoomType> roomTypeList = roomTypeService.getAllroomType();
        LayerData<Map> layerData=new LayerData<>();
        List<Map> returnList=new ArrayList<>();
        for(RoomType roomType:roomTypeList){
            Map<String,Object> map=new HashMap<>();
            map.put("typeName",roomType.getName());
            map.put("typeId",roomType.getId());
            returnList.add(map);
        }
        layerData.setData(returnList);
        layerData.setCode(200);
        layerData.setMsg("获取成功");

        return layerData;

    }
}




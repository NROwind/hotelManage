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
    public LayerData<Room> getCommodity(@RequestParam int id) {
        LayerData<Room> layerData = new LayerData<>();
        List<Room> room = roomService.getRoomandRoomtype(id);

        layerData.setData(room);
        layerData.setCode(200);
        layerData.setMsg("获取成功");
        return layerData;
    }

    @PutMapping("room")
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

    @PostMapping("room")
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
    public RestResponse deleteCommodity(@RequestParam int id) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        if (id != 0) {
            queryWrapper.eq("room_id", id);
        } else
            return RestResponse.failure("删除房间失败");
        if (!roomService.remove(queryWrapper)) {
            return RestResponse.failure("删除房间失败");
        }
        return RestResponse.success("删除房间成功");
    }

    @GetMapping("room/query")
    public LayerData<Room> getLikelyRooms(@RequestParam(value="page",defaultValue = "1")Integer page,
                                          @RequestParam(value="limit",defaultValue = "5")Integer limit,
            @RequestParam Map<String, String> map) {
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

        System.out.println(rooms);
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            Room room = iterator.next();
            System.out.println(room.getRoomId());
            RoomType roomType = roomTypeService.getById(room.getTypeId());
            if (!roomType.getName().contains(typeName)) {
                System.out.println(room.getRoomId() + roomType.getName());
                if (StringUtils.isNoneBlank(typeName)) {
                    iterator.remove();
                }
                continue;
            } else {
                room.setRoomType(roomType);
            }

        }
        roomPage.setRecords(rooms);
        System.out.println(roomPage.getRecords());
        System.out.println(roomPage.getTotal());
        layerData.setData(roomPage.getRecords());
        layerData.setCount((int)roomPage.getTotal());
        layerData.setCode(200);
        return layerData;

    }


    @GetMapping("rooms")
    public LayerData<Map> getAllRooms(@RequestParam(value="page",defaultValue = "1")Integer page,
                                    @RequestParam(value="limit",defaultValue = "10")Integer limit) {

        List<Room> roomList = roomService.getAllRooms();
        LayerData<Map>layerData=new LayerData<>();
        List<Map> mapList = new ArrayList<>();

        for (Room r : roomList) {
            Map<String, Object> map = new HashMap<>();

            map.put("roomId", r.getRoomId());
            map.put("floor", r.getFloor());
            map.put("roomType", r.getRoomType().getName());
            map.put("price", r.getRoomType().getPrice());
            map.put("status", r.getStatus());
            mapList.add(map);
        }
        Page<Map> roomPage = new Page<>(page,limit);
        roomPage.setRecords(mapList);
        layerData.setData(roomPage.getRecords());
        layerData.setCount(mapList.size());
        layerData.setMsg("成了");
        layerData.setCode(200);
        return layerData;

    }


    @GetMapping("roomtypes")
    public LayerData<RoomType> getAllRoomTypes() {

        List<RoomType> roomTypeList = roomTypeService.getAllroomType();
        LayerData<RoomType> layerData=new LayerData<>();
        layerData.setData(roomTypeList);
        layerData.setCode(200);
        layerData.setMsg("获取成功");
        return layerData;

    }
}




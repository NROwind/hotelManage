package org.csu.hotel;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.csu.hotel.domain.Commodity;
import org.csu.hotel.domain.Room;
import org.csu.hotel.persistence.RoomMapper;
import org.csu.hotel.service.CommodityService;
import org.csu.hotel.service.GuestConsumptionService;
import org.csu.hotel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@SpringBootTest
class HotelApplicationTests {
    @Autowired
    private RoomService roomService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private GuestConsumptionService guestConsumptionService;
    @Test
    void contextLoads() {
    }
    @Test
    void mapperTest1(){
//         Room room=new Room(503,5,2,"Y");
//        System.out.println(roomMapper.getRoomandRoomtype(505));
//        System.out.println(commodityService.getAllCommodities());
//         System.out.println(roomService.updateById(room));
//        UpdateWrapper<Room> updateWrapper = new UpdateWrapper<Room>();
//        //修改语句
//        updateWrapper.set("status", "Y");
//        updateWrapper.set("floor", 5);
//        updateWrapper.set("type_id",1);
//        //条件
//        updateWrapper.eq("room_id", 503);
//        System.out.println(roomService.update(updateWrapper));
        List<Integer>Ids=Arrays.asList(8,9);
        System.out.println(guestConsumptionService.removeByIds(Ids));
    }

}

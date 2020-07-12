package org.csu.hotel;

import org.csu.hotel.domain.Room;
import org.csu.hotel.persistence.RoomMapper;
import org.csu.hotel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HotelApplicationTests {
    @Autowired
    private RoomMapper roomMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void mapperTest1(){

        System.out.println(roomMapper.getRoomandRoomtype(505));
    }
}

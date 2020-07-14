package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.Floor;
import org.csu.hotel.domain.Member;
import org.csu.hotel.persistence.FloorMapper;
import org.csu.hotel.persistence.MemeberMapper;
import org.csu.hotel.service.FloorService;
import org.csu.hotel.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor> implements FloorService {
    @Autowired
    private FloorMapper floorMapper;

    public List<Floor> getFloor() {
        return floorMapper.selectList(null);
    }

    public int updateFloor(Floor floor) {
        return floorMapper.updateById(floor);
    }
}

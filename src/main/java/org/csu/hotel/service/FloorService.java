package org.csu.hotel.service;

import org.csu.hotel.domain.Floor;
import org.csu.hotel.persistence.FloorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FloorService {

    public List<Floor> getFloor();

    public int updateFloor(Floor floor);

}

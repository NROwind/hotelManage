package org.csu.hotel.controller;

import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Floor;
import org.csu.hotel.domain.Member;
import org.csu.hotel.service.FloorService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("baseinfo")
public class FloorController {
    @Autowired
    private FloorService floorService;

    @GetMapping("floor")
    @SysLog("获取楼层信息")
    public LayerData<Floor> getFloorList() {
        LayerData<Floor> layerData = new LayerData<>();
        List<Floor> floor= floorService.getFloor();
        layerData.setData(floor);
        layerData.setCode(200);
        layerData.setMsg("获取楼层信息成功");
        return layerData;
    }

    @PutMapping("floor")
    @SysLog("编辑楼层信息")
    public RestResponse updateFloor(@RequestBody Floor floor) {
        int result = floorService.updateFloor(floor);
        System.out.println("result: " + result);
        if ( result == 1 ) {
            return RestResponse.success("编辑楼层信息成功");
        } else {
            return RestResponse.failure("编辑楼层信息失败");
        }
    }

}

package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.csu.hotel.domain.Finance;
import org.csu.hotel.service.FinancialService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("form/")
public class FormController {

    @Autowired
    private FinancialService financialService;

    @PostMapping("finance/day")
    public LayerData<Finance> getFormsByday(@RequestParam(value="page",defaultValue = "1")Integer page,
                                            @RequestParam(value="limit",defaultValue = "10")Integer limit,
                                            @RequestParam(required = false) String date){

        if(date == null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            date =  df.format(new Date());
        }

        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date",date);

        LayerData<Finance> layerData = new LayerData<>();

        Page<Finance> financialPage = (Page<Finance>) financialService.page(new Page<>(page,limit),queryWrapper);

        System.out.println(financialPage.getTotal());
        layerData.setCount((int) financialPage.getTotal());

        layerData.setCode(200);
        layerData.setMsg("获取成功");
        layerData.setData(financialPage.getRecords());

        return layerData;

    }


    @PostMapping("finance/month")
    public RestResponse getFormsByMonth(@RequestParam(value="page",defaultValue = "1")Integer page,
                                        @RequestParam(value="limit",defaultValue = "10")Integer limit,
                                        @RequestParam(required = false) String date){

        if(date == null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
            date =  df.format(new Date());
        }

        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("date",date);
        System.out.println(date);

        RestResponse restResponse = RestResponse.success("成功");


        List<Finance> financeList = financialService.list(queryWrapper);

        List<Finance> returnList = new ArrayList<>();

        //统计该月份每一天的财务情况
        int flag = 0;
        for(Finance f: financeList){
            for(Finance rf:returnList){

                if(rf.getDate().equals(f.getDate())){
                    flag = 1;
                    rf.setRoomPrice(rf.getRoomPrice() + f.getRoomPrice());
                    rf.setReplacePrice(rf.getReplacePrice() + f.getReplacePrice());
                    rf.setCommodityPrice(rf.getCommodityPrice() + f.getCommodityPrice());
                    rf.setTotalPrice(rf.getTotalPrice() + f.getTotalPrice());
                }

            }

            if(flag == 0){
                returnList.add(f);
            }

        }

        restResponse.setData(returnList);

        return restResponse;

    }

    @GetMapping("room")
    public RestResponse getAllRooms(){

        return null;

    }





}

package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.domain.Employee;
import org.csu.hotel.service.EmployeeService;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("system/employee")
    public RestResponse getAllEmployee(@RequestParam(required = false) String id,@RequestParam(required = false) String name){

        RestResponse restResponse = RestResponse.success();

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(id)){
            int i = Integer.parseInt(id);
            queryWrapper.eq("id",i);
        }

        if(StringUtils.isNotBlank(name)){
            queryWrapper.eq("name",name);
        }

        List<Employee> employeeList = employeeService.list(queryWrapper);

        restResponse.setData(employeeList);

        return restResponse;
    }

}

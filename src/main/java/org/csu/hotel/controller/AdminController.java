package org.csu.hotel.controller;

import org.csu.hotel.domain.Admin;
import org.csu.hotel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("session")
    public Map<String,Object> login(String username, String passswod){
        HashMap<String,Object> adminMap = new HashMap<>();
        adminMap.put("user_name",username);
        adminMap.put("pass_word",passswod);
        List<Admin> list =adminService.getAdmin(adminMap);

        Map<String,Object> map = new HashMap<>();

        if(list.size() == 1){
            //登陆成功
            System.out.println(username);
            map.put("message","成功");
            map.put("code",200);
            map.put("token","");
        }
        else{
            //登陆失败
            map.put("code", "0000");
            map.put("message", "认证失败");
        }

        return map;

    }


}

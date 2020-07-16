package org.csu.hotel.controller;

import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Admin;
import org.csu.hotel.service.AdminService;
import org.csu.hotel.util.RestResponse;
import org.csu.hotel.util.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminService adminService;

    @PostMapping("session")
    @SysLog("用户登录")
    public RestResponse login(@RequestParam String username, @RequestParam String password){
        HashMap<String,Object> adminMap = new HashMap<>();
        adminMap.put("user_name",username);
        adminMap.put("pass_word",password);
        System.out.println(username);
        System.out.println(password);
        List<Admin> list = (List<Admin>) adminService.listByMap(adminMap);

        RestResponse restResponse = new RestResponse();

        if(list.size() == 1){
            //登陆成功
            System.out.println(username);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(list.get(0).getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return RestResponse.success(token);

        }
        else{
            //登陆失败
            return RestResponse.failure("失败");
        }

    }

    @PostMapping("haha")
    public String haha(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "haha:"+userDetails.getUsername()+","+userDetails.getPassword();
    }


}

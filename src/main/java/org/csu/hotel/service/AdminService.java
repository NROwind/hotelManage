package org.csu.hotel.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.hotel.domain.Admin;
import org.csu.hotel.persistence.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //登录
    public List<Admin> getAdmin(HashMap<String,Object> map){
        return adminMapper.selectByMap(map);
    }



}

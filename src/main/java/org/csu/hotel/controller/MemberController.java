package org.csu.hotel.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Commodity;
import org.csu.hotel.domain.Member;
import org.csu.hotel.domain.RoomType;
import org.csu.hotel.service.MemberService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("baseinfo")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Cacheable(key = "'member_all'",value = "members")
    @GetMapping("members")
    @SysLog("获取会员信息")
    public String getMemberList() {
        LayerData<Member> layerData = new LayerData<>();
        List<Member> members= memberService.getMemberList();
        layerData.setData(members);
        layerData.setCode(200);
        layerData.setMsg("获取会员信息成功");
        return JSON.toJSONString(layerData);
    }

    @CacheEvict(value = "members", allEntries=true)
    @PostMapping("members")
    @SysLog("新增会员")
    public RestResponse insertMember(@RequestBody Member member){
        String name=member.getName();
        if(!StringUtils.isNoneBlank(name)||memberService.insertMember(member)==0){
            return RestResponse.failure("新增会员失败");
        }
        return RestResponse.success("新增会员成功");
    }

    @CacheEvict(value = "members", allEntries=true)
    @DeleteMapping("members")
    @SysLog("删除会员")
    public RestResponse deleteMember(@RequestParam int id){
        Member member = memberService.getMemberById(id);
        int result = memberService.deleteMember(member);
        System.out.println("result: " + result);
        if ( result == 1 ) {
            return RestResponse.success("删除会员成功");
        } else {
            return RestResponse.failure("删除会员失败");
        }
    }

    @CacheEvict(value = "members", allEntries=true)
    @PutMapping("members")
    @SysLog("修改会员信息")
    public RestResponse updateMember(@RequestBody Member member){
        String name=member.getName();
        if(!StringUtils.isNoneBlank(name)||memberService.updateMember(member) == 0){
            return RestResponse.failure("修改会员信息失败");
        }
        return RestResponse.success("修改会员信息成功");
    }

}

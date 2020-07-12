package org.csu.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.csu.hotel.domain.Log;
import org.csu.hotel.service.LogService;
import org.csu.hotel.util.LayerData;
import org.csu.hotel.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system/log/")
public class LogController {

    @Autowired
    private LogService logService;

    //获取日志列表
    @PostMapping("list")
    private LayerData<Log> list(@RequestParam(value="page",defaultValue = "1")Integer page,
                                @RequestParam(value="limit",defaultValue = "10")Integer limit,
                                HttpServletRequest request){
        // Type title username http_method
        Map map = WebUtils.getParametersStartingWith(request,"s_");
        LayerData<Log> layerData = new LayerData<>();
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();

//        String u = request.getParameter("username");
//        System.out.println(u);
//        queryWrapper.eq("user_name",u);

        if(!map.isEmpty()){
            String type = (String) map.get("type");
            if(StringUtils.isNoneBlank(type)){
                queryWrapper.eq("type",type);
            }

            String title = (String) map.get("title");
            if(StringUtils.isNoneBlank(title)){
                queryWrapper.like("title",title);
            }

            String username = (String) map.get("user_name");
            System.out.println(username);
            if(StringUtils.isNoneBlank(username)){
                queryWrapper.eq("user_name",username);
            }

            String httpMethod = (String) map.get("httpMethod");
            if(StringUtils.isNoneBlank(httpMethod)){
                queryWrapper.eq("http_method",httpMethod);
            }

        }

        Page<Log> logPage = (Page<Log>) logService.page(new Page<>(page,limit),queryWrapper);

        System.out.println(logPage.getTotal());
        layerData.setCount((int) logPage.getTotal());

        layerData.setCode(200);
        layerData.setMsg("获取成功");
        layerData.setData(logPage.getRecords());

        return layerData;
    }

    @PostMapping("delete")
    public RestResponse delete(@RequestParam("ids")List<Long> ids){
        if(ids == null || ids.size()==0){
            return RestResponse.failure("请选择要删除的记录");

        }
        logService.removeByIds(ids);
        return RestResponse.success();
    }

}

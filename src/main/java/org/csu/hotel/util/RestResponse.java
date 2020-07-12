package org.csu.hotel.util;

import java.util.HashMap;

//Response构造器
public class RestResponse extends HashMap<String,Object> {

    private String message;
    private Integer code;

    public static RestResponse success(){
        return success("成功");
    }

    public static RestResponse success(String msg){
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(200);
        restResponse.setSuccess(true);
        restResponse.setMessage(msg);
        return restResponse;
    }

    public static RestResponse failure(String msg){
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(0);
        restResponse.setSuccess(false);
        restResponse.setMessage(msg);
        return restResponse;
    }

    public RestResponse setSuccess(Boolean success){
        if(success != null ) put("success",success);
        return this;
    }

    public RestResponse setMessage(String message){
        if(message != null ) put("message",message);
        return this;
    }

    public RestResponse setCode(Object code){
        if(code != null ) put("code",code);
        return this;
    }

    public RestResponse setData(Object data){
        if(data != null ) put("data",data);
        return this;
    }

    public RestResponse setPage(Integer page){
        if(page != null ) put("page",page);
        return this;
    }

    public RestResponse setCurrentPage(Integer currentPage){
        if(currentPage != null ) put("currentPage",currentPage);
        return this;
    }

    public RestResponse setLimit(Integer limit){
        if(limit != null ) put("limit",limit);
        return this;
    }

    public RestResponse setTotal(Long total){
        if(total != null ) put("total",total);
        return this;
    }

    public RestResponse setAny(String key, Object value){
        if(key != null && value != null ) put(key,value);
        return this;
    }

}

package org.csu.hotel.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Date;

@Data
public class Log extends Model<Log> {
    private int id;
    private String userName;
    private Long useTime;
    private String createDate;
    private String info;
    private String type;//请求类型
    private String title;//日志标题
    private String requestUri;//请求URI
    private String httpMethod;//操作方式
    private String classMethod;//请求类型、方法
    private String params;//操作提交的数据
    private String sessionId;
    private String response;
    private String exception;//异常信息

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(long time) {
        this.useTime = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}

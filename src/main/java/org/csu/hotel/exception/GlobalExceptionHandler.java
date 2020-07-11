package org.csu.hotel.exception;

import com.alibaba.fastjson.JSONObject;
import org.apache.juli.logging.LogFactory;
import org.csu.hotel.domain.Log;
import org.csu.hotel.util.RestResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateEngineException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //private static final Log log = LogFactory.getLog();

    //捕获500异常
    @ExceptionHandler({HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            TemplateEngineException.class,
            SQLException.class})
    public void handleHttpMessageNotReadableException(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Exception e){

        RestResponse restResponse = RestResponse.failure(e.getMessage());

        try{
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(response));
            writer.flush();
            writer.close();
        }
        catch(IOException el){
            el.printStackTrace();
        }
    }
}

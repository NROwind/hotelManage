package org.csu.hotel.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.csu.hotel.annotation.SysLog;
import org.csu.hotel.domain.Admin;
import org.csu.hotel.domain.Log;
import org.csu.hotel.exception.GlobalExceptionHandler;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Order(5)
@Component
public class WebLogAspect {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    private Log sysLog = null;

    private static final org.mybatis.logging.Logger LOGGER = (Logger) LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("@annotation(org.csu.hotel.annotation.SysLog)")
    private void webLog(){
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        //接收请求，记录请求的内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = (HttpSession) attributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

        sysLog = new Log();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date =  df.format(new Date());
        sysLog.setCreateDate(date);
        System.out.println(date);

        sysLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        sysLog.setHttpMethod(request.getMethod());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for(int i=0;i<args.length;i++){
            Object o = args[i];
            if(o instanceof ServletRequest || (o instanceof ServletResponse) || o instanceof MultipartFile){
                args[i] = o.toString();
            }
        }
        String str = JSONObject.toJSONString(args);
        sysLog.setParams(str.length()>5000?JSONObject.toJSONString("请求参数过长，不予显示"):str);

        sysLog.setRequestUri(request.getRequestURI().toString());

        sysLog.setType(isAjaxRequest(request)?"AJax请求":"普通请求");
        System.out.println(sysLog.getType());
        if(session != null){
            sysLog.setSessionId(session.getId());
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog mylog = method.getAnnotation(org.csu.hotel.annotation.SysLog.class);
        if(mylog != null ){
            sysLog.setTitle(mylog.value());
        }

        //Ajax请求
        //sysLog.setType();

        //获取用户名称
        // sysLog.setUserName(((Admin) session.getAttribute("admin")).getUserName());
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = (HttpSession) attributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

       Admin admin =  ((Admin) session.getAttribute("admin"));
        if(admin != null){
           // sysLog.setUserName(admin.getUserName());
        }
        String retString = JSONObject.toJSONString(ret);
        sysLog.setResponse(retString.length()>5000?JSONObject.toJSONString("返回参数过长，不予显示"):retString);
        sysLog.setUseTime(System.currentTimeMillis() - startTime.get());

        sysLog.insert();
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        try{

            Object obj = proceedingJoinPoint.proceed();
            return obj;
        }catch(Exception e){

            e.printStackTrace();
            sysLog.setException(e.getMessage());
            throw e;
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }

}

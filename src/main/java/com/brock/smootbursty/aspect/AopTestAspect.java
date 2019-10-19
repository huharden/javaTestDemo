package com.brock.smootbursty.aspect;


import com.brock.smootbursty.anno.AopTest;
import com.brock.smootbursty.model.AopTestVO;
import com.brock.smootbursty.utils.HttpContextUtils;
import com.brock.smootbursty.utils.IPUtils;
import com.brock.smootbursty.utils.JsonUtil;
import org.apache.catalina.session.StandardSessionFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class AopTestAspect {


    @Pointcut("@annotation(com.brock.smootbursty.anno.AopTest)")
    public void aopPointCut() {

    }

    @Around("aopPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {

     /*   if (rabbitTemplate == null) {
            return;
        }*/

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        AopTestVO aopTestVO = new AopTestVO();
        AopTest aopTest = method.getAnnotation(AopTest.class);
        if (aopTestVO != null) {
            //注解上的描述
            aopTestVO.setOperation(aopTest.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        aopTestVO.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] parameter = joinPoint.getArgs();
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        HashMap<String, Object> paramMap = new HashMap<>(16);
        for (int i = 0; i < parameter.length; i++) {
            if (!(parameter[i] instanceof ServletRequest) && !(parameter[i] instanceof StandardSessionFacade)) {
                paramMap.put(paramNames[i], parameter[i]);
            }
        }
        if (paramMap.size() > 0) {
            aopTestVO.setParams(JsonUtil.toJson(paramMap));
        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        aopTestVO.setIp(IPUtils.getIpAddr(request));

        aopTestVO.setUsername(aopTestVO.getIp());

        aopTestVO.setTime(time);
        // 发送MQ保存系统日志
//        rabbitTemplate.convertAndSend(RabbitQueueConstant.SYS_LOG_QUEUE, JsonUtil.toJson(sysLog));
    }
}

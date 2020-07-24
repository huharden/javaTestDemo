package com.hj.study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @projectName：data-platform
 * @className：SysSdkAspect
 * @describe：切面
 * @createTime：2020/7/24 9:25 上午
 * @author：HuTao
 */
@Aspect
@Component
public class SysSdkAspect {

    @Pointcut("@annotation(com.hj.study.anno.SysSdk)")
    public void sysSdkPointCut(){

    }

    @Around("sysSdkPointCut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        try {
            Object[] parameters = sysSdk(point);
            //执行方法
            result = point.proceed(parameters);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }


    public Object[] sysSdk(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取入参
       Object[] parameters = point.getArgs();
       //获取入参对象名
       String[] parameterNames = methodSignature.getParameterNames();

       //修改入参方法
       /*for(Object parameter: parameters){
           DpBaseInfo dpBaseInfo = new DpBaseInfo();
           if(parameter instanceof DpBaseInfo){
               dpBaseInfo = (DpBaseInfo) parameter;
               dpBaseInfo.setBaseName("切面测试");
           }
       }*/
       //方法一
       //当入参对象为父类对象，即不确定实体类型
        /*for(Object parameter: parameters){
            Class<?> clazz = parameter.getClass();
            //获取父类节点属性
            Class<?> superClazz = clazz.getSuperclass();
            Field[] declaredFields = superClazz.getDeclaredFields();
            for(Field field: declaredFields){
                //成员变量为private,故必须进行此操做
                field.setAccessible(true);
                //修改前的值
                Object fieldValue = field.get(parameter);
                String fieldName = field.getName();
                //修改后的值
                String afterValue = "100";
                if(fieldValue !=null && ("aspectName").equals(fieldName)){
                    field.set(parameter, afterValue);
                    break;
                }
            }
        }*/
        //方法二
        //通过获取父类
        for(Object parameter: parameters){
            Map<String, Method> methodMap = new HashMap<>();
            Class<?> clazz = parameter.getClass();
            Class<?> superClazz = clazz.getSuperclass();
            if(superClazz != null){
                Method[] superMethod = superClazz.getMethods();
                for(Method method: superMethod){
                    methodMap.put(method.getName(), method);
                }
            }
            Object value = null;
            String fieldName = "aspectName";
            String firstLetter = fieldName.substring(0,1).toUpperCase();

             //给参数赋值
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
            Method setMethod = methodMap.get(setMethodName);
            if(setMethod != null){
               setMethod.invoke(parameter, "110");
            }
            System.out.println("修改参数===>>>");
            //获取参数值
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            Method method = methodMap.get(getMethodName);
            if(method != null){
                value = method.invoke(parameter);
            }
            System.out.println("入参===>>>" + value);

        }

       //method.invoke();
        System.out.println("测试===>>>");
        return parameters;

    }



}

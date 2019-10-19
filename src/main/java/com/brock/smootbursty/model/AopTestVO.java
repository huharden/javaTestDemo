package com.brock.smootbursty.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * sys_log 实体类
 *
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2018/05/22 14:50
 */
@Data
@Accessors(chain = true)
public class AopTestVO implements Serializable {
    private Long id;

    /**
     * 用户名 username
     **/
    private String username;

    /**
     * 用户操作 operation
     **/
    private String operation;

    /**
     * 请求方法 method
     **/
    private String method;

    /**
     * 执行时长(毫秒) time
     **/
    private Long time;

    /**
     * IP地址 ip
     **/
    private String ip;

    /**
     * 请求参数 params
     **/
    private String params;

    private static final long serialVersionUID = 1L;
}

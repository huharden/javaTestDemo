package com.hj.study.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
* @Author: Brock.lee
* @Date: 2019/5/15
**/
@Data
@Accessors(chain = true)
@Table(name = "sys_log")
public class SysLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 用户操作
     */
    @Column(name = "operation")
    private String operation;

    /**
     * 请求方法
     */
    @Column(name = "method")
    private String method;

    /**
     * 请求参数
     */
    @Column(name = "params")
    private String params;

    /**
     * 执行时长(毫秒)
     */
    @Column(name = "time")
    private Long time;

    /**
     * IP地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 状态 状态 0正常 1禁用
     */
/*    @Column(name = "status")
    @ApiModelProperty(value = "状态 状态 0正常 1禁用", name = "status")
    private Integer status;*/
    /**
     * 创建时间s
     */
    @Column(name = "create_time")
    private Date createTime;

}

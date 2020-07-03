package com.hj.study.entity;

import lombok.Data;

import java.io.Serializable;


/**
* @Author: Brock.lee
* @Date: 2019/5/15
**/
@Data
public class MccXSelectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */

    private String FIELD;

    /**
     * 用户名
     */
    private String PROCESSING_ID;

    /**
     * 用户操作
     */
    private String TYPE;

    /**
     * 请求方法
     */
    private String mblNo;


}

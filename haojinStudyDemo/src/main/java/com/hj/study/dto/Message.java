package com.hj.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @projectName：javaTestDemo
 * @className：Message
 * @describe：TODO
 * @createTime：2020/4/13 4:48 下午
 * @author：HuTao
 */
@Data
public class Message {

    private String id;
    private String msg;
    private LocalDateTime sendTime;

}

package com.hj.study.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 删除记录表
 *
 * @author Administrator
 * @date 2019-04-19 14:48:26
 */
@Data
@Accessors(chain = true)
@Table(name = "sys_delete_log")
public class SysDeleteLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 级联删除key 级联删除多条数据，通过此字段进行关联一次操作
     */
    @Column(name = "keyword")
    private String keyword;

    /**
     * 表名
     */
    @Column(name = "table_name")
    private String tableName;

    /**
     * 记录内容 此字段为json类型，利于查询
     */
    @Column(name = "content")
    private String content;

    /**
     * 注释
     */
    @Column(name = "memo")
    private String memo;

    /**
     * 创建人编号
     */
    @Column(name = "delete_id")
    private String deleteId;

    /**
     * 创建日期
     */
    @Column(name = "delete_time")
    private Date deleteTime;

}

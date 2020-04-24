package com.hj.study.dao;

import com.hj.study.entity.SysDeleteLogEntity;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * sys_delete_log 持久化层
 *
 * @author : hutao
 * @date : 2018/03/23 16:41
 */
@Mapper
public interface SysDeleteLogDao extends BaseMapper<SysDeleteLogEntity> {
}
package com.hj.study.dao;

import com.hj.study.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * sys_log 持久化层
 *
 * @author : hutao
 * @date : 2018/05/22 14:50
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {


}

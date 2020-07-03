package com.hj.study.service;

import com.hj.study.entity.MccXSelectEntity;
import com.hj.study.entity.SysLogEntity;
import com.hj.study.utils.CommonException;

import java.util.List;
import java.util.Map;
/**
 * 系统日志
 *
 * @author : hutao
 * @date : 2018/1/20 10:10
 */
public interface SysLogService  {


    /**
     * 分页查询
     *
     * @param pageQuery 分页参数
     * @return result
     */
    List<SysLogEntity> findPage(Map<String, Object> map);

    /**
     * 保存日志
     */
    int save(SysLogEntity sysLogEntity) throws CommonException;

    int insertBatchSelect(List<MccXSelectEntity> list);


}

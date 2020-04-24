package com.hj.study.service.impl;

import com.hj.study.dao.SysLogDao;
import com.hj.study.entity.SysLogEntity;
import com.hj.study.service.SysLogService;
import com.hj.study.utils.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: 日志管理
* @Author: Brock.lee
* @Date: 2019/5/15
**/
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogDao sysLogDao;

    @Override
    public List<SysLogEntity> findPage(Map<String, Object> map) {
        SysLogEntity sysLogEntity = JsonUtil.mapToBean(map, SysLogEntity.class) ;
        return sysLogDao.select(sysLogEntity);
    }
}

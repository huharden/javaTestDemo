package com.hj.study.service.impl;

import com.hj.study.dao.SysLogDao;
import com.hj.study.entity.SysDeleteLogEntity;
import com.hj.study.entity.SysLogEntity;
import com.hj.study.service.SysDeleteService;
import com.hj.study.service.SysLogService;
import com.hj.study.utils.CommonException;
import com.hj.study.utils.JsonUtil;
import com.hj.study.utils.SnowflakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SysDeleteService sysDeleteService;

    @Override
    public List<SysLogEntity> findPage(Map<String, Object> map) {
        SysLogEntity sysLogEntity = JsonUtil.mapToBean(map, SysLogEntity.class) ;
        return sysLogDao.select(sysLogEntity);
    }

    @Override
    @Transactional
    public int save(SysLogEntity sysLogEntity) {

        sysLogEntity.setId(SnowflakeUtils.nextId());
        sysLogEntity.setMethod("INSERT");
        sysLogDao.insertSelective(sysLogEntity);

        //int count = this.update(sysLogEntity);
        SysDeleteLogEntity sysDeleteLogEntity = new SysDeleteLogEntity();
        sysDeleteLogEntity.setTableName("sys_log");
        sysDeleteService.saveLog(sysDeleteLogEntity);


        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int update(SysLogEntity sysLogEntity){

        sysLogEntity.setMethod("UPDATE");
        if(sysLogEntity.getTime() == null){
            throw new CommonException("必填参数缺失");
        }
        return sysLogDao.updateByPrimaryKey(sysLogEntity);

    }
}

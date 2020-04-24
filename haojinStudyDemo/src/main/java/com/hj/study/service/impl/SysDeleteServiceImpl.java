package com.hj.study.service.impl;

import com.hj.study.dao.SysDeleteLogDao;
import com.hj.study.entity.SysDeleteLogEntity;
import com.hj.study.service.SysDeleteService;
import com.hj.study.utils.CommonException;
import com.hj.study.utils.SnowflakeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 业务删除日志
 *
 * @author : hutao
 * @date : 2018/3/29 14:56
 */
@Service
public class SysDeleteServiceImpl implements SysDeleteService {


    @Resource
    private SysDeleteLogDao sysDeleteLogDao;

    @Override
    @Transactional
    public int saveLog(SysDeleteLogEntity sysDeleteLogEntity) {

        sysDeleteLogEntity.setId(SnowflakeUtils.nextId());

        if(sysDeleteLogEntity.getContent() == null){
            throw new CommonException("必填参数缺失");
        }

        return sysDeleteLogDao.insertSelective(sysDeleteLogEntity);
    }



}

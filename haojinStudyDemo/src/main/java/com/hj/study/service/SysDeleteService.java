package com.hj.study.service;


import com.hj.study.entity.SysDeleteLogEntity;
import com.hj.study.utils.CommonException;

/**
 * @author : hutao
 * @date : 2018/3/29 14:56
 */
public interface SysDeleteService  {

    int saveLog(SysDeleteLogEntity sysDeleteLogEntity) throws CommonException;




}

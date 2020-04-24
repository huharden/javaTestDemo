package com.hj.study.controller;

import com.hj.study.constant.StatusCode;
import com.hj.study.entity.SysLogEntity;
import com.hj.study.service.SysLogService;
import com.hj.study.utils.CommonException;
import com.hj.study.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
* @Description: 系统日志
* @Author: Brock.lee
* @Date: 2019/5/15
**/
@RestController
@RequestMapping("/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;


    /**
     * 日志信息
     */
    @PostMapping("/info")
//    @RequiresPermissions("sys:log:select")
    public R info(@RequestBody HashMap<String, Object> map) {

        List<SysLogEntity> log = sysLogService.findPage(map);
        if (log != null) {
            return R.ok(log);
        }
        return R.error(StatusCode.DATABASE_SELECT_FAILURE);
    }

    @PostMapping("/save")
    public R save(@RequestBody SysLogEntity sysLogEntity) throws CommonException {

        sysLogService.save(sysLogEntity);
        return R.ok();
    }


}

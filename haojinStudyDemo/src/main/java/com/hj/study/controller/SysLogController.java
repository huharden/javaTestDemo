package com.hj.study.controller;

import com.hj.study.constant.StatusCode;
import com.hj.study.entity.MccXSelectEntity;
import com.hj.study.entity.SysLogEntity;
import com.hj.study.service.SysLogService;
import com.hj.study.utils.CommonException;
import com.hj.study.utils.JsonUtil;
import com.hj.study.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: 系统日志
* @Author: Brock.lee
* @Date: 2019/5/15
**/
@RestController
@RequestMapping("mccm/system/NjPush")
@Slf4j
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

    @PostMapping(path = "/test")
    public String syncUserRoleGrid(@RequestBody Map<String, Object> params) {
        log.info("成功接收到消息===>>> {}", JsonUtil.toJson(params));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("SN", params.get("SN"));
        map.put("errorDescription", "成功");

        return JsonUtil.toJson(map);
    }

    @PostMapping(path = "/insertSelect")
    public String insertSelect(@RequestBody Map<String, Object> params) {

        List<MccXSelectEntity> list = new ArrayList<>();

        for (int i = 0; i<10000; i++){

            MccXSelectEntity mccXSelectEntity = new MccXSelectEntity();
            mccXSelectEntity.setFIELD("13502000" + i);
            mccXSelectEntity.setPROCESSING_ID("160169");
            mccXSelectEntity.setTYPE("");
            mccXSelectEntity.setMblNo("151700" + i);
            list.add(mccXSelectEntity);
        }
        sysLogService.insertBatchSelect(list);

        return "";

    }




}

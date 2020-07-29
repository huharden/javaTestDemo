package com.hj.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
@Slf4j
public class ScheduleJobTest implements SchedulingConfigurer {

    private static final String DEFAULT_CRON = "0/5 * * * * ?";
    private String cron = DEFAULT_CRON;
    Map<String, String> map = new HashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {

            log.info("真正的任务执行逻辑。。。执行任务id===>>>");
        }, triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger(cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        });
    }

    public void setCron(String cron) {
        map.put("1", "0/5 * * * * ?");
        map.put("2", "0/15 * * * * ?");
        System.out.println("原来的cron："+this.cron+"更新后的cron："+cron);
        this.cron = cron;
    }
}

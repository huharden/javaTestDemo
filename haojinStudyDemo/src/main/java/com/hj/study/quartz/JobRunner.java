package com.hj.study.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * @author liangkun
 * @ClassName JobRunner
 * @Description 定时任务工具类
 * @date Apr 26, 2017 3:31:26 PM
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
@Slf4j
public class JobRunner {



    public static final int STRATEGY_RULE_ONE = 0;
    public static final int STRATEGY_RULE_INTERVAL = 1;
    public static final int STRATEGY_RULE_NOW = 2;

    @Autowired
    SchedulerFactoryBean schedulerFactory;

    /**
     * @param className 启动类名
     * @param jobId     启动任务name
     * @param jobType   任务类型(一次、重复、立即)
     * @param cronReg   定时器规则
     * @param date
     * @return
     * @Description 启动任务
     * @Author 陈浩良
     * @date Apr 28, 2017 9:31:06 AM
     */
    public boolean startJob(String className, String jobId, int jobType, String cronReg, Date date) {

        boolean successFlag = false;
        try {
            // 任务全部使用同一个调度器
            Scheduler scheduler = null;

            // 获取调度器
            scheduler = schedulerFactory.getScheduler();

            if (!scheduler.isStarted()) {
                scheduler.start();
            }
            Class forName = Class.forName(className);
            JobDetail jobDetail = newJob(forName).withIdentity(jobId).build();
            jobDetail.getJobDataMap().put("jobKey", jobId);
            switch (jobType) {
                case STRATEGY_RULE_ONE:
                    SimpleTrigger sTrigger = (SimpleTrigger) newTrigger().startAt(date) // 设定5分钟后运行
                            .build();
                    scheduler.scheduleJob(jobDetail, sTrigger);
                    successFlag = true;
                    break;
                case STRATEGY_RULE_INTERVAL:
                    CronTrigger cTrigger = newTrigger().withSchedule(cronSchedule(cronReg)).build();
                    //强制put一个值进去，避免集群数据库持久化定时任务报错
                    cTrigger.getJobDataMap().put("key", "");
                    // 添加到调度器
                    scheduler.scheduleJob(jobDetail, cTrigger);
                    successFlag = true;
                    break;
                case STRATEGY_RULE_NOW:
                    jobDetail = newJob(forName).withIdentity(jobId).storeDurably().build();

                    jobDetail.getJobDataMap().put("jobKey", jobId);
                    jobDetail.getJobDataMap().put("jobType", STRATEGY_RULE_NOW);
                    // 添加到调度器
                    scheduler.addJob(jobDetail, true);
                    // 直接触发
                    scheduler.triggerJob(JobKey.jobKey(jobId));

                    successFlag = true;
                    break;
                default:

                    successFlag = false;
                    break;
            }
        }
        catch (Exception e) {
            log.info(e.getMessage());
            successFlag = false;
        }
        return successFlag;
    }

    /**
     * @param jobId
     * @return
     * @Description 停止任务
     * @Author 陈浩良
     * @date Apr 28, 2017 10:11:02 AM
     */
    public boolean shutDownJob(String jobId) {
        try {
            // 任务全部使用同一个调度器
            Scheduler scheduler = null;

            // 获取调度器
            scheduler = schedulerFactory.getScheduler();

            JobKey jobKey = JobKey.jobKey(jobId);

            // 添加到调度器
            scheduler.deleteJob(jobKey);
            //中断调度
            try {
                scheduler.interrupt(jobKey);
            }
            catch (Exception e) {
                log.info(e.getMessage());
            }

            return true;
        }
        catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    public Scheduler getScheduler() {
        return schedulerFactory.getScheduler();
    }

    /**
     * @param runTime
     * @return
     * @Description 从数据库格式转换到cron表达式
     * @Author 陈浩良
     * @date May 9, 2017 6:34:15 PM
     */
    public static String stringToCron(String runTime) {
        // YYYYMMDDWW12:12:00
        String yyyy = runTime.substring(0, 4);
        String mm = runTime.substring(4, 6);
        String dd = runTime.substring(6, 8);
        String ww = runTime.substring(8, 10);
        String hh = runTime.substring(10, 12);
        String MM = runTime.substring(13, 15);
        String ss = runTime.substring(16, 18);
        StringBuilder sBuilder = new StringBuilder();
        if ("SS".equals(ss)) {
            sBuilder.append("* ");
        }
        else {
            sBuilder.append(ss).append(" ");
        }
        if ("MM".equals(MM)) {
            sBuilder.append("* ");
        }
        else {
            sBuilder.append(MM).append(" ");
        }
        if ("HH".equals(hh)) {
            sBuilder.append("* ");
        }
        else {
            sBuilder.append(hh).append(" ");
        }
        if ("DD".equals(dd)) {
            sBuilder.append("* ");
        }
        else {
            sBuilder.append(dd).append(" ");
        }
        if ("MM".equals(mm)) {
            sBuilder.append("* ");
        }
        else {
            sBuilder.append(mm).append(" ");
        }
        if ("WW".equals(ww)) {
            sBuilder.append("? ");
        }
        else {
            String weekday = ww.substring(1, 2);
            if ("7".equals(weekday)) {
                sBuilder.append("1 ");
            }
            else {
                sBuilder.append(Integer.parseInt(weekday) + 1).append(" ");
            }
        }
        if (!"YYYY".equals(yyyy)) {
            sBuilder.append(yyyy);
        }

        return sBuilder.toString();
    }
}

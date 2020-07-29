package com.hj.study.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableScheduling
@Slf4j
public class QuartzConfig {

    public static final String QUARTZ_PROPERTIES_PATH = "quartz.properties";


//    public static final String QUARTZ_PROPERTIES_PATH_DEV = "application-dev.properties";
//
//    public static final String QUARTZ_PROPERTIES_PATH_PROD = "application-prod.properties";

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * 持久bean
     **/
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, PlatformTransactionManager transactionManager) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setJobFactory(jobFactory);
        Properties quartzProperties = null;
        quartzProperties = quartzProperties();
        String storeClazz = (String) quartzProperties.get("org.quartz.jobStore.class");
        if (!"org.quartz.simpl.RAMJobStore".equals(storeClazz)) {
            factory.setQuartzProperties(quartzProperties());
            //jdbc store
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            try {
                dataSource.setUrl((String) quartzProperties.get("org.quartz.dataSource.myDS.URL"));
                dataSource.setUsername((String) quartzProperties.get("org.quartz.dataSource.myDS.user"));
                dataSource.setDriverClassName((String) quartzProperties.get("org.quartz.dataSource.myDS.driver"));
                //dataSource.setPassword(ConfigTools.decrypt((String) quartzProperties.get("org.quartz.dataSource.myDS.publickey"), (String) quartzProperties.get("org.quartz.dataSource.myDS.password")));
                dataSource.setPassword((String) quartzProperties.get("org.quartz.dataSource.myDS.password"));
            }
            catch (Exception e) {
                log.info(e.getMessage());
            }
            factory.setDataSource(dataSource);
        }
        return factory;
    }

    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_PATH));
        try {
            propertiesFactoryBean.afterPropertiesSet();
        }
        catch (IOException e) {
            log.info(e.getMessage());
        }
        Properties properties = null;
        try {
            properties = propertiesFactoryBean.getObject();
        }
        catch (IOException e) {
            log.info(e.getMessage());
        }
//        String type = properties.getProperty("spring.profiles.active"); //获取文件前缀
//        if(type.equals("dev")){
//            propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_PATH_DEV));
//        }else{
//            propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_PATH_PROD));
//        }
//        propertiesFactoryBean.afterPropertiesSet();
        return properties;
    }

    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements
            ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) {
            final Object job;
            try {
                job = super.createJobInstance(bundle);
                beanFactory.autowireBean(job);
                return job;
            }
            catch (Exception e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }
}
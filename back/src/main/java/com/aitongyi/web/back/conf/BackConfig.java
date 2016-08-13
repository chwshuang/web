package com.aitongyi.web.back.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configurable
@PropertySource(value={"classpath:back.properties"})
public class BackConfig {
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean(name="schedulerFactoryBean")
	public SchedulerFactoryBean schedulerFactoryBean(){
		return new SchedulerFactoryBean();
	}

}

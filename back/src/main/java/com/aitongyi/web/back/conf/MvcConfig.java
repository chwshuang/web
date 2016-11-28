package com.aitongyi.web.back.conf;

//import org.mybatis.spring.annotation.MapperScan;
import com.aitongyi.web.back.security.SecurityHandlerInterceptor;
import com.aitongyi.web.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages =
        {"com.aitongyi.web.back.controller",
         "com.aitongyi.web.service",
         "com.aitongyi.web.task"})
@MapperScan("com.aitongyi.web.dao.mapper")
public class MvcConfig extends WebMvcConfigurerAdapter {
 
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    }

    @Autowired
    private UserService userService;

    /**
     * 添加安全处理拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityHandlerInterceptor(userService));
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
    }

    /**
     * jsp视图解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        bean.setPrefix("/WEB-INF/pages/");
        bean.setSuffix(".jsp");
        return bean;
    }

    /**
     *  公共部分解析器
     * @return
     */
    @Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		CommonsMultipartResolver common = new CommonsMultipartResolver();
		common.setMaxUploadSize(10 * 1024 * 1024);//10M
		return common;
	}
    
}
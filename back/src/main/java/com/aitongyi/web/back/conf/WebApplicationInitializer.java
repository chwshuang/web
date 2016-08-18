package com.aitongyi.web.back.conf;

import com.aitongyi.web.cache.conf.CacheConfig;
import com.aitongyi.web.dao.conf.DatabaseConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 项目启动基类
 * -- 整个项目的入口
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/**
	 * 获取配置信息
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { BackConfig.class, DatabaseConfig.class, SecurityConfig.class, CacheConfig.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return null;
	}
}

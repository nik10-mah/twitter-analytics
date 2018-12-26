/**
 * 
 */
package com.ml.epic.ta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * @author Nikhil Mahajan
 * @since Nov 27, 2018
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	}
	
	/**
	 * Layout dialect config for Thymeleaf
	 *
	 * @return the layout dialect
	 */
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

}

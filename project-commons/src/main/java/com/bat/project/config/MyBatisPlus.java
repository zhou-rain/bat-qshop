package com.bat.project.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhouR
 * @date: Create in 2020/1/8 - 20:17
 * @function: mybatis-plus 插件配置模板
 */
@Configuration
public class MyBatisPlus {

	/**
	 *分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor(){
		return new PaginationInterceptor();
	}

}

package com.mp.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.mp.mybatisplus.mp.injectors.MySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhouR
 * @date: Create in 2020/1/8 - 20:14
 * @function:  mp配置模板
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

	/**
	 * 乐观锁
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor(){
		return new OptimisticLockerInterceptor();
	}

	/**
	 * 如果需要自定义mapper
	 *
	 * 将自定义mapper加入容器中
	 */
	@Bean
	public MySqlInjector mySqlInjector(){
		return  new MySqlInjector();
	}

}

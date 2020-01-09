package com.test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bat.qmall.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
/*
	使用说明：
		1、创建springboot项目，选择自己需要的模块
		2、pom添加dependencies，

			<dependencies>

				<dependency>
					<groupId>com.bat.project</groupId>
					<artifactId>project-commons</artifactId>
					<version>1.0-SNAPSHOT</version>
				</dependency>
				<dependency>
					<groupId>com.bat.project</groupId>
					<artifactId>api-service</artifactId>
					<version>1.0-SNAPSHOT</version>
				</dependency>

			</dependencies>


		3、配置文件，
		必填属性：spring.profiles.active

		可选配置文件
		（1）、默认配置+数据库+mp配置		common
		（2）、开启zookeeper注册 			zk
		（3）、开启分页插件 				pagehelper
		（4）、开启mybatis-plu配置		mp

		如果要开启多个模块的配置项，用英文逗号隔开
		例： spring.profiles.active = common,pagehelper

		如果不需要另外指定数据库，只需要使用common即可


		4、启动项中加入@MapperScan("com.bat.qmall.mapper")

		5、创建 service.impl包，
		   创建controller包

		   尽情使用吧！



		6、使用git提交代码，
			1)、先clean
			2)、代码本地备份
			3)、Add
			4)、Conmmit directory
			5)、最后Repository——>push
			6)、修改详情写清楚，最后加上自己的姓名首字母小写


		注意！提交时添加文件
			只要提交每个模块的src即可
			别的classes文件，.idea  .mvn等其余配置项 禁止提交
		切记！切记！切记！


 */

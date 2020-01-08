package com.test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
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
		1、创建springboot项目，什么都不用勾选，直接下一步
		2、pom文件修改父依赖，和dependencies，
			加入build插件  这个是继承配置文件的插件

			<parent>
				<artifactId>project-dependencies</artifactId>
				<groupId>com.bat.project</groupId>
				<version>1.0-SNAPSHOT</version>
			</parent>
			<artifactId>自己的模块名</artifactId>

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


			<build>
				<plugins>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-remote-resources-plugin</artifactId>
						<version>1.6.0</version>
						<configuration>
							<resourceBundles>
								<resourceBundle>org.test:shared-resources:${project.version}</resourceBundle>
							</resourceBundles>
						</configuration>
						<executions>
							<execution>
								<goals>
									<goal>process</goal>
								</goals>
							</execution>
						</executions>
					</plugin>
				</plugins>
			</build>



		3、配置文件，

		必填项：spring.profiles.active

		（1）、如果使用默认数据库		parent
		（2）、如果自己指定数据库		parentnodb
		（3）、开启zookeeper注册 		zk
		（3）、开启分页插件 			pagehelper
		（3）、开启zookeeper注册 		zk

		如果要开启多个模块的配置项，用英文逗号隔开

		例： spring.profiles.active = parent,pagehelper


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

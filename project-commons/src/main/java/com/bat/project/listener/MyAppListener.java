package com.bat.project.listener;

import com.bat.project.Const.SystemConst;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * 项目启动的时候，将一些常用的数据放在application中，整个项目共享
 * @author Zhour
 *
 */
public class MyAppListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ServletContext context = sce.getServletContext();
		
		//将项目路径放在application域中
		String basepath = context.getContextPath();

		context.setAttribute("basepath", basepath);
		SystemConst.BASE_PATH = basepath;
		System.out.println("初始化项目路径>>> basepath:"+basepath);

		//设置完要去配置文件中配置

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
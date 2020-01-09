package com.mp.mybatisplus.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhouR
 * @date: Create in 2020/1/9 - 13:01
 * @function:
 */
public class MySqlInjector extends DefaultSqlInjector {
	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

		//获取父类的所有集合
		List<AbstractMethod> list = super.getMethodList(mapperClass);

		//将自定义的方法加入
		list.add(new FindAll());

		return list;
	}
}


/*
	1、创建MyBaseMapper接口，继承BaseMapper
	2、在接口中自定义方法
	3、创建MySqlInjector类，继承DefaultSqlInjector
	4、创建findAll类，继承AbstractMethod
	5、根据自己需要的增删改查方法，去AbstractMethod中查找相应的返回类，具体参数根据返回值自行解决
	6、完成之后，在创建MySqlInjector类中，将刚才定义的FindAll方法加入集合中，return出去
	7、将自定义mapper加入容器中
	8、以后使用时，只需要继承刚才定义的MyBaseMapper接口即可
 */
package com.mp.mybatisplus.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author: zhouR
 * @date: Create in 2020/1/9 - 13:03
 * @function:
 */
public class FindAll extends AbstractMethod {
	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {

		String sql = "select * from " + tableInfo.getTableName();
		SqlSource sqlSource = languageDriver.createSqlSource(configuration,sql,modelClass);

		return this.addSelectMappedStatementForTable(mapperClass, "findAll", sqlSource,tableInfo);

	}
}

/*
	第一个参数：默认
	第二个参数：在接口中定义的方法名
	第三个参数：sql语句
	第四个参数：默认

 */

package com.mp.mybatisplus.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author: zhouR
 * @date: Create in 2020/1/9 - 12:58
 * @function: 自定义sql注入
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

	List<T> findAll();


	//扩展其他方法

}

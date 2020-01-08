package com.bat.qmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bat.qmall.bean.User;

import java.util.List;

/**
 * @author: zhouR
 * @date: Create in 2020/1/8 - 11:39
 * @function:
 */
public interface UserMapper extends BaseMapper<User> {

	List<User> getAll();

}
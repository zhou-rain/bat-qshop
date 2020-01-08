package com.test.demo.service.impl;

import com.bat.qmall.bean.User;
import com.bat.qmall.mapper.UserMapper;
import com.bat.qmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhouR
 * @date: Create in 2020/1/8 - 17:15
 * @function:
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> getAll() {
		return userMapper.getAll();
	}

	@Override
	public List<User> selectAll() {
		return userMapper.selectList(null);
	}
}

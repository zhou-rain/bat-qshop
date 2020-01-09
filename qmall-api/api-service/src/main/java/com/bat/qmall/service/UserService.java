package com.bat.qmall.service;

import com.bat.qmall.bean.User;

import java.util.List;

/**
 * @author: zhouR
 * @date: Create in 2020/1/8 - 17:11
 * @function:
 */
public interface UserService{

	List<User> getAll();

	List<User> selectAll();

}

package com.test.demo.controller;

import com.bat.project.commons.Msg;
import com.bat.qmall.bean.User;
import com.bat.qmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhouR
 * @date: Create in 2020/1/8 - 10:18
 * @function:
 */
@RestController
public class TestC {

	@Autowired
	UserService userService;

	/**
	 * 	测试地址：http://localhost:8080/ok
	 */
	@RequestMapping("/ok")
	public Msg ok(){
		List<User> all = userService.selectAll();
		return Msg.success().add("all",all);
	}

	/**
	 * 测试地址：http://localhost:8080/okk
	 */
	@RequestMapping("/okk")
	public Msg okk(){
		List<User> all = userService.getAll();
		return Msg.success().add("all",all);
	}

}

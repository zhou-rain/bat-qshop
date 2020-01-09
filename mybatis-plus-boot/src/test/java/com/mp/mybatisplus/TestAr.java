package com.mp.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.mybatisplus.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: zhouR
 * @date: Create in 2020/1/9 - 11:42
 * @function:  使用ar，需要在实体类上继承Model<User>
 */
@SpringBootTest
public class TestAr {
	
	/**
	 * function:  自己查自己
	 */
	@Test
	void testTSelectById(){
		User user = new User();
		user.setId(2L);
		User user1 = user.selectById();
		System.out.println("user1 = " + user1);
	}
	
	
	/**
	 * function:
	 */
	@Test
	void testInsert(){
		User user = new User();
		user.setUserName("pxs");
		user.setPassword("123");
		user.setEmail("123@qq.com");

		boolean result = user.insert();
		System.out.println("result = " + result);
		
	}

	/**
	 * function:
	 */
	@Test
	public void testSelect(){
		User user = new User();
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		//查询年龄大于22岁的
		wrapper.ge("age",22);
		user.selectList(wrapper);

	}



	/**
	 * function:乐观锁
	 *
	 * 每次更新时，都要将当前的version带上，防止并发线程重复提交
	 * 只支持updateById() update(entity,wrapper)两种方法
	 *
	 */
	@Test
	public void testVersion(){

		User user = new User();

		user.setId(8L);
		User userVersion = user.selectById();

		user.setName("潘祥森");
		user.setVersion(userVersion.getVersion());
		user.updateById();


		//UPDATE tb_user
		// SET name=?,
		// version=?
		// WHERE id=? AND version=?

	}








}

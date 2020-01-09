package com.mp.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.mybatisplus.bean.User;
import com.mp.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	UserMapper userMapper;


	/**
	 * function:  查询所有
	 */
	@Test
	public void testSelectAll() {
		//由于设置了不查询password，这里查询不到该字段
		userMapper.selectList(null);
	}


	/**
	 * function:插入数据时，主键回填
	 * <p>
	 * 但是我们主键是自增的，而mp默认不是自增，所以要去实体类的id加入注解
	 *
	 * @TableId(type = IdType.AUTO)
	 * private Long id;
	 *
	 */
	@Test
	public void testInsert() {
		User user = new User(null, "zhouran", "123", "周冉1", 18, "123@qq.com",1,1, "南京");
		int insert = userMapper.insert(user);
		System.out.println("影响的行数 = " + insert);

		System.out.println("mp插入后，会自动回填主键id  " + user.getId());

	}


	/**
	 * function:mybatisplus中有两种更新语句
	 * <p>
	 * 1、根据id更新
	 * 2、根据条件更新
	 */
	@Test
	public void testUpdateById() {
		User user = new User();
		user.setId(5L);        //修改条件
		user.setEmail("gengxin@qq.com");    //要修改的字段
		userMapper.updateById(user);

		/*
		 *	不设置值的字段  不更新
		 */
	}


	/**
	 * function:  根据条件更新
	 * <p>
	 * 注意：eq的column 是数据库的字段，不是实体类的映射
	 */
	@Test
	public void testUpdateByWrapper() {
		User user = new User();
		user.setEmail("genjutiaojiangengxin@qq.com");    //要修改的字段
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("user_name", "zhangsan");
		userMapper.update(user, wrapper);
	}


	/**
	 * function: 更新
	 * <p>
	 * 使用 UpdateWrapper  有set()方法
	 */
	@Test
	public void testUpdateWrapper() {

		UpdateWrapper<User> wrapper = new UpdateWrapper<>();
		wrapper.set("age", 21)
				.set("password", "99999")
				.eq("user_name", "zhangsan");
		int update = userMapper.update(null, wrapper);
		System.out.println("update = " + update);

	}


	/**
	 * function: 根据map条件删除
	 * 多条件之间是and关系
	 */
	@Test
	public void testDeleteByMap() {

		Map<String, Object> map = new HashMap<>();

		map.put("password", "123");
		map.put("name", "周冉1");

		//DELETE FROM tb_user WHERE password = ? AND name = ?


		int i = userMapper.deleteByMap(map);
		System.out.println("i = " + i);

	}


	/**
	 * 上下两种方法效果是一样的
	 * 推荐使用第二种
	 */
	@Test
	public void testDelete() {

		//用法一、
//		QueryWrapper<User> wrapper = new QueryWrapper<>();
//		wrapper.eq("user_name","zhouran")
//				.eq("password",123);

		//用法二、  推荐
		User user = new User();
		user.setId(8L);
		QueryWrapper<User> wrapper = new QueryWrapper<>(user);

		userMapper.delete(wrapper);


	}

	/**
	 * function:逻辑删除
	 *
	 * 删除时其实是更新操作
	 *
	 * 查询时自动带上 deleted=1
	 */
	@Test
	public void test(){

		//UPDATE tb_user SET deleted=0 WHERE id=? AND deleted=1
		//userMapper.deleteById(8L);


		userMapper.selectById(8L);
		//SELECT id,user_name,name,age,email,version,deleted FROM tb_user WHERE id=? AND deleted=1

	}


	/**
	 * function:
	 */
	@Test
	public void testSelectList() {

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.like("email", "test");
		/*
		 Preparing: SELECT id,user_name,name,age,email FROM tb_user WHERE (email LIKE ?)
	 ==> Parameters: %test%(String)
		 */

		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);
	}

	/**
	 * function: 分页
	 */
	@Test
	public void testSelectPage() {

		Page<User> page = new Page<>(2, 2);
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		wrapper.like("email", "test");


		IPage<User> page1 = userMapper.selectPage(page, wrapper);
		System.out.println("总数据集合 = " + page1.getRecords());
		System.out.println("总页数 = " + page1.getPages());
		System.out.println("总页数 = " + page1.getTotal());
		System.out.println("当前页 = " + page1.getCurrent());

	}


/*
 ISqlInjector  SQL自动注入器接口
 它的实现类  AbstractSqlInjector 中有循环注入自定义方法 inject()
 inject 中有注入自定义方法  injectMappedStatement 这是一个抽象方法
 放在injectMappedStatement，按ctrl+t 可以看到所有实现它的方法，是我们常用的增删改查方法

 */


	/**
	 * function:  allEq()
	 */
	@Test
	public void testAllEq1() {
		Map<String,Object> map = new HashMap<>();
		map.put("user_name","zhouran");
		map.put("password","123");
		map.put("age",18);

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.allEq(map);

		//SELECT id,user_name,name,age,email
		// FROM tb_user
		// WHERE (password = ? AND user_name = ? AND age = ?)

		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}


	/**
	 * function:  allEq(map)  如果有null值，则拼成 is null
	 */
	@Test
	public void testAllEq2() {
		Map<String,Object> map = new HashMap<>();
		map.put("user_name","zhouran");
		map.put("password",null);
		map.put("age",18);

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.allEq(map);

		//SELECT id,user_name,name,age,email
		// FROM tb_user
		// WHERE (password IS NULL AND user_name = ? AND age = ?)

		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}


	/**
	 * function:  allEq(map,false)  如果有null值，则不参与条件拼接
	 */
	@Test
	public void testAllEq3() {
		Map<String,Object> map = new HashMap<>();
		map.put("user_name","zhouran");
		map.put("password",null);
		map.put("age",18);

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.allEq(map,false);

		//SELECT id,user_name,name,age,email
		// FROM tb_user
		// WHERE (user_name = ? AND age = ?)

		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}


	/**
	 * function:  allEq(BiPredicate<R, V> filter, Map<R, V> params)
	 * 满足条件才进行拼接
	 */
	@Test
	public void testAllEq4() {
		Map<String,Object> map = new HashMap<>();
		map.put("user_name","zhouran");
		map.put("password",null);
		map.put("age",18);

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.allEq((k,v) ->(k.equals("age")||k.equals("name")), map);
		//SELECT id,user_name,name,age,email FROM tb_user WHERE (age = ?)

		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}


	/**
	 * likeLeft = '%王'
	 * likeRight = '王%'
	 */

	/**
	 * function: 排序
	 */
	@Test
	public  void testOrderBy(){

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("age");
		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);

	}


	/**
	 * function:  or()
	 */
	@Test
	public void testOr(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("user_name","zhouran")
				.or()
				.eq("user_name","lsii");

		//SELECT id,user_name,name,age,email
		// FROM tb_user
		// WHERE (user_name = ? OR user_name = ?)

		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);

	}


	/**
	 * 使用select指定要查询的字段
	 */
	@Test
	public void testSelect(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("user_name","zhouran")
				.or()
				.eq("user_name","lsii")
				.select("id","name","age");

		//SELECT id,name,age
		// FROM tb_user
		// WHERE (user_name = ? OR user_name = ?)


		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);

	}






	/**
	 * function: 测试自定义 findAll方法
	 */
	@Test
	public void testSelDefineFindAll(){
		List<User> list = userMapper.findAll();
		list.forEach(System.out::println);
	}





}

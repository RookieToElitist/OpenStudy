package com.study.shardingjdbc5;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shardingjdbc5.entity.User;
import com.study.shardingjdbc5.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbc5ApplicationTests {

	@Autowired
	private UserMapper userMapper;


	//添加操作
	@Test
	public void addUser() {
		User user = new User();
		user.setUserId(100L);
		user.setUserName("jake");
		user.setUserStatus("在职");
		userMapper.insert(user);
	}

	//查询操作
	@Test
	public void findCourseDb() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		//设置 userid 值
		wrapper.eq("user_id",100L);
		User user = userMapper.selectOne(wrapper);
		System.out.println(user);
	}

}

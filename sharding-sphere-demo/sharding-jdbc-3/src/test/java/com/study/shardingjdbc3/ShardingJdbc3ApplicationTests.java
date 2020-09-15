package com.study.shardingjdbc3;

import com.study.shardingjdbc3.entity.Course;
import com.study.shardingjdbc3.entity.User;
import com.study.shardingjdbc3.mapper.CourseMapper;
import com.study.shardingjdbc3.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbc3ApplicationTests {

	//注入 user 的 mapper
	@Autowired
	private UserMapper userMapper;
	//======================测试垂直分库==================
//添加操作
	@Test
	public void addUser() {
		User user = new User();
		user.setUserName("jake");
		user.setUserStatus("在职");
		userMapper.insert(user);
	}


	@Autowired
	private CourseMapper courseMapper;

	@Test
	public void addCourseDb() {
		Course course = new Course();
		course.setCname("javademo1");
		//分库根据 user_id
		course.setUserId(111L);
		course.setCstatus("Normal1");
		courseMapper.insert(course);

		course.setUserId(100L);
		courseMapper.insert(course);
	}

}

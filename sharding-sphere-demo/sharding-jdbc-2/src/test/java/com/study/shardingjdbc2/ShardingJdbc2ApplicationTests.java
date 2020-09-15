package com.study.shardingjdbc2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shardingjdbc2.entity.Course;
import com.study.shardingjdbc2.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbc2ApplicationTests {

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


	//查询操作
	@Test
	public void findCourseDb() {
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		//设置 userid 值
		wrapper.eq("user_id",100L);
		//设置 cid 值
		wrapper.eq("cid",465162909769531393L);
		Course course = courseMapper.selectOne(wrapper);
		System.out.println(course);
	}

}

package com.study.shardingjdbc1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shardingjdbc1.entity.Course;
import com.study.shardingjdbc1.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbc1ApplicationTests {


	/*
	异常描述：Caused by: java.lang.AbstractMethodError: Method com/mysql/jdbc/DatabaseMetaData.supportsStoredFunctionsUsingCallSyntax()Z is abstract
	异常描述：数据库驱动版本问题，原版本是5.0.4，改为5.1.44
	 */


	//注入 mapper
	@Autowired
	private CourseMapper courseMapper;
	//添加课程的方法
	@Test
	public void addCourse() {
		for(int i=1;i<=10;i++) {
			Course course = new Course();
			course.setCname("java"+i);
			course.setUserId(100L);
			course.setCstatus("Normal"+i);
			courseMapper.insert(course);
		}
	}
	//查询课程的方法
	@Test
	public void findCourse() {
		//MybatisPlus的封装的用法
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("cid",512564963689103361L);
		Course course = courseMapper.selectOne(wrapper);
		System.out.println(course);
	}

}

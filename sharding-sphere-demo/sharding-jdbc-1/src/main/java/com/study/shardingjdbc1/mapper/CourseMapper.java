package com.study.shardingjdbc1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingjdbc1.entity.Course;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Repeatable;

@Repository
public interface CourseMapper extends BaseMapper<Course> {
}

package com.study.shardingjdbc4.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingjdbc4.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}

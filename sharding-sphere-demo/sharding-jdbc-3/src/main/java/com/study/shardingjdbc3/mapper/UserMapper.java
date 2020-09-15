package com.study.shardingjdbc3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.shardingjdbc3.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}

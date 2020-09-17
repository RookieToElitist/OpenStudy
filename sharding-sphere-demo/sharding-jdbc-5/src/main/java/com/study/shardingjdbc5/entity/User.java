package com.study.shardingjdbc5.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user") //指定对应表
public class User {
    private Long userId;
    private String userName;
    private String userStatus;
}

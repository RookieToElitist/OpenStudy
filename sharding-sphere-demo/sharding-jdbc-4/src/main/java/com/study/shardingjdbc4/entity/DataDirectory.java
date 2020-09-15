package com.study.shardingjdbc4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("data_directory")
public class DataDirectory {
    private Long ddId;
    private String ddName;
    private String ddStatus;

}

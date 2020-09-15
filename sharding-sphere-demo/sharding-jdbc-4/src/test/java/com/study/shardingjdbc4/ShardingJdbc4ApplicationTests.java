package com.study.shardingjdbc4;

import com.study.shardingjdbc4.entity.DataDirectory;
import com.study.shardingjdbc4.mapper.DataDirectoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbc4ApplicationTests {

	@Autowired
	private DataDirectoryMapper dataDirectoryMapper;

	@Test
	public void addDD(){
		DataDirectory  dd=new DataDirectory();

		dd.setDdName("status");
		dd.setDdStatus("a");

		dataDirectoryMapper.insert(dd);
	}
}

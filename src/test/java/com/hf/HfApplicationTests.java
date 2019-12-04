package com.hf;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hf.dao.IpsDao;
import com.hf.entity.IpsEntity;
import com.hf.util.SpringContextUtil;

@SpringBootTest
class HfApplicationTests {

	@Test
	void contextLoads() {
    	List<IpsEntity> findPage = SpringContextUtil.getBean(IpsDao.class).findPage(1, 2);
    	for (IpsEntity ipsEntity : findPage) {
			System.out.println(ipsEntity.toString());
		}
	}

}

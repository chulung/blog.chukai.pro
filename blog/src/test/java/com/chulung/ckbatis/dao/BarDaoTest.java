package com.chulung.ckbatis.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chulung.ckbatis.mapper.BarMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class BarDaoTest {
	@Autowired
	private BarMapper barMapper;

	@Test
	public void shouldReturnMapper() {
		assertNotNull(barMapper);
	}

	@Test
	public void testExecuteBar() {
		assertEquals("name", barMapper.executeBar());
	}

}

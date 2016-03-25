package com.wck.smelting;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wck.smelting.bean.Player;
import com.wck.smelting.bean.PointRecord;
import com.wck.smelting.service.BaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class DataUtil extends BaseService {
	@Test
	public void test() {
		List<Player> list = this.session.queryList(new Player());
		for (Player player : list) {
			PointRecord pointRecord = new PointRecord();
			pointRecord.setPlayerId(player.getId());
			pointRecord.setPoint(50);
			pointRecord.setRemark("学习任务计划书+50");
			pointRecord.setCreateTime(LocalDateTime.now());
			this.session.insert(pointRecord);
		}
	}
}

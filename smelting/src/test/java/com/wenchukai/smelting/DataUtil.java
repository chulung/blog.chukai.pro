package com.wenchukai.smelting;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wenchukai.smelting.bean.Player;
import com.wenchukai.smelting.bean.PointRecord;
import com.wenchukai.smelting.service.BaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class DataUtil extends BaseService {
	@Test
	public void test() {
		Set<Integer> set=new HashSet<>(Arrays.asList());
		
		List<Player> list = this.session.queryList(new Player());
		for (Player player : list) {
			Integer a = null;
			if (set.contains(player.getId())) {
				a=50;
			}
			if (a != null) {

				PointRecord pointRecord = new PointRecord();
				pointRecord.setPlayerId(player.getId());
				pointRecord.setPoint(a);
				pointRecord.setRemark("长老分配 +" + a);
				pointRecord.setCreateTime(LocalDateTime.now());
				this.session.insert(pointRecord);
			}
		}
	}
}

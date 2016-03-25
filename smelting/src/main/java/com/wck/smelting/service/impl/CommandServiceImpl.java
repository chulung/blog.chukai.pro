package com.wck.smelting.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wck.function.FunctionUtil;
import com.wck.smelting.bean.Player;
import com.wck.smelting.bean.PlayerTeam;
import com.wck.smelting.bean.PointRecord;
import com.wck.smelting.exception.GlobalMethodRuntimeExcetion;
import com.wck.smelting.service.BaseService;
import com.wck.smelting.service.CommandService;

@Service
public class CommandServiceImpl extends BaseService implements CommandService {
	private ReentrantLock lock = new ReentrantLock();

	@Override
	public void excute(String command) {
		if (lock.isLocked()) {
			throw new GlobalMethodRuntimeExcetion("其他命令正在执行中，请稍候...");
		}
		lock.lock();
		try {
			if ("reCountAllPoint".equals(command)) {
				reCountAllPoint();
			} else {
				throw new GlobalMethodRuntimeExcetion("没有此命令");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			lock.unlock();
		}

	}

	private void reCountAllPoint() {
		List<PointRecord> pointRecords = this.session.queryList(
				"select pr.playerId,pr.point, p.teamId from pointRecord pr inner join player p on p.id=pr.playerId",
				PointRecord.class);
		FunctionUtil.groupingAndSum(pointRecords, PointRecord::getPlayerId, PointRecord::getPoint).entrySet()
				.forEach(e -> {
					Player bean = new Player();
					bean.setId(e.getKey());
					bean.setPoint(e.getValue());
					this.session.update(bean);
				});
		FunctionUtil.groupingAndSum(pointRecords, PointRecord::getTeamId, PointRecord::getPoint).entrySet()
				.forEach(e -> {
					PlayerTeam team = new PlayerTeam();
					team.setId(e.getKey());
					team.setTotalPoint(e.getValue());
					this.session.update(team);
				});
	}

	public static Map<Integer, Integer> groupingAndSum(List<PointRecord> pointRecords,
			Function<? super PointRecord, ? extends Integer> classifier,
			Function<? super PointRecord, ? extends Integer> mapper) {
		return pointRecords.stream()
				.collect(Collectors.groupingBy(classifier, Collectors.reducing(0, mapper, Integer::sum)));
	}

}

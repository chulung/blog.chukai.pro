package com.wenchukai.smelting.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenchukai.smelting.bean.Player;
import com.wenchukai.smelting.bean.Task;
import com.wenchukai.smelting.bean.TaskRecive;
import com.wenchukai.smelting.exception.GlobalMethodRuntimeExcetion;
import com.wenchukai.smelting.service.BaseService;
import com.wenchukai.smelting.service.PlayerService;
import com.wenchukai.smelting.service.TaskService;

@Service
public class TaskServiceImpl extends BaseService implements TaskService {
	@Resource
	private PlayerService playerService;

	@Override
	public Task getTaskById(Integer id) {
		if (id == null) {
			return new Task();
		}

		Task queryOne = this.session.queryOne(new Task(id));
		queryOne.setTaskRecives(this.session.queryList(new TaskRecive(queryOne.getId())));
		return queryOne;
	}

	@Override
	public Integer insert(Task task) {
		if (checkExistBlank(task.getCompletePoint(), task.getEndTime(), task.getTaskType(), task.getTaskName())) {
			return null;
		}
		task.setTaskDesc(task.getTaskDesc().replaceAll("\\r(\\n)?", "<br/>"));
		;
		task.setCreateTime(LocalDateTime.now());
		return this.session.insert(task);
	}

	@Override
	public List<Task> getCurTasks() {
		return this.session.queryList(
				"select id, taskName,completePoint,taskDesc,endTime,taskType from task where endTime>? order by createTime desc",
				Task.class, LocalDateTime.now());
	}

	@Override
	public List<Task> getExpireTasks() {
		return this.session.queryList(
				"select id, taskName,completePoint,taskDesc,endTime,taskType from task where endTime<? order by endTime desc",
				Task.class, LocalDateTime.now());
	}

	@Override
	public void receive(Integer id) {
		Task task = this.getTaskById(id);
		if (task == null) {
			throw new GlobalMethodRuntimeExcetion("任务不存在");
		}
		Player player = this.playerService.getPlayerById(this.webSessionSupport.getCurPlayerId().get());
		if (player.getTeamId() <= 0) {
			throw new GlobalMethodRuntimeExcetion("请先加入战队");
		}
		if (task.getEndTime().isBefore(LocalDateTime.now())) {
			throw new GlobalMethodRuntimeExcetion("任务已结束");
		}
		if (task.getTaskType() == 1) {
			TaskRecive recive = new TaskRecive();
			recive.setTeamId(player.getTeamId());
			recive.setTaskId(task.getId());
			if (this.session.queryOne(recive) != null) {
				throw new GlobalMethodRuntimeExcetion("你的战队已领取该任务");
			}
		} else {
			TaskRecive recive = new TaskRecive();
			recive.setTeamId(player.getTeamId());
			recive.setTaskId(task.getId());
			recive.setPlayerId(player.getId());
			if (this.session.queryOne(recive) != null) {
				throw new GlobalMethodRuntimeExcetion("你已领取该任务");
			}
		}
		this.session.insert(new TaskRecive(task.getId(), player.getTeamId(), player.getId(), LocalDateTime.now()));

	}

	@Override
	public Map<String, Object> getTaskRecive(Integer id) {
		Map<String, Object> map = new HashMap<>();
		Task task = this.session.queryOne(
				"select ts.taskType  from task inner join taskRecive tr on ts.id=tr.taskId and tr.id =? and tr.giveOut=0",
				Task.class, id);
		if (task == null) {
			return null;
		}

		TaskRecive taskRecive = this.session
				.queryOne("select id,teamId,playerId completePoint from taskRecive where id =?", TaskRecive.class, id);
		List<Player> players = null;
		if (task.getTaskType().equals(1)) {
			players = this.session.queryList("select playerId,userName from player where teamId=?", Player.class,
					taskRecive.getTeamId());
		} else if (task.getTaskType().equals(2)) {
			players = this.session.queryList("select playerId,userName from player where id=?", Player.class,
					taskRecive.getPlayerId());
		}
		int point = taskRecive.getCompletePoint();
		int mod = point % players.size();
		int ave = point / players.size();
		int[] pointAve = new int[players.size()];
		for (int i = 0; i < pointAve.length; i++) {
			if (i == 0) {
				pointAve[i] = ave + mod;
			} else {
				pointAve[i] = ave;
			}
		}
		taskRecive.setPointAve(pointAve);
		map.put("taskRecive", taskRecive);
		map.put("players", players);
		return map;
	}

}

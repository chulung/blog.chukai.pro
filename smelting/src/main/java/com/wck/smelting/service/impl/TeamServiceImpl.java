package com.wck.smelting.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wck.cache.annotation.Cache;
import com.wck.smelting.bean.Player;
import com.wck.smelting.bean.PlayerTeam;
import com.wck.smelting.bean.TaskRecive;
import com.wck.smelting.exception.GlobalMethodRuntimeExcetion;
import com.wck.smelting.service.BaseService;
import com.wck.smelting.service.TeamService;
import com.wck.smelting.service.PlayerService;

@Service
public class TeamServiceImpl extends BaseService implements TeamService {
	@Resource
	private PlayerService playerService;

	/**
	 * 查询所有战队 3分钟缓存
	 */
	@Override
	@Cache(key = "allTeam", timeToLive = 3)
	public List<PlayerTeam> getAllTeams() {
		return this.session.queryList(new PlayerTeam());
	}

	@Override
	public PlayerTeam getTeamById(Integer id) {
		if (id == null) {
			return null;
		}
		PlayerTeam team = this.session.queryOne(new PlayerTeam(id));
		if (team != null) {
			team.setPlayers(this.playerService.getPlayersByTeamId(id));
			team.setTaskRecives(this.session.queryList(
					"select ts.id,ts.completePoint,tr.giveOut,ts.taskname,p.userName from taskRecive tr inner join "
							+ "task ts on tr.taskId=ts.id inner join player p on p.id=tr.playerId where  tr.teamId =? ORDER BY tr.createTime",
					TaskRecive.class, team.getId()));
		}
		return team;
	}

	/**
	 * 查询所有战队
	 */
	@Override
	public List<PlayerTeam> getAllTeamsAndPlayer() {
		return session.queryList(new PlayerTeam()).stream().parallel().map(g -> {
			// 查询每个团队的玩家信息
			g.setPlayers(playerService.getPlayersByTeamId(g.getId()));
			return g;
			// 按团队积分排序
		}).sorted(Comparator.comparing(PlayerTeam::getTotalPoint).reversed()).collect(Collectors.toList());
	}

	@Override
	public boolean hasTeam(Optional<Integer> curPlayerId) {
		if (!curPlayerId.isPresent()) {
			return false;
		}
		return this.session.queryOne("select id from player where id=? and teamId!=0", Player.class,
				curPlayerId.get()) != null;
	}

	@Override
	public void joinTeam(Integer id) {
		if (id == null) {
			throw new GlobalMethodRuntimeExcetion("战队不存在");
		}
		PlayerTeam team = this.session.queryOne(new PlayerTeam(id));
		if (team != null) {
			this.session.execute("update player set teamId=?,teamName=? where id=?", team.getId(), team.getTeamName(),
					this.webSessionSupport.getCurPlayerId().get());
		}
	}
}

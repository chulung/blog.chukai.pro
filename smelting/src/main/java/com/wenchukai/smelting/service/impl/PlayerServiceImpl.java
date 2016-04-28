package com.wenchukai.smelting.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenchukai.cache.annotation.Cache;
import com.wenchukai.smelting.bean.Player;
import com.wenchukai.smelting.service.BaseService;
import com.wenchukai.smelting.service.PlayerService;
import com.wenchukai.smelting.util.WebSessionSupport;

@Service
public class PlayerServiceImpl extends BaseService implements PlayerService {
	@Resource
	private WebSessionSupport webSessionSupport;

	@Override
	public Player signIn(Player player) {
		if (checkExistBlank(player) || checkExistBlank(player.getUserName(), player.getPassword())) {
			return null;
		}
		Player queryOne = session.queryOne(new Player(player.getUserName(), player.getPassword()));
		if (queryOne != null) {
			// 回写sessionId
			queryOne.setSessionId(webSessionSupport.signIn(queryOne));
			Player bean = new Player();
			bean.setId(queryOne.getId());
			bean.setSessionId(queryOne.getSessionId());
			session.update(bean);
		}
		return queryOne;
	}

	@Override
	public Player getPlayerById(Integer id) {
		return this.session.queryOne(new Player(id));
	}

	/**
	 * topThree 3分钟缓存
	 */
	@Cache(key = "topThree", timeToLive = 3)
	@Override
	public List<Player> getTopThreePlayer() {
		return session.queryList("select p.userName,p.point,p.teamName from player p order by p.point desc limit 0,3",
				Player.class);
	}

	@Override
	public List<Player> getPlayersByTeamId(Integer id) {
		if (id == null) {
			return Collections.emptyList();
		}
		return this.session.queryList("select userName,point from player where teamId=?", Player.class, id);
	}

}

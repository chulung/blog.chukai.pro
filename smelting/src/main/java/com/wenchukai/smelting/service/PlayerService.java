package com.wenchukai.smelting.service;

import java.util.List;

import com.wenchukai.smelting.bean.Player;

public interface PlayerService {
	Player signIn(Player player);

	Player getPlayerById(Integer id);

	List<Player> getTopThreePlayer();
	
	List<Player> getPlayersByTeamId(Integer id);
}

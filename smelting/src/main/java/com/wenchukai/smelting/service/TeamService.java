package com.wenchukai.smelting.service;

import java.util.List;
import java.util.Optional;

import com.wenchukai.smelting.bean.PlayerTeam;

public interface TeamService {

	List<PlayerTeam> getAllTeams();

	PlayerTeam getTeamById(Integer id);

	List<PlayerTeam> getAllTeamsAndPlayer();

	boolean hasTeam(Optional<Integer> curPlayerId);

	void joinTeam(Integer id);

}

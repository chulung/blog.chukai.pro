package com.wck.smelting.service;

import java.util.List;
import java.util.Optional;

import com.wck.smelting.bean.PlayerTeam;

public interface TeamService {

	List<PlayerTeam> getAllTeams();

	PlayerTeam getTeamById(Integer id);

	List<PlayerTeam> getAllTeamsAndPlayer();

	boolean hasTeam(Optional<Integer> curPlayerId);

	void joinTeam(Integer id);

}

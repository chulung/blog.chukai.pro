package com.wck.smelting.service;

import java.util.List;
import java.util.Optional;

import com.wck.smelting.bean.PlayerGroup;

public interface GroupService {

	List<PlayerGroup> getAllGroups();

	PlayerGroup getGroupById(Integer id);

	List<PlayerGroup> getAllGroupsAndPlayer();

	boolean hasGroup(Optional<Integer> curPlayerId);

	void joinGroup(Integer id);

}

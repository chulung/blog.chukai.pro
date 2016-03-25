package com.wck.smelting.bean;

import java.util.List;

import javax.persistence.Id;

import com.wck.durable.annotation.Ignore;

public class PlayerGroup {
	@Id
	private Integer id;
	private String groupName;
	private Integer totalPoint;
	@Ignore
	private List<Player> players;

	public PlayerGroup() {
	}

	public PlayerGroup(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}

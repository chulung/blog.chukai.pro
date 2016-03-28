package com.wenchukai.smelting.bean;

import java.util.List;

import javax.persistence.Id;

import com.wenchukai.durable.annotation.Ignore;

public class PlayerTeam {
	@Id
	private Integer id;
	private String teamName;
	private Integer totalPoint;
	@Ignore
	private List<Player> players;

	@Ignore
	private List<TaskRecive> taskRecives;

	public PlayerTeam() {
	}

	public PlayerTeam(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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

	public List<TaskRecive> getTaskRecives() {
		return taskRecives;
	}

	public void setTaskRecives(List<TaskRecive> taskRecives) {
		this.taskRecives = taskRecives;
	}

}

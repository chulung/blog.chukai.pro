package com.wck.smelting.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Id;

import com.wck.durable.annotation.Ignore;

public class TaskRecive {
	@Id
	private Integer id;
	private Integer taskId;
	private Integer teamId;
	private Integer playerId;
	private String userName;
	private LocalDateTime createTime;
	private String teamName;
	private Integer completePoint;
	//发放积分 0未 1是
	private Integer giveOut;
	
	@Ignore
	private int[] pointAve;
	
	public TaskRecive() {
	}

	public TaskRecive(Integer taskId, Integer teamId, Integer playerId, LocalDateTime createTime) {
		this.taskId = taskId;
		this.teamId = teamId;
		this.playerId = playerId;
		this.createTime = createTime;
	}

	public TaskRecive(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getCompletePoint() {
		return completePoint;
	}

	public void setCompletePoint(Integer completePoint) {
		this.completePoint = completePoint;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGiveOut() {
		return giveOut;
	}

	public void setGiveOut(Integer giveOut) {
		this.giveOut = giveOut;
	}

	public int[] getPointAve() {
		return pointAve;
	}

	public void setPointAve(int[] pointAve) {
		this.pointAve = pointAve;
	}

}

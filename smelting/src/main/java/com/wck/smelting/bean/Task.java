package com.wck.smelting.bean;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.wck.durable.annotation.Ignore;

public class Task extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5218760327382949002L;
	@Id
	private Integer id;
	private String taskName;
	private String taskDesc;
	private Integer completePoint;
	private LocalDateTime createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	private Integer taskType;
	@Ignore
	private List<TaskRecive> taskRecives;

	public Task(Integer id) {
		this.id = id;
	}

	public Task() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public Integer getCompletePoint() {
		return completePoint;
	}

	public void setCompletePoint(Integer completePoint) {
		this.completePoint = completePoint;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public List<TaskRecive> getTaskRecives() {
		return taskRecives;
	}

	public void setTaskRecives(List<TaskRecive> taskRecives) {
		this.taskRecives = taskRecives;
	}

}

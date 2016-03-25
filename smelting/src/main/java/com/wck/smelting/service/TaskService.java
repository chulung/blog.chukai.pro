package com.wck.smelting.service;

import java.util.List;
import java.util.Map;

import com.wck.smelting.bean.Task;
import com.wck.smelting.bean.TaskRecive;

public interface TaskService {

	Task getTaskById(Integer id);

	Integer insert(Task task);

	List<Task> getCurTasks();

	List<Task> getExpireTasks();

	void receive(Integer id);

	Map<String, Object> getTaskRecive(Integer id);

}

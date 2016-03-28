package com.wenchukai.smelting.service;

import java.util.List;
import java.util.Map;

import com.wenchukai.smelting.bean.Task;
import com.wenchukai.smelting.bean.TaskRecive;

public interface TaskService {

	Task getTaskById(Integer id);

	Integer insert(Task task);

	List<Task> getCurTasks();

	List<Task> getExpireTasks();

	void receive(Integer id);

	Map<String, Object> getTaskRecive(Integer id);

}

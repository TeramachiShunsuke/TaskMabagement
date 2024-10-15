package com.example.taskmanagement.domain.port.inbound;

import com.example.taskmanagement.domain.model.Task;

import java.util.List;

public interface TaskUseCase {
    Task findTaskById(Long id);
    List<Task> findAllTasks();
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(Long id);
}

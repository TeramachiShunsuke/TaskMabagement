package com.example.taskmanagement.application.service;

import com.example.taskmanagement.domain.model.Task;
import com.example.taskmanagement.domain.port.inbound.TaskUseCase;
import com.example.taskmanagement.domain.port.outbound.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskUseCase {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task findTaskById(Long id) {
        Optional<Task>  task =  taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}

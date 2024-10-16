package com.example.taskmanagement.adapter.inbound.web.controller;

import com.example.taskmanagement.adapter.inbound.web.dto.TaskRequest;
import com.example.taskmanagement.domain.model.Task;
import com.example.taskmanagement.domain.port.inbound.TaskUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskUseCase taskUseCase;

    public TaskController(TaskUseCase taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = new Task(null,taskRequest.getTitle(),taskRequest.getDescription(), taskRequest.getStatus(), taskRequest.getAssignee(),taskRequest.isCompleted());
        Task createdTask = taskUseCase.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequest taskRequest) {
        Task updatedTask = new Task(id, taskRequest.getTitle(), taskRequest.getDescription(),taskRequest.getStatus(),taskRequest.getAssignee(), taskRequest.isCompleted());
        Task result = taskUseCase.updateTask(updatedTask);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskUseCase.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskUseCase.findTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskUseCase.findAllTasks();
        return ResponseEntity.ok(tasks);
    }
}
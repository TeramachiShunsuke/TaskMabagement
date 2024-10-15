package com.example.taskmanagement.adapter.outbound.persistence;

import com.example.taskmanagement.domain.model.Task;
import com.example.taskmanagement.domain.port.outbound.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryImpl(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = toEntity(task);
        TaskEntity savedEntity = taskJpaRepository.save(entity);
        return toModel(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        taskJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Task> findById(Long id) {
        Optional<TaskEntity> entity = taskJpaRepository.findById(id);
        return entity.map(this::toModel);
    }

    @Override
    public List<Task> findAll() {
        List<TaskEntity> entities = taskJpaRepository.findAll();
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    private TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setStatus(task.getStatus());
        entity.setAssignee(task.getAssignee());
        entity.setCompleted(task.isCompleted());
        return entity;
    }

    private Task toModel(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getAssignee(),
                entity.isCompleted()
        );
    }
}
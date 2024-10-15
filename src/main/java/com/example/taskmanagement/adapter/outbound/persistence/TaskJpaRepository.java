package com.example.taskmanagement.adapter.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository  extends JpaRepository<TaskEntity, Long> {
}

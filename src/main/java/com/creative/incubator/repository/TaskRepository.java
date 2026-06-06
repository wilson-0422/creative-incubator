package com.creative.incubator.repository;

import com.creative.incubator.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectIdOrderByCreatedAtDesc(Long projectId);
    List<Task> findByAssigneeIdOrderByCreatedAtDesc(Long assigneeId);
    List<Task> findAllByOrderByCreatedAtDesc();
    long countByStatus(String status);
    long countByProjectId(Long projectId);
}

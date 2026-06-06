package com.creative.incubator.service;

import com.creative.incubator.model.Task;
import com.creative.incubator.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> findByProjectId(Long projectId) {
        return taskRepository.findByProjectIdOrderByCreatedAtDesc(projectId);
    }

    public List<Task> findByAssigneeId(Long assigneeId) {
        return taskRepository.findByAssigneeIdOrderByCreatedAtDesc(assigneeId);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task updateStatus(Long id, String status) {
        Task task = findById(id);
        if (task != null) {
            task.setStatus(status);
            return taskRepository.save(task);
        }
        return null;
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public long countByStatus(String status) {
        return taskRepository.countByStatus(status);
    }

    public long countByProjectId(Long projectId) {
        return taskRepository.countByProjectId(projectId);
    }

    public long count() {
        return taskRepository.count();
    }
}

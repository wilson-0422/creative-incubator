package com.creative.incubator.service;

import com.creative.incubator.model.Project;
import com.creative.incubator.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAllByOrderByCreatedAtDesc();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> findByStatus(String status) {
        return projectRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public List<Project> findByManagerId(Long managerId) {
        return projectRepository.findByManagerIdOrderByCreatedAtDesc(managerId);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project updateStatus(Long id, String status) {
        Project project = findById(id);
        if (project != null) {
            project.setStatus(status);
            return projectRepository.save(project);
        }
        return null;
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    public long countByStatus(String status) {
        return projectRepository.countByStatus(status);
    }

    public long count() {
        return projectRepository.count();
    }
}

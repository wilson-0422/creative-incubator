package com.creative.incubator.repository;

import com.creative.incubator.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByManagerIdOrderByCreatedAtDesc(Long managerId);
    List<Project> findByStatusOrderByCreatedAtDesc(String status);
    List<Project> findAllByOrderByCreatedAtDesc();
    long countByStatus(String status);
}

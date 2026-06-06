package com.creative.incubator.repository;

import com.creative.incubator.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
    List<Archive> findAllByOrderByArchivedAtDesc();
    List<Archive> findByDepartmentOrderByArchivedAtDesc(String department);
}

package com.creative.incubator.service;

import com.creative.incubator.model.Archive;
import com.creative.incubator.model.Project;
import com.creative.incubator.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Autowired
    private ProjectService projectService;

    public List<Archive> findAll() {
        return archiveRepository.findAllByOrderByArchivedAtDesc();
    }

    public Archive findById(Long id) {
        return archiveRepository.findById(id).orElse(null);
    }

    public Archive archiveProject(Long projectId, String summary) {
        Project project = projectService.findById(projectId);
        if (project == null) {
            return null;
        }
        projectService.updateStatus(projectId, "COMPLETED");
        Archive archive = new Archive();
        archive.setProject(project);
        archive.setName(project.getName());
        archive.setSummary(summary);
        archive.setDepartment(project.getDepartment());
        return archiveRepository.save(archive);
    }

    public List<Archive> findByDepartment(String department) {
        return archiveRepository.findByDepartmentOrderByArchivedAtDesc(department);
    }

    public long count() {
        return archiveRepository.count();
    }
}

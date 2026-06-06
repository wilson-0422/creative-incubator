package com.creative.incubator.controller;

import com.creative.incubator.model.Project;
import com.creative.incubator.model.User;
import com.creative.incubator.service.ProjectService;
import com.creative.incubator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(@RequestParam(required = false) String status, Model model) {
        if (status != null && !status.isEmpty()) {
            model.addAttribute("projects", projectService.findByStatus(status));
        } else {
            model.addAttribute("projects", projectService.findAll());
        }
        model.addAttribute("currentStatus", status);
        return "projects/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        if (project == null) {
            return "redirect:/projects";
        }
        model.addAttribute("project", project);
        return "projects/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("users", userService.findAll());
        return "projects/create";
    }

    @PostMapping
    public String create(@ModelAttribute Project project,
                         @AuthenticationPrincipal UserDetails userDetails) {
        User manager = userService.findByUsername(userDetails.getUsername());
        project.setManager(manager);
        if (project.getStatus() == null || project.getStatus().isEmpty()) {
            project.setStatus("ACTIVE");
        }
        projectService.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id);
        if (project == null) {
            return "redirect:/projects";
        }
        model.addAttribute("project", project);
        model.addAttribute("users", userService.findAll());
        return "projects/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Project project) {
        Project existing = projectService.findById(id);
        if (existing == null) {
            return "redirect:/projects";
        }
        existing.setName(project.getName());
        existing.setDescription(project.getDescription());
        existing.setStatus(project.getStatus());
        existing.setStartDate(project.getStartDate());
        existing.setEndDate(project.getEndDate());
        projectService.save(existing);
        return "redirect:/projects/" + id;
    }
}

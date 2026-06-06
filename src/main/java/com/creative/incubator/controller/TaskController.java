package com.creative.incubator.controller;

import com.creative.incubator.model.Project;
import com.creative.incubator.model.Task;
import com.creative.incubator.model.User;
import com.creative.incubator.service.ProjectService;
import com.creative.incubator.service.TaskService;
import com.creative.incubator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(@RequestParam(required = false) Long projectId, Model model) {
        if (projectId != null) {
            model.addAttribute("tasks", taskService.findByProjectId(projectId));
            model.addAttribute("currentProjectId", projectId);
        } else {
            model.addAttribute("tasks", taskService.findAll());
        }
        model.addAttribute("projects", projectService.findAll());
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        if (task == null) {
            return "redirect:/tasks";
        }
        model.addAttribute("task", task);
        return "tasks/detail";
    }

    @GetMapping("/create")
    public String createForm(@RequestParam(required = false) Long projectId, Model model) {
        Task task = new Task();
        if (projectId != null) {
            Project project = projectService.findById(projectId);
            if (project != null) {
                task.setProject(project);
            }
        }
        model.addAttribute("task", task);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("users", userService.findAll());
        return "tasks/create";
    }

    @PostMapping
    public String create(@ModelAttribute Task task) {
        if (task.getStatus() == null || task.getStatus().isEmpty()) {
            task.setStatus("TODO");
        }
        taskService.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        taskService.updateStatus(id, status);
        return "redirect:/tasks/" + id;
    }
}

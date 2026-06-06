package com.creative.incubator.controller;

import com.creative.incubator.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/archives")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("archives", archiveService.findAll());
        return "archives/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("archive", archiveService.findById(id));
        return "archives/detail";
    }

    @PostMapping("/project/{projectId}")
    public String archiveProject(@PathVariable Long projectId,
                                 @RequestParam String summary) {
        archiveService.archiveProject(projectId, summary);
        return "redirect:/archives";
    }
}

package com.creative.incubator.controller;

import com.creative.incubator.dto.DashboardStats;
import com.creative.incubator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String overview(Model model) {
        DashboardStats stats = new DashboardStats();
        stats.setTotalProposals(proposalService.count());
        stats.setPendingReviews(proposalService.countByStatus("SUBMITTED") + proposalService.countByStatus("UNDER_REVIEW"));
        stats.setActiveProjects(projectService.countByStatus("ACTIVE"));
        stats.setCompletedTasks(taskService.countByStatus("DONE"));
        stats.setArchivedProjects(archiveService.count());
        stats.setTotalUsers(userService.findAll().size());

        model.addAttribute("stats", stats);
        model.addAttribute("recentProposals", proposalService.findAll().stream().limit(5).toList());
        model.addAttribute("activeProjects", projectService.findByStatus("ACTIVE").stream().limit(5).toList());
        model.addAttribute("pendingTasks", taskService.findAll().stream()
                .filter(t -> "TODO".equals(t.getStatus()) || "IN_PROGRESS".equals(t.getStatus()))
                .limit(5).toList());
        return "dashboard/overview";
    }
}

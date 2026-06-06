package com.creative.incubator.controller;

import com.creative.incubator.model.Proposal;
import com.creative.incubator.model.User;
import com.creative.incubator.service.ProposalService;
import com.creative.incubator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(@RequestParam(required = false) String status, Model model) {
        if (status != null && !status.isEmpty()) {
            model.addAttribute("proposals", proposalService.findByStatus(status));
        } else {
            model.addAttribute("proposals", proposalService.findAll());
        }
        model.addAttribute("currentStatus", status);
        return "proposals/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Proposal proposal = proposalService.findById(id);
        if (proposal == null) {
            return "redirect:/proposals";
        }
        model.addAttribute("proposal", proposal);
        return "proposals/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("proposal", new Proposal());
        return "proposals/create";
    }

    @PostMapping
    public String create(@ModelAttribute Proposal proposal,
                         @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        proposal.setProposer(user);
        proposal.setDepartment(user.getDepartment());
        if (proposal.getStatus() == null || proposal.getStatus().isEmpty()) {
            proposal.setStatus("DRAFT");
        }
        proposalService.save(proposal);
        return "redirect:/proposals";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Proposal proposal = proposalService.findById(id);
        if (proposal == null) {
            return "redirect:/proposals";
        }
        model.addAttribute("proposal", proposal);
        return "proposals/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Proposal proposal) {
        Proposal existing = proposalService.findById(id);
        if (existing == null) {
            return "redirect:/proposals";
        }
        existing.setTitle(proposal.getTitle());
        existing.setDescription(proposal.getDescription());
        existing.setCategory(proposal.getCategory());
        proposalService.save(existing);
        return "redirect:/proposals/" + id;
    }

    @PostMapping("/{id}/submit")
    public String submit(@PathVariable Long id) {
        proposalService.submit(id);
        return "redirect:/proposals/" + id;
    }
}

package com.creative.incubator.controller;

import com.creative.incubator.model.Proposal;
import com.creative.incubator.model.Review;
import com.creative.incubator.model.User;
import com.creative.incubator.service.ProposalService;
import com.creative.incubator.service.ReviewService;
import com.creative.incubator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "reviews/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Review review = reviewService.findById(id);
        if (review == null) {
            return "redirect:/reviews";
        }
        model.addAttribute("review", review);
        return "reviews/detail";
    }

    @GetMapping("/proposal/{proposalId}")
    public String reviewForm(@PathVariable Long proposalId, Model model) {
        Proposal proposal = proposalService.findById(proposalId);
        if (proposal == null) {
            return "redirect:/proposals";
        }
        model.addAttribute("proposal", proposal);
        model.addAttribute("review", new Review());
        return "reviews/review";
    }

    @PostMapping("/proposal/{proposalId}")
    public String submitReview(@PathVariable Long proposalId,
                               @ModelAttribute Review review,
                               @AuthenticationPrincipal UserDetails userDetails) {
        Proposal proposal = proposalService.findById(proposalId);
        User reviewer = userService.findByUsername(userDetails.getUsername());
        if (proposal == null || reviewer == null) {
            return "redirect:/proposals";
        }
        review.setProposal(proposal);
        review.setReviewer(reviewer);
        reviewService.save(review);
        return "redirect:/proposals/" + proposalId;
    }
}

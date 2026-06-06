package com.creative.incubator.service;

import com.creative.incubator.model.Review;
import com.creative.incubator.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProposalService proposalService;

    public List<Review> findAll() {
        return reviewRepository.findAllByOrderByCreatedAtDesc();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> findByProposalId(Long proposalId) {
        return reviewRepository.findByProposalIdOrderByCreatedAtDesc(proposalId);
    }

    public List<Review> findByReviewerId(Long reviewerId) {
        return reviewRepository.findByReviewerIdOrderByCreatedAtDesc(reviewerId);
    }

    public Review save(Review review) {
        Review saved = reviewRepository.save(review);
        updateProposalStatus(review);
        return saved;
    }

    private void updateProposalStatus(Review review) {
        if ("APPROVED".equals(review.getResult())) {
            List<Review> reviews = findByProposalId(review.getProposal().getId());
            boolean allApproved = reviews.stream().allMatch(r -> "APPROVED".equals(r.getResult()));
            if (allApproved && reviews.size() >= 2) {
                proposalService.updateStatus(review.getProposal().getId(), "APPROVED");
            }
        } else if ("REJECTED".equals(review.getResult())) {
            proposalService.updateStatus(review.getProposal().getId(), "REJECTED");
        } else {
            proposalService.updateStatus(review.getProposal().getId(), "UNDER_REVIEW");
        }
    }

    public long countByResult(String result) {
        return reviewRepository.countByResult(result);
    }
}

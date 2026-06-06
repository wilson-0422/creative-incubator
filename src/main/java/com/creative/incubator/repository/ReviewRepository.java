package com.creative.incubator.repository;

import com.creative.incubator.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProposalIdOrderByCreatedAtDesc(Long proposalId);
    List<Review> findByReviewerIdOrderByCreatedAtDesc(Long reviewerId);
    List<Review> findAllByOrderByCreatedAtDesc();
    long countByResult(String result);
}

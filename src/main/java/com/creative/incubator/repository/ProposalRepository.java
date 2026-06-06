package com.creative.incubator.repository;

import com.creative.incubator.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> findByProposerIdOrderByCreatedAtDesc(Long proposerId);
    List<Proposal> findByStatusOrderByCreatedAtDesc(String status);
    List<Proposal> findAllByOrderByCreatedAtDesc();
    long countByStatus(String status);
}

package com.creative.incubator.service;

import com.creative.incubator.model.Proposal;
import com.creative.incubator.model.User;
import com.creative.incubator.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    public List<Proposal> findAll() {
        return proposalRepository.findAllByOrderByCreatedAtDesc();
    }

    public Proposal findById(Long id) {
        return proposalRepository.findById(id).orElse(null);
    }

    public List<Proposal> findByProposer(User proposer) {
        return proposalRepository.findByProposerIdOrderByCreatedAtDesc(proposer.getId());
    }

    public List<Proposal> findByStatus(String status) {
        return proposalRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public Proposal save(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    public Proposal submit(Long id) {
        Proposal proposal = findById(id);
        if (proposal != null) {
            proposal.setStatus("SUBMITTED");
            return proposalRepository.save(proposal);
        }
        return null;
    }

    public Proposal updateStatus(Long id, String status) {
        Proposal proposal = findById(id);
        if (proposal != null) {
            proposal.setStatus(status);
            return proposalRepository.save(proposal);
        }
        return null;
    }

    public void deleteById(Long id) {
        proposalRepository.deleteById(id);
    }

    public long countByStatus(String status) {
        return proposalRepository.countByStatus(status);
    }

    public long count() {
        return proposalRepository.count();
    }
}

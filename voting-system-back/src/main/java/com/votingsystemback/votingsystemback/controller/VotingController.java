package com.votingsystemback.votingsystemback.controller;

import com.votingsystemback.votingsystemback.DTO.VoteRequest;
import com.votingsystemback.votingsystemback.Service.VotingService;
import com.votingsystemback.votingsystemback.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/voting")
public class VotingController {
    @Autowired
    private VotingService votingService;

  //  @GetMapping("/candidates")
   // public ResponseEntity<List<Voting.Candidate>> getAllVotesOfCandidates() {
     //   try {
       //     List<Voting.Candidate> candidates = votingService.getAllVotesOfCandidates();
     //       return ResponseEntity.ok(candidates);
       // } catch (Exception e) {
        //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      //  }
   // }

    @PostMapping("/vote")
    public ResponseEntity<String> vote(@RequestBody VoteRequest request) {
        try {
            String transactionHash = votingService.vote(request.getCandidateIndex());
            return ResponseEntity.ok(transactionHash);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/status")
    public ResponseEntity<Boolean> getVotingStatus() {
        try {
            boolean status = votingService.getVotingStatus();
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/remaining-time")
    public ResponseEntity<BigInteger> getRemainingTime() {
        try {
            BigInteger remainingTime = votingService.getRemainingTime();
            return ResponseEntity.ok(remainingTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Additional endpoints for voting, getting voting status, etc.
}
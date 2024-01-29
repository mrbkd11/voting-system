package com.votingsystemback.votingsystemback.DTO;

import java.math.BigInteger;

public class VoteRequest {

    private BigInteger candidateIndex;

    // Getters and setters
    public BigInteger getCandidateIndex() {
        return candidateIndex;
    }

    public void setCandidateIndex(BigInteger candidateIndex) {
        this.candidateIndex = candidateIndex;
    }
}

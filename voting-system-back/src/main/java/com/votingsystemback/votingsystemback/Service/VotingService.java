package com.votingsystemback.votingsystemback.Service;

import com.votingsystemback.votingsystemback.Voting;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
public class VotingService {
    private Web3j web3j;
    private Credentials credentials;
    private ContractGasProvider contractGasProvider;

    @Value("${eth.client.url}")
    private String ethClientUrl;

    @Value("${contract.address}")
    private String contractAddress;

    @Value("${wallet.private.key}")
    private String privateKey;

    @Value("${contract.gas.price}")
    private BigInteger gasPrice;

    @Value("${contract.gas.limit}")
    private BigInteger gasLimit;

    // Initialize Web3j, Credentials, and ContractGasProvider after properties are set
    @PostConstruct
    public void init() {
        this.web3j = Web3j.build(new HttpService(ethClientUrl));
        this.credentials = Credentials.create(privateKey);
        this.contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
    }

    public String vote(BigInteger candidateIndex) throws Exception {
        Voting voting = Voting.load(contractAddress, web3j, credentials, contractGasProvider);
        TransactionReceipt transactionReceipt = voting.vote(candidateIndex).send();
        return transactionReceipt.getTransactionHash();
    }

    public boolean getVotingStatus() throws Exception {
        Voting voting = Voting.load(contractAddress, web3j, credentials, contractGasProvider);
        return voting.getVotingStatus().send();
    }

    public BigInteger getRemainingTime() throws Exception {
        Voting voting = Voting.load(contractAddress, web3j, credentials, contractGasProvider);
        return voting.getRemainingTime().send();
    }

    // Additional methods for voting, getting voting status, etc.
}

package com.votingsystemback.votingsystemback.Service;

import com.votingsystemback.votingsystemback.SimpleStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

@Service
public class SimpleStorageService {

    @Value("${eth.client.url}")
    private String ethClientUrl;

    @Value("${contract.address}")
    private String contractAddress;

    @Value("${wallet.private.key}")
    private String privateKey;

    private SimpleStorage loadContract() {
        Web3j web3j = Web3j.build(new HttpService(ethClientUrl));
        Credentials credentials = Credentials.create(privateKey);
        return SimpleStorage.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    public BigInteger getValue() throws Exception {
        SimpleStorage contract = loadContract();
        return contract.get().send();
    }

    public String setValue(BigInteger value) throws Exception {
        SimpleStorage contract = loadContract();
        TransactionReceipt transactionReceipt = contract.set(value).send();
        return transactionReceipt.getTransactionHash();
    }
}
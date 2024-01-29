package com.votingsystemback.votingsystemback.controller;


import com.votingsystemback.votingsystemback.Service.SimpleStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/simplestorage")
public class SimpleStorageController {

    private final SimpleStorageService simpleStorageService;

    @Autowired
    public SimpleStorageController(SimpleStorageService simpleStorageService) {
        this.simpleStorageService = simpleStorageService;
    }

    @GetMapping("/value")
    public BigInteger getValue() throws Exception {
        return simpleStorageService.getValue();
    }

    @PostMapping("/value")
    public String setValue(@RequestBody BigInteger value) throws Exception {
        return simpleStorageService.setValue(value);
    }
}

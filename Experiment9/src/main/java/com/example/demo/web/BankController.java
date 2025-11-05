package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.partc.service.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest req) {
        try {
            bankService.transfer(req.getFromId(), req.getToId(), req.getAmount(), req.isFailAfterDebit());
            return ResponseEntity.ok("Transfer completed");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Transfer failed: " + ex.getMessage());
        }
    }
}

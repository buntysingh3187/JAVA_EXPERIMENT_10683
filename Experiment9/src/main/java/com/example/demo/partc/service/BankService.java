package com.example.demo.partc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.partc.dao.AccountDao;
import com.example.demo.partc.entity.Account;

@Service
public class BankService {

    @Autowired
    private AccountDao dao;

    @Transactional
    public void transfer(Long fromId, Long toId, double amount, boolean failAfterDebit) {
        Account from = dao.find(fromId);
        Account to = dao.find(toId);
        if (from == null || to == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // debit
        from.setBalance(from.getBalance() - amount);
        dao.save(from);

        // optionally fail to demonstrate rollback
        if (failAfterDebit) {
            throw new RuntimeException("Simulated failure after debit — should trigger rollback");
        }

        // credit
        to.setBalance(to.getBalance() + amount);
        dao.save(to);
    }
}

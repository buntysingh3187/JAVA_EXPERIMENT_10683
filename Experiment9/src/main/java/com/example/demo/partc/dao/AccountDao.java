package com.example.demo.partc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.partc.entity.Account;

@Repository
public class AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Account save(Account a) {
        currentSession().saveOrUpdate(a);
        return a;
    }

    public Account find(Long id) {
        return currentSession().get(Account.class, id);
    }
}

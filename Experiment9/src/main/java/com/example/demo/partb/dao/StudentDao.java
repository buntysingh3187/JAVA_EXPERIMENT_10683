package com.example.demo.partb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.partb.entity.Student;

@Repository
public class StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Student save(Student s) {
        currentSession().saveOrUpdate(s);
        return s;
    }

    public Student find(Long id) {
        return currentSession().get(Student.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Student> listAll() {
        return currentSession().createQuery("from Student").list();
    }

    public void delete(Long id) {
        Student s = find(id);
        if (s != null) currentSession().delete(s);
    }
}

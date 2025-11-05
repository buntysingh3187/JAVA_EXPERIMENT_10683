package com.example.demo.partb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.partb.dao.StudentDao;
import com.example.demo.partb.entity.Student;

@Service
public class StudentService {

    @Autowired
    private StudentDao dao;

    @Transactional
    public Student create(Student s) {
        return dao.save(s);
    }

    @Transactional(readOnly = true)
    public Student get(Long id) {
        return dao.find(id);
    }

    @Transactional(readOnly = true)
    public List<Student> list() {
        return dao.listAll();
    }

    @Transactional
    public Student update(Student s) {
        return dao.save(s);
    }

    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }
}

package com.example.demo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.partb.entity.Student;
import com.example.demo.partb.service.StudentService;
import com.example.demo.parta.StudentDI;
import com.example.demo.partc.entity.Account;
import com.example.demo.partc.service.BankService;

public class DemoRunner {
    public static void main(String[] args) {
        // Load Spring context (reads AppConfig and application.properties)
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {

            System.out.println("\n--- Part A: Dependency Injection using Java-based @Configuration ---");
            StudentDI studentDI = ctx.getBean(StudentDI.class);
            System.out.println(studentDI);
            studentDI.study();

            System.out.println("\n--- Part B: Hibernate CRUD demo ---");
            StudentService ss = ctx.getBean(StudentService.class);

            // Create student
            Student s = new Student("Bob", "bob@example.com", 21);
            ss.create(s);
            System.out.println("Created: " + s);

            // List
            List<Student> all = ss.list();
            System.out.println("All students:");
            all.forEach(System.out::println);

            // Update
            if (!all.isEmpty()) {
                Student first = all.get(0);
                first.setAge(first.getAge() + 1);
                ss.update(first);
                System.out.println("Updated: " + ss.get(first.getId()));
            }

            // Delete (commented out by default)
            // if (s.getId() != null) ss.delete(s.getId());

            System.out.println("\n--- Part C: Transaction demo (transfer) ---");
            BankService bank = ctx.getBean(BankService.class);

            // create two accounts in a transaction boundary
            org.hibernate.SessionFactory sf = ctx.getBean(org.hibernate.SessionFactory.class);
            org.hibernate.Session session = sf.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            try {
                Account a1 = new Account("Carol", 500.0);
                Account a2 = new Account("Dave", 200.0);
                session.save(a1);
                session.save(a2);
                tx.commit();
                System.out.println("Created accounts: " + a1 + ", " + a2);

                // perform a failing transfer to show rollback
                try {
                    bank.transfer(a1.getId(), a2.getId(), 100.0, true);
                } catch (Exception ex) {
                    System.out.println("Transfer failed as expected: " + ex.getMessage());
                }

                // verify balances unchanged (read within transaction)
                session = sf.openSession();
                org.hibernate.query.Query<Account> q = session.createQuery("from com.example.demo.partc.entity.Account where owner in (:o1, :o2)", Account.class);
                q.setParameter("o1", "Carol");
                q.setParameter("o2", "Dave");
                q.list().forEach(System.out::println);
                session.close();

                // perform a successful transfer
                bank.transfer(a1.getId(), a2.getId(), 50.0, false);
                session = sf.openSession();
                q = session.createQuery("from com.example.demo.partc.entity.Account where owner in (:o1, :o2)", Account.class);
                q.setParameter("o1", "Carol");
                q.setParameter("o2", "Dave");
                q.list().forEach(System.out::println);
                session.close();

            } finally {
                if (session != null && session.isOpen()) session.close();
            }

            System.out.println("\nDemo finished.");
        }
    }
}

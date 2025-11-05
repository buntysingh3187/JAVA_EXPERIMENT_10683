# Spring + Hibernate demo

This project demonstrates three things:

- Part A: Spring Dependency Injection using Java-based configuration.
- Part B: Hibernate CRUD operations for a `Student` entity using Spring-managed SessionFactory.
- Part C: Transaction management with Spring + Hibernate: a simple banking transfer example using @Transactional.

Prerequisites
- Java 11 or newer
- Maven
- MySQL server

Setup
1. Create a MySQL database and user. Example SQL:

```sql
CREATE DATABASE demo_db;
CREATE USER 'demo_user'@'localhost' IDENTIFIED BY 'demo_pass';
GRANT ALL PRIVILEGES ON demo_db.* TO 'demo_user'@'localhost';
FLUSH PRIVILEGES;
```

2. Edit `src/main/resources/application.properties` and set your DB URL, username and password.

3. Build and run the project:

On Windows (cmd.exe):

```
mvn -q package
java -cp target\spring-hibernate-demo-1.0-SNAPSHOT.jar;target\dependency\* com.example.demo.DemoRunner
```

Alternatively run from your IDE.

Notes
- The demo's main runner will show Part A (DI), Part B (CRUD), and Part C (transaction) examples sequentially.
- For Part C there is a deliberate thrown exception to show rollback behaviour — change code to see a successful transfer.

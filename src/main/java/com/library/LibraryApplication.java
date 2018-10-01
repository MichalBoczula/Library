package com.library;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@EnableJpaAuditing
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    public static void connecctToH2() throws SQLException {
//        Connection conn = DriverManager.
//                getConnection("jdbc:h2:~/test");
//        conn.close();
//        final JdbcConnectionPool cp = JdbcConnectionPool.
//                create("jdbc:h2:~/test", "sa", "sa");
//        Connection conn = cp.getConnection();
//        conn.close(); cp.dispose();
    }
}

package com.javarush.alimova.configure;

import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hibernate.cfg.JdbcSettings.*;

public class Cnn {

    private final ApplicationProperties properties;

    public Cnn(ApplicationProperties properties) {
        this.properties = properties;
        try {
            Class.forName(properties.getProperty(JAKARTA_JDBC_DRIVER));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection get() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty(JAKARTA_JDBC_URL),
                    properties.getProperty(JAKARTA_JDBC_USER),
                    properties.getProperty(JAKARTA_JDBC_PASSWORD)
            );
        } catch (SQLException e) {
            throw new RuntimeException("failed connection", e);
        }
    }
}

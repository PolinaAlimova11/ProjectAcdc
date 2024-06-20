package com.javarush.alimova;

import com.javarush.alimova.configure.ApplicationProperties;
import com.javarush.alimova.configure.ConfigurationDB;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.SessionCreator;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigurationIT {

    public static final String CHANGELOG_FILE = "db/changelog_test.xml";
    public static final String CHANGELOG_FILE_NAME = "CHANGELOG_FILE";

    private static final JdbcDatabaseContainer<?> CONTAINER;

    public static final String DOCKER_IMAGE_NAME = "postgres:16.3";

    protected final ConfigurationDB configurationDB;
    protected static final ApplicationProperties applicationProperties;
    protected final SessionCreator sessionCreator;

    static {
        CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME);
        CONTAINER.start();

        applicationProperties = Creator.getComponent(ApplicationProperties.class);
        applicationProperties.setProperty(CHANGELOG_FILE_NAME, CHANGELOG_FILE);
        applicationProperties.setProperty(JAKARTA_JDBC_URL, CONTAINER.getJdbcUrl());
        applicationProperties.setProperty(JAKARTA_JDBC_USER, CONTAINER.getUsername());
        applicationProperties.setProperty(JAKARTA_JDBC_PASSWORD, CONTAINER.getPassword());
    }

    public ConfigurationIT() {

        configurationDB = Creator.getComponent(ConfigurationDB.class);
        try {
            configurationDB.startInitDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sessionCreator = Creator.getComponent(SessionCreator.class);
    }

    @Test
    void testSessionCreator() {
        SessionCreator sessionCreator = Creator.getComponent(SessionCreator.class);
        assertNotNull(sessionCreator);
    }
}

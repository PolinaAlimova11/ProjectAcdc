package com.javarush.alimova;

import com.javarush.alimova.configure.ApplicationProperties;
import com.javarush.alimova.configure.ConfigurationDB;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.SessionCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigurationIT {

    public static final String CHANGELOG_FILE = "db/changelog_test.xml";
    public static final String CHANGELOG_FILE_NAME = "CHANGELOG_FILE";

    protected final ConfigurationDB configurationDB;
    protected final ApplicationProperties applicationProperties;
    protected final SessionCreator sessionCreator;

//    static {
//        ApplicationProperties properties = Creator.getComponent(ApplicationProperties.class);
//        properties.setProperty(CHANGELOG_FILE_NAME, CHANGELOG_FILE);
//    }

    public ConfigurationIT() {
        applicationProperties = Creator.getComponent(ApplicationProperties.class);
        applicationProperties.setProperty(CHANGELOG_FILE_NAME, CHANGELOG_FILE);
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

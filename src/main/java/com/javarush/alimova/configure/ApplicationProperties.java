package com.javarush.alimova.configure;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties extends Properties {

    public static final String CHANGELOG_FILE = "db/changelog.xml";
    public static final String CHANGELOG_FILE_NAME = "CHANGELOG_FILE";


    public ApplicationProperties() {
        try {
            this.load(ApplicationProperties.class.getResourceAsStream("/application.properties"));
            this.setProperty(CHANGELOG_FILE_NAME, CHANGELOG_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

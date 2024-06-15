package com.javarush.alimova.configure;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties extends Properties {

    public ApplicationProperties() {
        try {
            this.load(ApplicationProperties.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.javarush.alimova.configure;

import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;

import static org.hibernate.cfg.JdbcSettings.*;

@AllArgsConstructor
public class ConfigurationDB {

    public static final String CHANGELOG_FILE_NAME = "CHANGELOG_FILE";
    public final ApplicationProperties properties;
    public void startInitDB() throws Exception {
        System.out.println("Running Liquibase...");

        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
            CommandScope update = new CommandScope("update");

            update.addArgumentValue("changelogFile", properties.getProperty(CHANGELOG_FILE_NAME));
            update.addArgumentValue("url", properties.getProperty(JAKARTA_JDBC_URL));
            update.addArgumentValue("username", properties.getProperty(JAKARTA_JDBC_USER));
            update.addArgumentValue("password", properties.getProperty(JAKARTA_JDBC_PASSWORD));
            update.addArgumentValue("driver", properties.getProperty(JAKARTA_JDBC_DRIVER));

            update.execute();
        });

        System.out.println("Running Liquibase...DONE");

    }
}

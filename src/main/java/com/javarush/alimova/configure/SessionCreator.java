package com.javarush.alimova.configure;

import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.StepAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreator {
    private final ApplicationProperties applicationProperties;
    private final SessionFactory sessionFactory;
    private final ThreadLocal<Session> sessionBox = new ThreadLocal<>();

    public SessionCreator(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        Configuration configuration = new Configuration();
        configuration.addProperties(applicationProperties);
        configuration.addAnnotatedClass(StepAction.class);
        configuration.addAnnotatedClass(Action.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionBox.get() == null || !sessionBox.get().isOpen()
                ? sessionFactory.openSession()
                : sessionBox.get();
    }

    public void close() {
        sessionFactory.close();
    }
}

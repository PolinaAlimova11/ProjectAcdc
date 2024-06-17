package com.javarush.alimova.configure;

import com.javarush.alimova.entity.*;
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
        configuration.addAnnotatedClass(Point.class);
        configuration.addAnnotatedClass(Quest.class);
        configuration.addAnnotatedClass(PointAction.class);
        configuration.addAnnotatedClass(QuestFirstPoint.class);
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

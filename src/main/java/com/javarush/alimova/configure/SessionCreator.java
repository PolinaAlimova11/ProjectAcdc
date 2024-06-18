package com.javarush.alimova.configure;

import com.javarush.alimova.entity.*;
import com.javarush.alimova.exception.ConnectionDBException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SessionCreator {
    private final ApplicationProperties applicationProperties;
    private final SessionFactory sessionFactory;
    private final ThreadLocal<Session> sessionBox = new ThreadLocal<>();
    private final ThreadLocal<AtomicInteger> levelBox = new ThreadLocal<>();

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

    public void beginTransactional() {
        if (levelBox.get() == null) {
            levelBox.set(new AtomicInteger(0));
        }
        AtomicInteger level = levelBox.get();
        if (level.getAndIncrement() == 0) {
            Session session = getSession();
            sessionBox.set(session);
            session.beginTransaction();
        }
        log.info(">>> start level: {} session={}", level.get(), sessionBox.get());
    }


    public void endTransactional() {
        AtomicInteger level = levelBox.get();
        Session session = sessionBox.get();
        log.info("\t\tcheck tx: {} session={}", level.get(), session);

        if (level.decrementAndGet() == 0) {
            sessionBox.remove();
            try {
                session.getTransaction().commit();
                session.close();
            } catch (Exception e) {
                session.getTransaction().rollback();
                session.close();
                throw new ConnectionDBException();
            }
        }
        log.info("<<< end level: {} session={}", level.get(), session);

    }

    public void close() {
        sessionFactory.close();
    }
}

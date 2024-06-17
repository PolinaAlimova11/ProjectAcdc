package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.StepAction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.stream.Stream;

public class StepActionRepository extends BaseRepository<StepAction>{

    public StepActionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, StepAction.class);
    }

    public Stream<StepAction> getByActionOrderBySerialNumberAsc(Action action) {
        try (Session session = super.getSessionCreator().getSession()) {
            Transaction tx = session.beginTransaction();
            Query<StepAction> query2 = session.createQuery("select s from StepAction s where s.action = :action order by s.serialNumber asc", StepAction.class);
            query2.setParameter("action", action);
            tx.commit();
            return query2.stream();
        }
    }
}

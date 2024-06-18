package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.entity.StepAction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.stream.Stream;

public class StepActionRepository extends BaseRepository<StepAction>{

    public StepActionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, StepAction.class);
    }

    public Stream<StepAction> getByActionOrderBySerialNumberAsc(Long actionId) {
        Session session = sessionCreator.getSession();
        Query<StepAction> query2 = session.createQuery("select s from StepAction s where s.action.id = :actionId order by s.serialNumber asc", StepAction.class);
        query2.setParameter("actionId", actionId);
        return query2.stream();
    }

    public String getNumberStepByAction(Long actionId, long number) {
        Session session = sessionCreator.getSession();
        Query<String> query = session.createQuery(
                "select sa.description from StepAction sa where sa.action.id =:actionId and sa.serialNumber = :number", String.class);
        query.setParameter("actionId", actionId);
        query.setParameter("number", number);
        return query.uniqueResult();

    }
}

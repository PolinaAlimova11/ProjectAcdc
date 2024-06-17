package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.entity.QuestFirstPoint;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class QuestFirstPointRepository extends BaseRepository<QuestFirstPoint> {
    public QuestFirstPointRepository(SessionCreator sessionCreator) {
        super(sessionCreator, QuestFirstPoint.class);
    }

    public Long getIdFirstPointByQuest(Long id) {
        Session session = super.getSessionCreator().getSession();
        Query<Long> query = session.createQuery("select qp.point.id from QuestFirstPoint qp where qp.quest.id = :id", Long.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }
}

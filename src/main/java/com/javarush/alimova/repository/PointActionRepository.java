package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.entity.PointAction;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PointActionRepository extends BaseRepository<PointAction>{

    public PointActionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, PointAction.class);
    }
    public Long getIdNextPointByAction(Long id) {
        Session session = sessionCreator.getSession();
        Query<Long> query = session.createQuery("select pa.nextPoint.id from PointAction pa where pa.action.id = :id", Long.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }
}

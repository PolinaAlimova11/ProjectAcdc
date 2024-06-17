package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public abstract class BaseRepository<T> implements Repository<T>{

    private final SessionCreator sessionCreator;
    private final Class<T> entityClass;


    @Override
    public Stream<T> getAll() {
            Session session = sessionCreator.getSession();
            Query<T> query = session.createQuery(
                    "select e from %s e".formatted(entityClass.getName(), entityClass));
            return query.stream();

    }

    @Override
    public Optional<T> create(T value) {
        try(Session session = sessionCreator.getSession()) {
            session.persist(value);
            return Optional.of(value);
        }
    }

    @Override
    public Optional<T> delete(T value) {
        Session session = sessionCreator.getSession();
        session.remove(value);
        return Optional.of(value);
    }

    @Override
    public Optional<T> update(T value) {
        Session session = sessionCreator.getSession();
        session.refresh(value);
        return Optional.of(value);

    }

    @Override
    public Optional<T> get(Long index) {
        Session session = sessionCreator.getSession();
        T result = session.find(entityClass, index);
        return Optional.of(result);
    }

    @Override
    public boolean isEmpty() {
        Session session = sessionCreator.getSession();
        Query<Long> query = session.createQuery(
                "select count(e) from %s e".formatted(entityClass.getName(), Long.class));
        return query.uniqueResult() == 0;
    }
}

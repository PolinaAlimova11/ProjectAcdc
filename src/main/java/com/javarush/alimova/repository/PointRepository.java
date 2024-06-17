package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.entity.Point;

public class PointRepository extends BaseRepository<Point> {

    public PointRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Point.class);
    }
}

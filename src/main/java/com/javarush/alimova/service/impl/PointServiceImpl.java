package com.javarush.alimova.service.impl;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.dto.PointDto;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.Point;
import com.javarush.alimova.exception.ConnectionDBException;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.PointActionRepository;
import com.javarush.alimova.repository.PointRepository;
import com.javarush.alimova.repository.StepActionRepository;
import com.javarush.alimova.service.PointService;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final SessionCreator sessionCreator;
    private final StepActionRepository stepActionRepository;
    public static final long NUMBER_STEP = 1L;
    public static final Logger log = LoggerFactory.getLogger(QuestServiceImpl.class);

    @Override
    public Optional<PointDto> getPoint(Long id) {
        try(Session session = sessionCreator.getSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Optional<Point> pointOptional = pointRepository.get(id);
                Optional<PointDto> pointDto = Optional.empty();
                if (pointOptional.isPresent()) {
                    Map<String, Long> listAction = new HashMap<>();
                    for(Action action : pointOptional.get().getActionList()) {
                        String description = stepActionRepository.getNumberStepByAction(action.getId(), NUMBER_STEP);
                        listAction.put(description, action.getId());
                    }
                    pointDto = pointOptional.map(x -> MapperDto.MAPPER.from(x, listAction));
                }
                tx.commit();
                return pointDto;
            } catch (Exception e) {
                tx.rollback();
                log.error(String.format("Connection drop in method getPoint(id = %d)", id));
                throw new ConnectionDBException(e);
            }

        }
    }
}

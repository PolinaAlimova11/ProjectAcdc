package com.javarush.alimova.service.impl;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.StepAction;
import com.javarush.alimova.exception.ConnectionDBException;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.ActionRepository;
import com.javarush.alimova.repository.PointActionRepository;
import com.javarush.alimova.repository.StepActionRepository;
import com.javarush.alimova.service.ActionService;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;
    private final StepActionRepository stepActionRepository;
    private final PointActionRepository pointActionRepository;
    private final SessionCreator sessionCreator;

    public static final Logger log = LoggerFactory.getLogger(QuestServiceImpl.class);


    @Override
    public Optional<ActionDto> getByIdWithStepAction(Long id) {

        try(Session session = sessionCreator.getSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Optional<Action> actionOptional = actionRepository.get(id);
                Optional<ActionDto> actionDto = Optional.empty();
                if (actionOptional.isPresent()) {
                    Stream<StepAction> stepActionStream = stepActionRepository.getByActionOrderBySerialNumberAsc(actionOptional.get().getId());
                    Long nextPoint = pointActionRepository.getIdNextPointByAction(id);
                    actionDto = actionOptional.map(x -> MapperDto.MAPPER.from(x,
                            stepActionStream.map(StepAction::getDescription).toList(), nextPoint));
                }
                tx.commit();
                return actionDto;
            } catch (Exception e) {
                tx.rollback();
                log.error(String.format("Connection drop in method getByIdWithStepAction(id = %d)", id));
                throw new ConnectionDBException(e);
            }

        }
    }
}

package com.javarush.alimova;

import com.javarush.alimova.configure.ApplicationProperties;
import com.javarush.alimova.configure.ConfigurationDB;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.StepAction;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.ActionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class QuestApplication {
    public static void main(String[] args) throws Exception {
//        ConfigurationDB configurationDB = Creator.getComponent(ConfigurationDB.class);
//        configurationDB.startInitDB();

        ApplicationProperties applicationProperties = Creator.getComponent(ApplicationProperties.class);
        SessionCreator sessionCreator = Creator.getComponent(SessionCreator.class);
        ActionRepository actionRepository = Creator.getComponent(ActionRepository.class);

//        try(Session session = sessionCreator.getSession()) {
//            Transaction tx = session.beginTransaction();
//            Query<StepAction> query = session.createQuery("select s from StepAction s", StepAction.class);
//            List<StepAction> stepActionList = query.getResultList();
//            tx.commit();
//            for(StepAction action : stepActionList) {
//                System.out.println(action.getId()+ " " + action.getDescription() + " " + action.getAction().getStatusPoint());
//            }
//
//            tx = session.beginTransaction();
//            Query<Action> query1 = session.createQuery("select a from Action a", Action.class);
//            List<Action> actionList = query1.getResultList();
//            tx.commit();
//            for(Action action : actionList) {
//                System.out.println(action.getStatusPoint());
//                for(StepAction stepAction : action.getStepActionList()) {
//                    System.out.println(stepAction.getDescription() + " " + stepAction.getSerialNumber());
//                }
//            }
//        }
//        try(Session session = sessionCreator.getSession()) {
////            Query<StepAction> query = session.createQuery("select s from StepAction s", StepAction.class);
////            List<StepAction> stepActionList = query.getResultList();
//
//            Query<Action> query = session.createQuery("select a from Action a where id = :id", Action.class);
//            query.setParameter("id", 1);
//            Optional<Action> action = query.uniqueResultOptional();
//
//            Query<StepAction> query2 = session.createQuery("select s from StepAction s where s.action = :action order by s.serialNumber asc", StepAction.class);
//            query2.setParameter("action", action.get());
//            Stream<StepAction> stepActionList = query2.stream();
//
//            ActionDto dto = MapperDto.MAPPER.from(action.get(),
//                    stepActionList.map(StepAction::getDescription).toList(), 1L);
//
//            System.out.println(dto);
//
//        }

        try (Session session = sessionCreator.getSession()) {
            Transaction tx = session.beginTransaction();
//            List<Action> actionList = actionRepository.getAll().toList();
//            for(Action action : actionList) {
//                System.out.println(action.getStatusPoint() +  " " + action.getId());
//            }

            Action action = actionRepository.getById(1L).get();
            System.out.println(action.getId());

            Action action2 = actionRepository.get(1L).get();
            System.out.println(action2.getId() + " " + action2.getStepActionList());

            System.out.println(actionRepository.isEmpty());

            tx.commit();
        }


    }
}

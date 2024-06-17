package com.javarush.alimova.controller;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.dto.StatisticQuest;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.service.QuestService;
import com.javarush.alimova.service.impl.QuestServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

@WebServlet("/quests")
public class QuestMenuServlet extends HttpServlet {

    private final QuestService questService = Creator.getComponent(QuestServiceImpl.class);
    public static final Logger log = LoggerFactory.getLogger(QuestServiceImpl.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<QuestDto> quests = questService.getAll();
        req.setAttribute("quests", quests);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest_menu.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Long idQuest = Long.valueOf(req.getParameter("questId"));
        Optional<QuestDto> quest = questService.getQuest(idQuest);

        if (quest.isPresent()) {
            QuestDto questDto = quest.get();
            session.setAttribute("currentQuest", questDto);
            session.setAttribute("title", questDto.getTitle());
            session.setAttribute("description", questDto.getDescription());

            log.info(String.format("Choose quest %s (id: %d)", questDto.getTitle(), questDto.getId()));


            Map<Long, StatisticQuest> statistic = (Map<Long, StatisticQuest>) session.getAttribute("statistic");
            if (Objects.isNull(statistic.get(idQuest))) {
                statistic.put(idQuest,
                        new StatisticQuest(questDto.getTitle(), 0, null));
            }
            req.setAttribute("pointId", questDto.getStartIdPoint());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest_start.jsp");
        requestDispatcher.forward(req, resp);

    }
}

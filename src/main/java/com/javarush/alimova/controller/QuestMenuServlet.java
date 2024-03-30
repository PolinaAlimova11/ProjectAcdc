package com.javarush.alimova.controller;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.dto.StatisticQuest;
import com.javarush.alimova.entity.Quest;
import com.javarush.alimova.service.QuestService;
import com.javarush.alimova.service.impl.QuestServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebServlet("/quests")
public class QuestMenuServlet extends HttpServlet {

    private final QuestService questService = Creator.getComponent(QuestServiceImpl.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        questService.initBaseQuest();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();

        Collection<Quest> quests = questService.getAll();
        req.setAttribute("quests", quests);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest_menu.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Long idQuest = Long.valueOf(req.getParameter("questId"));
        Quest quest = questService.getQuest(idQuest);

        session.setAttribute("currentQuest", quest);
        session.setAttribute("title", quest.getTitle());
        session.setAttribute("description", quest.getDescription());

        Map<Long, StatisticQuest> statistic = (Map<Long, StatisticQuest>) session.getAttribute("statistic");
        if(Objects.isNull(statistic.get(idQuest))) {
            statistic.put(idQuest,
                    new StatisticQuest(quest.getTitle(), 0, null));
        }
        req.setAttribute("pointId", quest.getStartIdPoint());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest_start.jsp");
        requestDispatcher.forward(req, resp);

    }
}

package com.javarush.alimova.controller;

import com.javarush.alimova.configure.Creator;
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

import java.io.IOException;
import java.util.Collection;

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
        Collection<Quest> quests = questService.getAll();
        String idString = req.getQueryString();
        Long id = (Long)req.getAttribute("questId");
        req.setAttribute("quests", quests);
        req.setAttribute("nameUser", "Oleg");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest_menu.jsp");
        requestDispatcher.forward(req, resp);
    }
}

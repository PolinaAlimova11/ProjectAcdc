package com.javarush.alimova.controller;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.dto.PointDto;
import com.javarush.alimova.entity.Quest;
import com.javarush.alimova.service.QuestService;
import com.javarush.alimova.service.impl.QuestServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet("/game")
public class QuestGameServlet extends HttpServlet {

    private final QuestService questService = Creator.getComponent(QuestServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("questId"));
        Quest quest = questService.getQuest(id);
        req.setAttribute("id", id);
        req.setAttribute("title", quest.getTitle());
        req.setAttribute("discription", quest.getDescription());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest_start.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idQuest") != null) {
            //это если только начало квеста?
            //лучше сделать начало квеста через поинт
            Long idQuest = Long.valueOf(req.getParameter("idQuest"));
            Quest currentQuest = questService.getQuest(idQuest);
            Map<Long, PointDto> pointDtoMap = currentQuest.getListPoint();
            //сделать через сервис?
            //тут проверку, какой point доставать (вот это можно спрятать в сервис)
            PointDto currentPoint = pointDtoMap.get(currentQuest.getStartIdPoint());

            //нужно протягивать наименование и дальше. Может, помещать в сессию? как текущий квест
            req.setAttribute("point", currentPoint);
            req.setAttribute("id", idQuest);
            req.setAttribute("title", currentQuest.getTitle());
        }
        if (req.getParameter("actionId") != null) {
            Long idAction = Long.valueOf(req.getParameter("actionId"));
            ActionDto currentAction = questService.getAction(idAction);
            req.setAttribute("action", currentAction);
            req.setAttribute("loss", StatusPoint.LOSS);
            req.setAttribute("win", StatusPoint.WIN);
            req.setAttribute("active", StatusPoint.ACTIVE);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest.jsp");
        requestDispatcher.forward(req, resp);
    }
}

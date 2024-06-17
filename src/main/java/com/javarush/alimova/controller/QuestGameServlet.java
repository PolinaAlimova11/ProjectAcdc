package com.javarush.alimova.controller;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.dto.PointDto;
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
import java.util.Map;

@WebServlet("/game")
public class QuestGameServlet extends HttpServlet {

    private final QuestService questService = Creator.getComponent(QuestServiceImpl.class);
    public static final Logger log = LoggerFactory.getLogger(QuestServiceImpl.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        QuestDto currentQuest = (QuestDto)session.getAttribute("currentQuest");


        if (req.getParameter("pointId") != null) {

            sendPointMenu(req, currentQuest);
        }
        if (req.getParameter("actionId") != null){
            sendActionMenu(req, session, currentQuest);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/quest.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void sendActionMenu(HttpServletRequest req, HttpSession session, QuestDto currentQuest) {
//        Long idAction = Long.valueOf(req.getParameter("actionId"));
//        ActionDto currentAction = questService.getAction(idAction);
//        req.setAttribute("action", currentAction);
//        req.setAttribute("loss", StatusPoint.LOSS);
//        req.setAttribute("win", StatusPoint.WIN);
//        req.setAttribute("active", StatusPoint.ACTIVE);
//
//        if(currentAction.getStatus() != StatusPoint.ACTIVE) {
//            Map<Long, StatisticQuest> statistic = (Map<Long, StatisticQuest>) session.getAttribute("statistic");
//            StatisticQuest statisticQuest = statistic.get(currentQuest.getId());
//            statisticQuest.setResultGame(currentAction.getStatus().name());
//            log.info(String.format("Quest finished with status %s", currentAction.getStatus().name()));
//        }
    }

    private static void sendPointMenu(HttpServletRequest req, QuestDto currentQuest) {
//        Map<Long, PointDto> pointDtoMap = currentQuest.getListPoint();
//        Long pointId = Long.valueOf(req.getParameter("pointId"));
//        PointDto currentPoint = pointDtoMap.get(pointId);
//
//        req.setAttribute("point", currentPoint);
//
//        log.info(String.format("Choose point %s (id %d)", currentPoint.getQuestion(), pointId));
    }
}

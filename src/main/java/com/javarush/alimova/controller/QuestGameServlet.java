package com.javarush.alimova.controller;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.dto.PointDto;
import com.javarush.alimova.dto.StatisticQuest;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.service.ActionService;
import com.javarush.alimova.service.PointService;
import com.javarush.alimova.service.impl.ActionServiceImpl;
import com.javarush.alimova.service.impl.PointServiceImpl;
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
import java.util.Optional;

@WebServlet("/game")
public class QuestGameServlet extends HttpServlet {

    private final ActionService actionService = Creator.getComponent(ActionServiceImpl.class);
    private final PointService pointService = Creator.getComponent(PointServiceImpl.class);

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
        Long idAction = Long.valueOf(req.getParameter("actionId"));
        Optional<ActionDto> currentAction = actionService.getByIdWithStepAction(idAction);

        if (currentAction.isPresent()) {
            ActionDto actionDto = currentAction.get();

            req.setAttribute("action", actionDto);
            req.setAttribute("loss", StatusPoint.LOSS);
            req.setAttribute("win", StatusPoint.WIN);
            req.setAttribute("active", StatusPoint.ACTIVE);

            if(actionDto.getStatusPoint() != StatusPoint.ACTIVE) {
                Map<Long, StatisticQuest> statistic = (Map<Long, StatisticQuest>) session.getAttribute("statistic");
                StatisticQuest statisticQuest = statistic.get(currentQuest.getId());
                statisticQuest.setResultGame(actionDto.getStatusPoint() .name());
                log.info(String.format("Quest finished with status %s", actionDto.getStatusPoint() .name()));
            }
        }
    }

    private void sendPointMenu(HttpServletRequest req, QuestDto currentQuest) {
        Long pointId = Long.valueOf(req.getParameter("pointId"));
        Optional<PointDto> currentPointOptional = pointService.getPoint(pointId);

        if(currentPointOptional.isPresent()) {
            PointDto pointDto = currentPointOptional.get();
            req.setAttribute("point", pointDto);
            log.info(String.format("Choose point %s (id %d)", pointDto.getQuestion(), pointId));

        }
    }
}

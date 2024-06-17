package com.javarush.alimova.controller;

import com.javarush.alimova.configure.ConfigurationDB;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.dto.StatisticQuest;
import com.javarush.alimova.service.impl.QuestServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    public static final Logger log = LoggerFactory.getLogger(QuestServiceImpl.class);
    private final ConfigurationDB configurationDB = Creator.getComponent(ConfigurationDB.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            configurationDB.startInitDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession currentSession = req.getSession();

        if(Boolean.parseBoolean(req.getParameter("exit"))) {
            currentSession.invalidate();
        }
        else {
            String name = req.getParameter("userName");
            currentSession.setAttribute("userName", name);
            Map<Long, StatisticQuest> statistic = new HashMap<>();
            currentSession.setAttribute("statistic", statistic);

            log.info(String.format("Create user %s", name));
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/index.jsp");
        requestDispatcher.forward(req, resp);
    }


}

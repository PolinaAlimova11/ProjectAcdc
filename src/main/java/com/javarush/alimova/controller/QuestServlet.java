package com.javarush.alimova.controller;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.service.QuestService;
import com.javarush.alimova.service.impl.QuestServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {

    private final QuestService questService = Creator.getComponent(QuestServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        resp.getWriter().println("Hi");
        getServletContext().getRequestDispatcher("quest_page.jsp").forward(req, resp);
    }
}

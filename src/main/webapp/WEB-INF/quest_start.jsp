<%--
  Created by IntelliJ IDEA.
  User: poli
  Date: 24.03.2024
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="parts/header.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <p>${requestScope.title}</p>
    <p>${requestScope.discription}</p>
    <br>
    <form action="game" method="post">
        <button type="submit" name="idQuest" value="${requestScope.id}">Начать</button>
    </form>


<%--тут должна быть кнопка для начала или просто весь квест--%>
</div>
</body>
</html>

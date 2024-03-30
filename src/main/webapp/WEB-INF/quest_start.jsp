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
    <p>${sessionScope.title}</p>
    <p>${sessionScope.description}</p>
    <br>
    <form action="game" method="post">
        <button type="submit" name="pointId" value="${requestScope.pointId}">Начать</button>
    </form>
    <br>
    <br>
    <form method="get" action="/">
        <input type="submit" value="На главный экран"/>
    </form>
</div>
</body>
</html>

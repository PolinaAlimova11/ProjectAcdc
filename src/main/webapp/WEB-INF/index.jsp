<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="parts/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мир квестов</title>
</head>
<body>

<div class="container">
    <h1>Мир квестов</h1>
    <br>
    <c:if test="${sessionScope.userName == null}">
        <p>Введите имя</p>
        <form action="" method="post">
            <label> Имя:
                <input type="text" name="userName">
            </label>
            <input type="submit" value="Ок"/>
        </form>
    </c:if>

    <c:if test="${sessionScope.userName != null}">

        <h3>Привет, ${sessionScope.userName}</h3>
        <br>

        <form action="quests" method="get">
            <button type="submit">
                Перейти в меню квестов
            </button>
        </form>
        <br>
        <form action="" method="post">
            <button type="submit" name="exit" value="${true}">Выйти</button>
        </form>

        <br>
        <h4>Твои достижения:</h4>
        <br>
        <c:forEach var="statictic" items="${sessionScope.statistic}">
            <p>Квест: ${statictic.value.getNameQuest()}</p>
            <p>Сыграно раз: ${statictic.value.getCounterGame()}</p>
            <p>Результат последней игры: ${statictic.value.getLastStatusGames()}</p>
        </c:forEach>

    </c:if>

</div>

</body>
</html>

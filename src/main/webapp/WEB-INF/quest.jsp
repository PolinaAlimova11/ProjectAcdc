<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="parts/header.jsp"/>
<html>
<head>
    <title>Квест ${sessionScope.title}</title>
</head>
<body>
<div class="container">
    <c:set var="point" value="${requestScope.point}"/>
    <c:set var="action" value="${requestScope.action}"/>

    <h3>${sessionScope.title}</h3>

    <c:if test="${action==null}">
        <p>${point.getQuestion()}</p>
        <br>
        <form action="game" method="post">
            <p>Выберите действие:</p>
            <c:forEach var = "actionFor" items = "${point.getListAction()}">
                <label>
                        ${actionFor.key}
                    <input type = "radio" name="actionId" value="${actionFor.value}">
                </label>
                <br>
            </c:forEach>
            <br>
            <input type="submit" value="Ок">

        </form>
    </c:if>

    <c:if test="${action!=null}">
        <c:forEach var = "currentAction" items = "${action.getListAction()}">
            <p>${currentAction}</p>
        </c:forEach>
        <c:if test="${action.getStatusPoint() == requestScope.loss}">
            <h4>Вы проиграли</h4>
        </c:if>
        <c:if test="${action.getStatusPoint() == requestScope.win}">
            <h4>Вы выиграли</h4>
        </c:if>
        <c:if test="${action.getStatusPoint() == requestScope.active}">
            <form method="post" action="game">
                <button type="submit" name="pointId" value="${requestScope.action.getIdNextPoint()}">
                    Продолжить
                </button>
            </form>
        </c:if>
        <br>
    </c:if>

    <br>
    <br>
    <form method="get" action="/">
        <input type="submit" value="На главный экран"/>
    </form>

</div>
</body>
</html>

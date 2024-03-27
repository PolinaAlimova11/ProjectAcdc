<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="parts/header.jsp"/>
<html>
<head>
    <title>Квест ${requestScope.title}</title>
</head>
<body>
<div class="container">
    <c:set var="point" value="${requestScope.point}"/>
    <c:set var="action" value="${requestScope.action}"/>

    <h3>${requestScope.title}</h3>

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
        <c:if test="${action.getStatus() == requestScope.loss}">
            <p>Вы проиграли (тут кнопка на главное меню)</p>
        </c:if>
        <c:if test="${action.getStatus() == requestScope.win}">
            <p>Вы выиграли (тут кнопка на главное меню)</p>
        </c:if>
        <c:if test="${action.getStatus() == requestScope.active}">
            <p>Продолжить (форма с кнопкой)</p>
        </c:if>
        <br>
    </c:if>

</div>
</body>
</html>

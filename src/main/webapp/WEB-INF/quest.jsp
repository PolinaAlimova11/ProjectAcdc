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

    <h3>${requestScope.title}</h3>
    
    <c:if test="${requestScope.action==null}">
        <p>${point.getQuestion()}</p>
        <br>
        <form action="game" method="post">
            <p>Выберите действие:</p>
            <c:forEach var = "action" items = "${point.getListAction()}">
                <label>
                        ${action.getListAction().get(0)}
                    <input type = "radio" name="action" value="${action}">
                </label>
                <br>
            </c:forEach>
            <input type="submit" value="Ок">

        </form>
    </c:if>

    <c:if test="${requestScope.action!=null}">
        <c:forEach var = "currentAction" items = "${action.getListAction()}">
<%--            думает, что тут строка (нужно посмотреть)--%>
            <p>${currentAction}</p>
        </c:forEach>
        <br>
        <p>${requestScope.action.getStatus()}</p>
    </c:if>
    

</div>
</body>
</html>

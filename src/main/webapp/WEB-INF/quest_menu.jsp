<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:import url="parts/header.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <p>Привет, ${sessionScope.get("userName")}</p>
        <form method="post">
            <label> Доступные квесты:
                <select name="questId" id="questId" class="form-control">
                <c:forEach var = "quest" items = "${requestScope.quests}">
                    <option value = "${quest.id}">${quest.title}</option>
                </c:forEach>
                </select>
            </label>
            <input type="submit" value="Выбрать" />
        </form>
    <br>
    <br>
    <form method="get" action="/">
        <input type="submit" value="На главный экран"/>
    </form>
</div>
<%--<c:import url="parts/footer.jsp"/>--%>
</body>
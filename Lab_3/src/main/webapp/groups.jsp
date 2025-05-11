<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список груп</title>
</head>
<body>
<h2>Список навчальних груп</h2>

<c:if test="${empty groups}">
    <p>Немає жодної групи.</p>
</c:if>

<c:if test="${not empty groups}">
    <ul>
        <c:forEach var="group" items="${groups}">
            <li>
                <strong>${group.name}</strong>: ${group.description}
                <a href="edit-group?name=${group.name}&description=${group.description}">Редагувати</a>
                |
                <form action="delete-group" method="post" style="display:inline;">
                    <input type="hidden" name="name" value="${group.name}" />
                    <input type="submit" value="Видалити" />
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>

<p><a href="add_group.jsp">Додати нову групу</a></p>
<p><a href="index.jsp">Повернутись на головну</a></p>
</body>
</html>

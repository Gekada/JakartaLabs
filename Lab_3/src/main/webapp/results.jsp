<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Результати пошуку</title>
</head>
<body>
<h2>Результати пошуку</h2>

<c:if test="${empty results}">
    <p>Нічого не знайдено.</p>
</c:if>

<c:if test="${not empty results}">
    <table border="1">
        <thead>
        <tr>
            <th>Прізвище</th>
            <th>Група</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${results}">
            <tr>
                <td>${student.surname}</td>
                <td>${student.groupName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<p><a href="${pageContext.request.contextPath}/index.jsp">Повернутись на головну</a></p>
</body>
</html>

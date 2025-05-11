<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список студентів</title>
</head>
<body>
<h2>Список всіх студентів</h2>

<c:if test="${empty students}">
    <p>Список порожній.</p>
</c:if>

<c:if test="${not empty students}">
    <table border="1">
        <thead>
        <tr>
            <th>Прізвище</th>
            <th>Група</th>
            <th>Дії</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.surname}</td>
                <td>${student.groupName}</td>
                <td>
                    <a href="edit-student?surname=${student.surname}&group=${student.groupName}">Редагувати</a>
                    |
                    <a href="delete-student?surname=${student.surname}">Видалити</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<p><a href="add-student">Додати нового студента</a></p>

<p><a href="index.jsp">Повернутись на головну</a></p>
</body>
</html>

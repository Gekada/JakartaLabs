<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редагування студента</title>
</head>
<body>
<h2>Редагувати студента</h2>
<form action="edit-student" method="post" accept-charset="UTF-8">

    <input type="hidden" name="originalSurname" value="${student.surname}" />
    <p>Прізвище: <input type="text" name="surname" value="${student.surname}" /></p>
    <p>Група: <input type="text" name="group" value="${student.groupName}" /></p>
    <input type="submit" value="Зберегти" />
</form>
<p><a href="students">Скасувати</a></p>
</body>
</html>

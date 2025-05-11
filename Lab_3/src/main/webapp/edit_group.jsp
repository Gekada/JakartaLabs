<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редагування групи</title>
</head>
<body>
<h2>Редагувати групу</h2>
<form action="edit-group" method="post" accept-charset="UTF-8">
    <input type="hidden" name="name" value="${group.name}"/>
    <p>Назва групи: <strong>${group.name}</strong></p>
    <p>Опис: <input type="text" name="description" value="${group.description}"/></p>
    <input type="submit" value="Зберегти"/>
</form>
<p><a href="groups">Скасувати</a></p>
</body>
</html>

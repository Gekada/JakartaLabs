<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Додати студента</title>
</head>
<body>
<h2>Додати нового студента</h2>
<form action="add-student" method="post" accept-charset="UTF-8">
    <p>Прізвище: <input type="text" name="surname" required /></p>
    <p>Група: <input type="text" name="group" required /></p>
    <input type="submit" value="Додати"/>
</form>
<p><a href="students">Скасувати</a></p>
</body>
</html>

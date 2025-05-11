<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Додати групу</title></head>
<body>
<h2>Додати нову групу</h2>
<form method="post" action="${pageContext.request.contextPath}/add-group">
    Назва: <input type="text" name="name" required><br>
    Опис: <input type="text" name="description"><br>
    <button type="submit">Додати</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/index.jsp">Повернутись на головну</a>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Система деканату</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      padding: 20px;
    }
    h1 {
      color: #2e6da4;
    }
    form {
      margin-bottom: 20px;
    }
    a {
      display: block;
      margin: 5px 0;
      color: #337ab7;
      text-decoration: none;
    }
    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>

<h1>Система деканату</h1>

<h2>🔍 Пошук студентів</h2>
<form method="get" action="${pageContext.request.contextPath}/search">
  <label>Прізвище: <input type="text" name="surname" /></label><br/>
  <label>Група: <input type="text" name="groupName" /></label><br/>
  <input type="submit" value="Пошук" />
</form>

<h2>📁 Перехід до розділів</h2>
<a href="students">📋 Список студентів</a>
<a href="groups">🏫 Список груп</a>
<a href="add_student.jsp">➕ Додати студента</a>
<a href="add_group.jsp">➕ Додати групу</a>

</body>
</html>

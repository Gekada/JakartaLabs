<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dekanat</title>
    </head>
    <body>
         <c:if test="${empty user}">
            <form action="LogInServlet" method="POST">
                Login:
                <input type="text" name="log">
                Password:
                <input type="password" name="pas">
                <input type="submit" value="log in">
            </form>
        </c:if>
        
        <c:if test="${!empty user}">
            <form action="LogOutServlet" method="POST">
                <input type="submit" value="log out">
            </form>
            
            <form action="SearchGroupServlet" method="POST">
                <input type="radio" name="searchType" value="ByGroup" checked> Search by group 
                <input type="radio" name="searchType" value="BySname"> Search by surname
                <br/>
                <input type="text" name="searchText">
                <input type="submit" value="search">
                
            </form>
        </c:if>
        
        <c:if test="${user.isAdmin()}">
            <form action="AddGroupServlet" method="POST">
                Input group name:
                <input type="text" name="name">
                <input type="submit" value="add">
            </form>            
        </c:if>
        
        <c:if test="${!empty searchedGroups}">
            Results:
            <c:forEach var="group" items="${searchedGroups}">
            <form action="OpenGroupServlet" method="POST">
                <input type="hidden" name="id" value="${group.getId()}">
                <input type="submit" value="${group.getName()}">
            </form>
            </c:forEach>
        </c:if>
        
        <br/>
        
        <c:if test="${!empty groups}">
            <c:forEach var="group" items="${groups}">
            
                <form action="OpenGroupServlet" method="POST">
                    <input type="hidden" name="id" value="${group.getId()}">
                    <input type="submit" value="${group.getName()}">
                </form>
                <c:if test="${user.isAdmin()}">
                    <form action="DeleteGroupServlet" method="POST">
                        <input type="hidden" name="id" value="${group.getId()}">
                        <input type="submit" value="delete">
                    </form>
                    
                </c:if>
            </c:forEach>
        </c:if>
       
        
        <c:if test="${!empty group}">
            <c:forEach var="student" items="${group.getStudents()}">
                ${student} 
                <br/>
                
                <c:if test="${user.isAdmin()}">
                <form action="DelteUserFromGroupServlet" method="POST">
                    <input type="hidden" name="id" value="${student.getId()}">
                    <input type="submit" value="delete">
                </form>
                   
                </c:if>
                
            </c:forEach>
            <c:if test="${user.isAdmin()}">
                <c:if test="${free.size()>0}">
                <form action="AddUserToGroupServlet" method="POST">
                    <select name="id">
                        <c:forEach var="user" items="${free}">
                            <option value="${user.getId()}">
                                ${user}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="add">
                </form>
                </c:if>
            </c:if>
        </c:if>
        
    </body>
</html>

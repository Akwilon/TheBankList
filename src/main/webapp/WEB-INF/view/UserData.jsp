<%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 30.06.2020
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User by ID</title>
    <style>
        <%@include file='/style.css' %>
    </style>
</head>
<body>
<div class="login-box">
<form method="post">
    <input type="number" name="userId" placeholder="ID 1 to 13" required>
    <button type="submit">send</button>
</form>
<h2>User Data By ID</h2>
<table>
    <tr>
        <th>UserId</th>
        <th>Name</th>
        <th>Surname</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getUserID()}</td>
            <td>${user.getName()}</td>
            <td>${user.getSurname()}</td>
        </tr>
    </c:forEach>
</table>
<div>
    <button onclick="location.href='/TheBankList_war'">Back to main</button>
</div>
</div>
</body>
</html>

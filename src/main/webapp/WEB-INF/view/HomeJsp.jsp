<%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 30.06.2020
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file='/style.css' %>
    </style>
    <title>Home</title>
</head>
<body>
<div class="login-box">
<h2>Choose action</h2>
<ol class="rounded"  >
    <li><a href="/TheBankList_war/show-all-accounts">All Accounts</a></li>
    <li><a href="/TheBankList_war/user-data">User by ID</a></li>
    <li><a href="/TheBankList_war/richest-user-id">Parametres</a></li>
</ol>
</div>
</body>
</html>

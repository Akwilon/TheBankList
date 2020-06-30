<%@ page import="dao.Dao" %>
<%@ page import="bean.Account" %>
<%@ page import="dao.impl.AccountDao" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="dao.ConnectionPoolException" %>
<%@ page import="dao.ConnectionPoolNotInitializedException" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 30.06.2020
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Richest User</title>
    <style>
        <%@include file='/style.css' %>
    </style>
</head>
<body>
<div class="login-box">
    <h2>Get variable parametres</h2>
    <form method="post" action="/TheBankList_war/richest-user-id">
        <div class="user-box">
        <input type="text" name="" value="${account}">
        <label>Get richest User ID</label>
        </div>
        <input type="submit" value="GET">
    </form>

    <form method="post" action="/TheBankList_war/summ-accounts">
        <div class="user-box">
        <input name="max" type="text" value="${value}" >
            <label>Summ of all Accounts</label>
        </div>
        <input type="submit" value="GET"></span>
    </form>

    <div>
        <button class="btn btn-primary btn-ghost btn-cross" onclick="location.href='/TheBankList_war'">Back to main</button>
    </div>
</div>

</body>
</html>

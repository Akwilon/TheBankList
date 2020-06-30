<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Account" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 30.06.2020
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All accounts</title>
    <style>
        <%@include file='/style.css' %>
    </style>
</head>
<body>
<div class="login-box">
<h2>All Accounts</h2>
<%
    List<Account> accounts =(List<Account>) request.getAttribute("accounts");
%>
<table >
    <tr>
        <th>AccountId</th>
        <th>Account</th>
        <th>UserId</th>
    </tr>
    <%
        for (Account account:accounts) {

    %>
    <tr>
        <td><%=account.getAccountID()%></td>
        <td><%=account.getAccount()%></td>
        <td><%=account.getUserID()%></td>
    </tr>
    <%}%>
</table>
<div>
    <button onclick="location.href='/TheBankList_war'">Back to main</button>
</div>
</div>
</body>
</html>

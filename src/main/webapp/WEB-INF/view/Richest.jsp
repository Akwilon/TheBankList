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
<h1>Get variable parametres</h1>


<%
    Account account = null;
    try {
        List<Account> accountList = new AccountDao().getAll();
        System.out.println("Account collection: " + accountList);
        for (Account account1: accountList){

            int max = Integer.MIN_VALUE;
            if (account1.getAccount() > max){
                max = account1.getAccount();
                account = account1;
            }
        }
    }catch (ConnectionPoolException | ConnectionPoolNotInitializedException e){
        e.printStackTrace();
    }
%>
<form>
    <input name="max" type="text" size="10" value="<%=account.getAccount()%>">
    <button type="submit" >get</button>
</form>
<div>
    <button onclick="location.href='/TheBankList_war'">Back to main</button>
</div>


</body>
</html>

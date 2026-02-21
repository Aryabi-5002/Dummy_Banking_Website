<%@ page import="java.util.*, DTO.BankAccount, DTO.Custom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Accounts</title>
    <link rel="stylesheet" href="Home.css">
</head>
<body>

<header>
    <h1 style="color:white">Your Bank Accounts</h1>
</header>

<%
    Custom customer = (Custom) session.getAttribute("customer");

    if (customer == null) {
%>
    <div class="container">
        <p style="color:red;">Session expired. Please login again.</p>
    </div>
<%
    } else {
        List<BankAccount> accounts = customer.getList();

        if (accounts == null || accounts.isEmpty()) {
%>
    <div class="container">
        <p>No accounts found.</p>
    </div>
<%
        } else {
%>

<div class="container">
    <h2>Customer Info</h2>
    <p><strong>Name:</strong> <%= customer.getCust_name() %></p>
    <p><strong>Customer ID:</strong> <%= customer.getCust_id() %></p>
</div>

<div class="container">
    <h2>Accounts</h2>
    <table border="1" width="100%" cellpadding="10">
        <tr>
            <th>Account Number</th>
            <th>Account Type</th>
            <th>Account Limit</th>
        </tr>
        <%
            for (BankAccount ba : accounts) {
        %>
        <tr>
            <td><%= ba.getAcc_no() %></td>
            <td><%= ba.getAcc_type()%></td>
            <td><%= ba.getAcc_limit()%></td>
        </tr>
        <%
            }
        %>
    </table>
</div>

<%
        }
    }
%>

</body>
</html>
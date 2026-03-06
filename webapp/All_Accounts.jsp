<%@ page import="java.util.*, DTO.BankAccount, DTO.Custom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title> Accounts</title>
    <link rel="stylesheet" href="Home.css">
</head>
<body>

<header>
    <h1 style="color:white"> All Active Bank Accounts</h1>
    <h3 style=color:white><a href="Bankadmin.html">Dashboard</a></h3>
</header>

<%
    {
        List<BankAccount> accounts = (List<BankAccount>)session.getAttribute("list");

        if (accounts == null || accounts.isEmpty()) {
%>
    <div class="container">
        <p>No accounts found. or login again</p>
    </div>
<%
        } else {
%>


<div class="container">
    <h2>Accounts</h2>
    <table border="1" width="100%" cellpadding="10">
        <tr>
            <th>Account Number</th>
            <th>Account Type</th>
            <th>Account Limit</th>
            <th>Customer Name</th>
            <th>Customer Id</th>
        </tr>
        <%
            for (BankAccount ba : accounts) {
            	if(ba.isStatus()){
            	Custom customer=(Custom)ba.getCustom();
        %>
        <tr>
            <td><%= ba.getAcc_no() %></td>
            <td><%= ba.getAcc_type()%></td>
            <td><%= ba.getAcc_limit()%></td>
            <td><%=customer.getCust_name()%></td>
            <td><%= customer.getCust_id() %></td>
            
        </tr>
        <%
            	}
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
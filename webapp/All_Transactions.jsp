<%@ page import="java.util.*, DTO.BankAccount, DTO.Custom,DTO.BankTransaction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Overall Transactions</title>
    <link rel="stylesheet" href="Home.css">
</head>
<body>

<header>
    <h1 style="color:white"> Active account Transactions</h1>
        <h3 style=color:white><a href="Bankadmin.html">Dashboard</a></h3>
</header>

<%
    {
        List<BankAccount> accounts = (List<BankAccount>)session.getAttribute("list");

        if (accounts == null || accounts.isEmpty()) {
%>
    <div class="container">
        <p>No accounts found or login again.</p>
    </div>
<%
        } else {
%>


<div class="container">
    <h2>Transactions</h2>
    <table border="1" width="100%" cellpadding="10">
        <tr>
            <th>Account Number</th>
            <th>Customer Id</th>
            <th>Transaction Id</th>
            <th>Deposit</th>
            <th>Withdrawn</th>
            <th>time</th>
        </tr>
        <%
            for (BankAccount ba : accounts) {
            	if(ba.isStatus()){
            	Custom customer=(Custom)ba.getCustom();
            	List<BankTransaction> transactions=(List<BankTransaction>)ba.getBanktransaction();
            	for (BankTransaction bt : transactions){
        %>
        <tr>
            <td><%= ba.getAcc_no() %></td>
            <td><%= customer.getCust_id() %></td>
            <td><%= bt.getTid() %></td>
            <td><%= bt.getDeposite() %></td>
            <td><%= bt.getWithdrawn() %></td>
            <td><%= bt.getLocaldatetime() %></td>
            
            
        </tr>
        <%
            	}
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
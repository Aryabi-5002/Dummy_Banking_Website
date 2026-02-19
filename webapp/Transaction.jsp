<%@ page import="java.util.*, DTO.Custom, DTO.BankAccount, DTO.BankTransaction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Transactions</title>
    <link rel="stylesheet" href="Home.css">
</head>
<body>

<header>
    <h1 style="color:white">Transaction History</h1>
</header>

<div class="container">

<%
    // Logged-in customer
    Custom customer = (Custom) session.getAttribute("customer");

    if (customer == null) {
%>
        <p style="color:red;">Session expired. Please login again.</p>
<%
    } else {

        List<BankAccount> accounts = customer.getList();

        if (accounts == null || accounts.isEmpty()) {
%>
            <p>No bank accounts found.</p>
<%
        } else {

            // Loop through each account of this customer
            for (BankAccount account : accounts) {

                List<BankTransaction> transactions =
                        account.getBanktransaction();
%>

                <h3>Account Number: <%= account.getAcc_no() %></h3>
                <h4>Account Type: <%= account.getAcc_type() %></h4>

<%
                if (transactions == null || transactions.isEmpty()) {
%>
                    <p>No transactions for this account.</p>
<%
                } else {
%>
                    <table border="1" width="100%" cellpadding="10">
                        <tr>
                            <th>Transaction ID</th>
                            <th>Deposit</th>
                            <th>Withdrawn</th>
                            <th>Balance</th>
                            <th>Date & Time</th>
                        </tr>

<%
                    for (BankTransaction bt : transactions) {
%>
                        <tr>
                            <td><%= bt.getTid() %></td>
                            <td><%= bt.getDeposite() %></td>
                            <td><%= bt.getWithdrawn() %></td>
                            <td><%= bt.getBalance() %></td>
                            <td><%= bt.getLocaldatetime() %></td>
                        </tr>
<%
                    }
%>
                    </table>
                    <br>
<%
                }
            }
        }
    }
%>

</div>

</body>
</html>
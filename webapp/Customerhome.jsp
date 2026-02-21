<%@ page import="java.util.*, DTO.BankAccount, DTO.Custom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Home</title>
    <link rel="stylesheet" href="Home.css">
</head>
<body>

<header>
    <h1 style="color:white">Customer Dashboard</h1>
    <p>Welcome to Your Account</p>
</header>

<%
    Custom customer = (Custom) session.getAttribute("customer");

    int accountCount = 0;

    if (customer != null && customer.getList() != null) {
        accountCount = customer.getList().size();
    }
%>

<% if (customer != null) { %>

    <!-- Navigation (always visible when logged in) -->
    <nav>
        <a href="Deposite.html">Deposit</a>
        <a href="Withdrawl.html">Withdraw</a>
        <a href="Checkbalance.jsp">Check Balance</a>
        <a href="Transaction.jsp">Transactions</a>
        <a href="Accounts.jsp">Accounts</a>
        <button id="logoutBtn">Logout</button>

    </nav>

    <!-- Show Create Account ONLY if 0 or 1 account -->
    <% if (accountCount < 2) { %>
        <div class="container">
            <h3>Create New Account</h3>

            <form action="createbankaccount" method="post">
                <label>
                    <input type="radio" name="banktype" value="savings" required>
                    Savings
                </label>

                <label>
                    <input type="radio" name="banktype" value="current">
                    Current
                </label>

                <br><br>
                <button type="submit">Create Account</button>
            </form>
        </div>
    <% } %>

<% } else { %>

    <div class="container">
        <p style="color:red;">Please login first.</p>
    </div>

<% } %>

<div class="container">
    <h2>Quick Overview</h2>
    <p>
        Access your banking services securely. Use the navigation menu
        to manage your funds and view transaction history.
    </p>
</div>
<script>
document.getElementById("logoutBtn").addEventListener("click", function() {
    // Redirect the browser to the logout URL (GET request)
    window.location.href = '<%= request.getContextPath() %>/logout';
});
</script>

</body>
</html>
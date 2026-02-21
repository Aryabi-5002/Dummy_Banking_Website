<%@ page import="java.util.*, DAO.Bankdao, DTO.BankAccount, DTO.Custom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Check Balance</title>
    <link rel="stylesheet" href="Home.css">
</head>
<body>

<header>
    <h1 style="color:white">Account Balance</h1>
</header>

<div class="container">
    <form method="post" action="">
        <div style="margin-bottom: 10px;">
            <label for="accno">Account Number</label><br>
            <input type="number" id="accno" name="accno" required>
        </div>
        <button type="submit">Check Balance</button>
    </form>
</div>

<%
    // Handle form submission
    String accnoParam = request.getParameter("accno");
    if (accnoParam != null && !accnoParam.isEmpty()) {

        long accno = Long.parseLong(accnoParam);

        // Get the logged-in customer from session
        Custom customer = (Custom) session.getAttribute("customer");

        if (customer != null) {
            Bankdao bankDao = new Bankdao();

            // Fetch accounts of this customer
            List<BankAccount> accounts = customer.getList();

            BankAccount matchedAccount = null;

            for (BankAccount ba : accounts) {
                if (ba.getAcc_no() == accno) {
                    matchedAccount = ba;
                    break;
                }
            }

            if (matchedAccount != null) {
%>
<div class="container">
    <p><strong>Account Number:</strong> <%= matchedAccount.getAcc_no() %></p>
    <p><strong>Available Balance:</strong> ₹ <%= matchedAccount.getAmmount() %></p>
    <br>
    <p>This balance reflects your most recent transactions.</p>
</div>
<%
            } else {
%>
<div class="container">
    <p style="color:red;">No account found with this Account Number for your profile.</p>
</div>
<%
            }
        } else {
%>
<div class="container">
    <p style="color:red;">Customer not logged in.</p>
</div>
<%
        }
    }
%>

</body>
</html>
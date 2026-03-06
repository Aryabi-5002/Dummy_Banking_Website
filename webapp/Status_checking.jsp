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
    <h1 style="color:white">All Bank Accounts</h1>
        <h3 style=color:white><a href="Bankadmin.html">Dashboard</a></h3>
</header>
<div id="serverResponse"></div>

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
            <th>Customer Id</th>
            <th>Status</th>
        </tr>
        <%
            for (BankAccount ba : accounts) {
            	Custom customer=(Custom)ba.getCustom();
        %>
        <tr>
            <td><%= ba.getAcc_no() %></td>
            <td><%= customer.getCust_id() %></td>
            <td>
    <input type="checkbox"
           id="<%= ba.getAcc_no() %>"
           name="accountStatus"
           value="<%= ba.getAcc_no() %>"
           <%= ba.isStatus() ? "checked" : "" %>
           onchange="updateItem(this)">

    <label for="<%= ba.getAcc_no() %>">Is Active</label>
</td>
            
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
<script>
function updateItem(element) {

    const accno = element.id;


    fetch("http://localhost:8080/banking/changestatus", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "accno=" +encodeURIComponent(accno)
    })
    .then(res => res.text())
    .then(result => {
        document.getElementById("serverResponse").innerHTML = result;
    })
    .catch(err => console.error(err));

}
</script>
</html>
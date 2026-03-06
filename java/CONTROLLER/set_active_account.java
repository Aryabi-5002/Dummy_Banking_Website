
package CONTROLLER;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet ("/setactiveaccount")

public class set_active_account extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String accno	=req.getParameter("accno");
	long acno=Long.parseLong(accno);
	req.getSession().setAttribute("accno", acno); //here i can tell my active account is set
	req.getRequestDispatcher("/Transactionpage.jsp").include(req, resp);
	
	}

}
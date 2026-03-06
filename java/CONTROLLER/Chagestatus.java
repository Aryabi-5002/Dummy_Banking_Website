
package CONTROLLER;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.Bankdao;
import DTO.BankAccount;
@WebServlet("/changestatus")
public class Chagestatus extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String accno=req.getParameter("accno");
	
	long acnn=Long.parseLong(accno);
	
	Bankdao bankdao=new Bankdao();
	
BankAccount bankaccount	=bankdao.fetch_by_accont_no(acnn);
	
	if(bankaccount.isStatus())
	{
		bankaccount.setStatus(false);
	}
	else {
		bankaccount.setStatus(true);
	}
	bankdao.update(bankaccount);
	
	List< BankAccount>list =bankdao.fetch_all_bank_details();
	req.getSession().setAttribute("list", list);
	//req.getRequestDispatcher("Status_checking.jsp").include(req, resp);
	resp.getWriter().print("<h3>Status has been updated succsefully <h3>");
	}
	

}
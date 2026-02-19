
package CONTROLLER;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Bankdao;
import DTO.BankAccount;
@WebServlet("/changestatus")
public class Chagestatus extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	req.getRequestDispatcher("Accounthome.jsp").include(req, resp);
	resp.getWriter().print("<h1>Status has benn updated succsefully <h1>");
	}
	

}
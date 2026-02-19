
package CONTROLLER;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import DAO.Bankdao;
import DTO.BankAccount;
import DTO.BankTransaction;
@WebServlet("/withdrawl")
public class withdrawl extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amount=req.getParameter("amount");
		System.out.println(amount);
		double amt= Double.parseDouble(amount);
		String account_no=req.getParameter("accno");
		long accno=Long.parseLong(account_no);
		
		//Custom customer= (Custom) req.getSession().getAttribute("customer");
		//long acno=BankAccount.get
				
		
		Bankdao bankDao=new Bankdao();
		BankAccount bankAccount =bankDao.fetch_by_accont_no(accno);
		
		if(bankAccount.getAmmount()<amt)
		{
			resp.getWriter().print("<p>Insuffcient balence  , your actual balence is :</p>"+bankAccount.getAmmount());

			req.getRequestDispatcher("/withdrawl.html").include(req, resp);


		}
		else {
			
		if(amt>bankAccount.getAcc_limit()) {
			resp.getWriter().print("<h1>you account limit is existed your actual accont</h1>"+bankAccount.getAcc_limit());
			req.getRequestDispatcher("/withdrawl.html").include(req, resp);

		}
		else {
		bankAccount.setAmmount(bankAccount.getAmmount()-amt);	//before putting any data inside database we should the data
		bankDao.update(bankAccount);
		
		BankTransaction banktransaction=new BankTransaction();
		//banktransaction.setTid(0);
		banktransaction.setDeposite(0);
		banktransaction.setWithdrawn(amt);
		banktransaction.setBalance(bankAccount.getAmmount());
		banktransaction.setLocaldatetime(LocalDateTime.now());
	List<BankTransaction>list	=bankAccount.getBanktransaction();//old bank trasacton history 
		
	list.add(banktransaction);
		
	bankDao.update(bankAccount);
		resp.getWriter().print("<h1> Amount has been withdrawn  successfully</h1>");
		resp.getWriter().print("<h1>Your Current blence is<h1> "+bankAccount.getAmmount());
		req.getRequestDispatcher("/Transactionpage.jsp").include(req, resp);

		}
	}
	}
}
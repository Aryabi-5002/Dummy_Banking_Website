
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
import DTO.Custom;
@WebServlet("/depositpage")
public class deposite extends HttpServlet{
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
		
			
		if(amt>bankAccount.getAcc_limit()) {
			resp.getWriter().print("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<style>\r\n"
					+ ".alert {\r\n"
					+ "  padding: 22px;\r\n"
					+"font-size:20px;\r\n"
					+ "  background-color: red;\r\n"
					+ "  color: white;\r\n"
					

					+ "}\r\n"
					+ "\r\n"
					+ ".closebtn {\r\n"
					+ "  margin-left: 15px;\r\n"
					+ "  color: white;\r\n"
					+ "  font-weight: bold;\r\n"
					+ "  float: right;\r\n"
					+ "  font-size: 30px;\r\n"
					+ "  line-height: 20px;\r\n"
					+ "  cursor: pointer;\r\n"
					+ "  transition: 0.3s;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ ".closebtn:hover {\r\n"
					+ "  color: black;\r\n"
					+ "}\r\n"
					+ "</style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "<div  class=\"alert\">\r\n"
					+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
					+ "  <strong>Your Account Limit Existed !</strong> .\r\n"
					+ "</div>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "");
				//req.getRequestDispatcher("/Transactionpage.jsp").include(req, resp);
				return;
				
		}
		else {
		bankAccount.setAmmount(bankAccount.getAmmount()+amt);	//before putting any data inside database we should the data
		bankDao.update(bankAccount);
	// this is table of bank transaction  data stores hare 
		BankTransaction banktransaction=new BankTransaction();
		//banktransaction.setTid(0);
		banktransaction.setDeposite(amt);
		banktransaction.setWithdrawn(0);
		banktransaction.setBalance(bankAccount.getAmmount());
		banktransaction.setLocaldatetime(LocalDateTime.now());
	List<BankTransaction>list	=bankAccount.getBanktransaction();//old bank trasacton history 
		
	list.add(banktransaction);	  //inside this now we are having older transaction histroy +current transactio istroy
	
	//object for transaction dao class
	bankDao.update(bankAccount);
	resp.getWriter().print("<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<head>\r\n"
			+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
			+ "<style>\r\n"
			+ ".alert {\r\n"
			+ "  padding: 22px;\r\n"
			+"font-size:20px;\r\n"
			+ "  background-color: green;\r\n"
			+ "  color: white;\r\n"
			

			+ "}\r\n"
			+ "\r\n"
			+ ".closebtn {\r\n"
			+ "  margin-left: 15px;\r\n"
			+ "  color: white;\r\n"
			+ "  font-weight: bold;\r\n"
			+ "  float: right;\r\n"
			+ "  font-size: 30px;\r\n"
			+ "  line-height: 20px;\r\n"
			+ "  cursor: pointer;\r\n"
			+ "  transition: 0.3s;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".closebtn:hover {\r\n"
			+ "  color: black;\r\n"
			+ "}\r\n"
			+ "</style>\r\n"
			+ "</head>\r\n"
			+ "<body>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "<div  class=\"alert\">\r\n"
			+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
			+ "  <strong>Amount has been deposited  successfully</strong> .\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "</body>\r\n"
			+ "</html>\r\n"
			+ "");
		//req.getRequestDispatcher("/Transactionpage.jsp").include(req, resp);
		}
	}
	}
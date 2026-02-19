
package CONTROLLER;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.Bankdao;
import DAO.Customerdao;
import DTO.BankAccount;
import DTO.Custom;
@WebServlet("/createbankaccount")
public class create_bank_acount extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accouttype=req.getParameter("banktype");
		
		Custom customer=(Custom) req.getSession().getAttribute("customer");
		
		List<BankAccount>list=customer.getList();//will give the list of bank accouunts which have been creates for current user
	
		boolean flag=true;
		
		for (BankAccount bankaccount : list) {
			if(bankaccount.getAcc_type().equals(accouttype))
			{
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
						+ "  <strong>Your Account Is already Existed!</strong> .\r\n"
						+ "</div>\r\n"
						+ "\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");			
				
				flag=false;
				req.getRequestDispatcher("Customerhome.html").include(req, resp);
			}
		}
		
		if(flag) {
			BankAccount bankAccount = new BankAccount();
			//bankAccount.setAccno(0);//it will get generated automatically so its not required to set externally
			bankAccount.setAmmount(0);//---
			bankAccount.setStatus(false);//---
			
			bankAccount.setAcc_type(accouttype);
			
			if(bankAccount.getAcc_type().equals("savings"))
				bankAccount.setAcc_limit(10000);
			else 
				bankAccount.setAcc_limit(15000);
			
			bankAccount.setCustom(customer);
			
			Bankdao bankDao=new Bankdao();
			bankDao.save(bankAccount);
			
			List<BankAccount>list2=list;//savings
			list2.add(bankAccount);//saving + current=list2
			customer.setList(list2);
			
		 Customerdao customerdao = new Customerdao();
			customerdao.update(customer);
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
					+ "  <strong>Bank Account has been created Successfully Waiting for Bank Manager Approval !</strong> .\r\n"
					+ "</div>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "");
						req.getRequestDispatcher("Bankadmin.html").include(req, resp);
			}
			
		}

	}
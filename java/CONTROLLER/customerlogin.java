
package CONTROLLER;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import DAO.Customerdao;
import DTO.Custom;
@WebServlet("/customerlogin")
public class customerlogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custid=req.getParameter("custid");
		String pwd=req.getParameter("pwd");
		long id=Long.parseLong(custid);
		Customerdao dao=new Customerdao();
		Custom customer=dao.fetchbyCustid(id);
		if(customer==null) {
			resp.getWriter().print("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<style>\r\n"
					+ ".alert {\r\n"
					+ "  padding: 20px;\r\n"
					+ "  background-color: red;\r\n"
					+ "  color: white;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ ".closebtn {\r\n"
					+ "  margin-left: 15px;\r\n"
					+ "  color: white;\r\n"
					+ "  font-weight: bold;\r\n"
					+ "  float: right;\r\n"
					+ "  font-size: 22px;\r\n"
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
					+ "<div class=\"alert\">\r\n"
					+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
					+ "  <strong>Dear Customer invalid credential!</strong> .\r\n"
					+ "</div>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "");
			req.getRequestDispatcher("Customerlogin.html").include(req, resp);

		}
			else {
				if(customer.getPassword().equals(pwd))
				{
					resp.getWriter().print("<!DOCTYPE html>\r\n"
							+ "<html>\r\n"
							+ "<head>\r\n"
							+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
							+ "<style>\r\n"
							+ ".alert {\r\n"
							+ "  padding: 20px;\r\n"
							+ "  background-color: green;\r\n"
							+ "  color: white;\r\n"
							+ "}\r\n"
							+ "\r\n"
							+ ".closebtn {\r\n"
							+ "  margin-left: 15px;\r\n"
							+ "  color: white;\r\n"
							+ "  font-weight: bold;\r\n"
							+ "  float: right;\r\n"
							+ "  font-size: 22px;\r\n"
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
							+ "<div class=\"alert\">\r\n"
							+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
							+ "  <strong>Dear Customer Login done Succsesfuly</strong> .\r\n"
							+ "</div>\r\n"
							+ "\r\n"
							+ "</body>\r\n"
							+ "</html>\r\n"
							+ "");
					req.getSession().setAttribute("customer", customer);
					req.getRequestDispatcher("/Customerhome.jsp").include(req, resp);
				}
				else {
					resp.getWriter().print("<!DOCTYPE html>\r\n"
							+ "<html>\r\n"
							+ "<head>\r\n"
							+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
							+ "<style>\r\n"
							+ ".alert {\r\n"
							+ "  padding: 20px;\r\n"
							+ "  background-color: red;\r\n"
							+ "  color: white;\r\n"
							+ "}\r\n"
							+ "\r\n"
							+ ".closebtn {\r\n"
							+ "  margin-left: 15px;\r\n"
							+ "  color: white;\r\n"
							+ "  font-weight: bold;\r\n"
							+ "  float: right;\r\n"
							+ "  font-size: 22px;\r\n"
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
							+ "<div class=\"alert\">\r\n"
							+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
							+ "  <strong>Dear Customer You have entered a wrong password</strong> .\r\n"
							+ "</div>\r\n"
							+ "\r\n"
							+ "</body>\r\n"
							+ "</html>\r\n"
							+ "");
					req.getRequestDispatcher("/Customerlogin.html").include(req, resp);
				}
			}
		}
	}
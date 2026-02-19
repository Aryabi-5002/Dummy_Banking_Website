
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
@WebServlet("/adminlogin")
public class Adminlogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("adminemail");
		String Password=req.getParameter("adminpassword");
		if(email.equals("admin@gmail.com")&& Password.equals("admin"))
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
					+ "  <strong>Succsessfully Login!</strong> .\r\n"
					+ "</div>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "");
			Bankdao bankdao=new Bankdao();
	List<BankAccount>list=bankdao.fetch_all_bank_details();
	
	req.getSession().setAttribute("list", list);
	req.getRequestDispatcher("/Accounthome.jsp").include(req, resp);

		}
		else {
			resp.getWriter().print("<h1> Provide coreect credentials</h1>");
			req.getRequestDispatcher("Bankadmin.html").include(req, resp);
		}
		
	}

}
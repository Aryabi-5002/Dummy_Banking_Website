
package CONTROLLER;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import DAO.Customerdao;
import DTO.Custom;


@WebServlet("/customersignup")
public class customersignup extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String custname=req.getParameter("custname");
		String custmobile=req.getParameter("mobile");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		String gender=req.getParameter("gender");
		String dob=req.getParameter("dob");
		
		long mobile=Long.parseLong(custmobile);
		
		//long mobile1=Long.parseLong(mobile1);
		/*resp.getWriter().print("<h1>name: "+custname+"<h1>");
		resp.getWriter().print("<h1>mobile: "+mobile+"<h1>");
		resp.getWriter().print("<h1>password: "+password+"<h1>");
		resp.getWriter().print("<h1>email: "+email+"<h1>");
		resp.getWriter().print("<h1>gender: "+gender+"<h1>");
		resp.getWriter().print("<h1>dob: "+dob+"<h1>");*/
		
//		resp.getWriter().print("<h1>name: "+custname+"<h1>"
//				+ "<h1>mobile: "+mobile+"<h1>"
//				+ "<h1>password: "+password+"<h1>"
//				+ "<h1>email: "+email+"<h1>"
//				+ "<h1>gender: "+gender+"<h1>"
//				+ "<h1>dob: "+dob+"<h1>");
		Date date=Date.valueOf(dob);
		
		Period period=Period.between(date.toLocalDate(), LocalDate.now());
		
		int age=period.getYears();
		
		if (age<18)
		{
			resp.getWriter().print("<h1>He is not eligible to create a Bank Account <h1>");
		}else {
			//resp.getWriter().print("<h1>He is not eligible to create a Bank Account <h1>");
			Customerdao dao =new Customerdao();
			
//		Customer customer1= dao.fetch(mobile);
//		
//		Customer customer2=dao.fetch(email);
		 
		 List<Custom>list1=dao.fetch(mobile);
		 List<Custom>list2=dao.fetch(email);
		 
		 if(list1.isEmpty() && list2.isEmpty())
		
		
		 {
			Custom customer =new Custom();
		  customer.setCust_name(custname);
		  customer.setCust_email(email);
		  customer.setGender(gender);
		  customer.setPassword(password);
		  customer.setDob(date);
		  customer.setMob(mobile);
		  
		//Dao dao  =new Dao();
		dao.save(customer);
		
//		resp.getWriter().print("<h1>account create succssefully<h1>");
//		  
	
		Custom customer2=dao.fetch(email).get(0);
		long id=customer2.getCust_id();
		if(customer2.getGender().equals("male")) {
			resp.getWriter().print("<script>window.alert(`Hello Sir account create succssefully`) </script>");
			resp.getWriter().print("<h1 >your customer id is :"+id+" </h1>");
           req.getRequestDispatcher("Home.html").include(req, resp);
		}
		else {
			resp.getWriter().print("<script>window.alert(`Hello mam account create succssefully`) </script>");
			resp.getWriter().print("<h1 >your customer id is :"+id+" <h1>");
	           req.getRequestDispatcher("Home.html").include(req, resp);

		}
		
		}
		
		 else {
				resp.getWriter().print("<script>window.alert(`account is alreadyexisted`)</script>");
		           req.getRequestDispatcher("Home.html").include(req, resp);

			}
		}
		
	}
}
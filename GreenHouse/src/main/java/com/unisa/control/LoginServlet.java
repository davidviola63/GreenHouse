package com.unisa.control;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.unisa.dao.RegistrationDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        try {

		        if (RegistrationDao.userValido(email, password)) {
		        	
		        	HttpSession session=request.getSession();
		        	session.setAttribute("User", RegistrationDao.getUser(email));
		        	
		            response.sendRedirect("greenHouseHome.jsp");
		            
		        } else {
		        	
		        	request.setAttribute("errorMessage", "Login fallito: Nome utente e/o password non validi. Riprova.");
		        	request.getRequestDispatcher("login.jsp").forward(request, response);
		        }    
		 
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	     }
		 
		 
}


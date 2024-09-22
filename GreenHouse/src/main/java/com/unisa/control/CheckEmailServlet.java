package com.unisa.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unisa.dao.ManagerUserDao;

@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
    	String email = request.getParameter("email");
        boolean emailExists = false;
        
        try {
        	
        	if (email != null && !email.isEmpty()) {
        		emailExists = ManagerUserDao.isEmailRegistered(email);
			}
        
        }catch(Exception e) {
        	e.printStackTrace();
        }


        response.setContentType("text/plain");
        if (emailExists) {
            response.getWriter().write("esiste");  
        } else {
            response.getWriter().write("disponibile"); 
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package com.unisa.control;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.unisa.dao.RegistrationDao;


/*
 * LoginServlet si occupa di intercettare la richiesta di accedere al sito tramite email e password
 * Verifica che l'user è una persona esistente nel database
 * 
 *  
 * 
 * */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        try {

		        if (RegistrationDao.userValido(email, password)) {
		        	/*
		        	 * Crea una nuova sessione dell'utente loggato
		        	 */
		        	HttpSession session=request.getSession();
		        	session.setAttribute("User", RegistrationDao.getUser(email));
		        	
		            response.sendRedirect("greenHouseHome.jsp");
		            
		        } else {
		        	/*
		        	 * La linea di codice request.getRequestDispatcher("login.jsp").forward(request, response);
		        	 * ha il compito di inoltrare (o "forwardare") la richiesta corrente a un'altra risorsa, in questo caso, a login.jsp.
		        	 * 
		        	 *  -- Un RequestDispatcher è un oggetto che permette di inoltrare una richiesta a un'altra risorsa 
		        	 * (come un file JSP, un'altra servlet, ecc.) all'interno del server stesso, senza inviare un nuovo client-side redirect.
		        	 * 
		        	 * --l metodo forward del RequestDispatcher inoltra la richiesta e la risposta corrente alla risorsa specificata (qui login.jsp).
		        	 * 
		        	 * */
		        	request.setAttribute("errorMessage", "Login fallito: Nome utente e/o password non validi. Riprova.");
		        	request.getRequestDispatcher("login.jsp").forward(request, response);
		        }    
		 
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	     }
		 
		 
}


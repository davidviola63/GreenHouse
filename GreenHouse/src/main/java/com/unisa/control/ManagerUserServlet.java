package com.unisa.control;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unisa.dao.ManagerUserDao;
import com.unisa.model.UtenteBean;
import com.unisa.model.ArticoloBean;
import com.unisa.model.MetodoPagamentoBean;

@WebServlet("/ManagerUserServlet")
public class ManagerUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	        
		  String action = request.getParameter("action");

	        try {
	        	if ("registra".equals(action)) {
	        		registerUtente(request,response);
	        	}else if("login".equals(action)) {
	        		loginUtente(request, response);
	        	}else if("logout".equals(action)) {
	        		logoutUtente(request,response);
	        	}
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
		 
	    }
	 
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		
	}
	 
	 private void registerUtente(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		 	UtenteBean user=new UtenteBean();	 	
	        user.setNome(request.getParameter("nome"));
	        user.setCognome(request.getParameter("cognome")); 
	        user.setEmail(request.getParameter("email"));
	        user.setPassword(request.getParameter("password"));
	        user.setCitta(request.getParameter("citta"));
	        user.setVia(request.getParameter("via"));
	        user.setCivico(request.getParameter("civico"));
	        
	        MetodoPagamentoBean mp=new MetodoPagamentoBean();
	        mp.setNumeroCarta(request.getParameter("cartaCredito"));
	        mp.setData(LocalDate.parse(request.getParameter("dataScadenza")));
	        mp.setCircuito(request.getParameter("circuito"));
	        mp.setCvc(request.getParameter("cvc"));
	        
	        try {

	            if (ManagerUserDao.isEmailRegistered(user.getEmail())) {
	                request.setAttribute("errorMessage", "L'email è già registrata.Account esistente. Per favore, usa un'altra email.");
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	            } 
	            
	            
	            else {
	                
	            	boolean registered=ManagerUserDao.registerUser(user.getNome(), user.getCognome(), user.getEmail(), user.getPassword(),
	            			user.getCitta(),user.getVia(),user.getCivico());
	            	
	                	if(registered) {
	                		
	                		ManagerUserDao.registerCard(mp.getNumeroCarta(),user.getEmail(),mp.getData(),mp.getCircuito(),mp.getCvc());
	                		HttpSession session=request.getSession(false);
	                		
	                		session.setAttribute("User", ManagerUserDao.getUser(user.getEmail()));
		        	
	                		response.sendRedirect("greenHouseHome.jsp");
	                	}
	                		else {
	                			request.setAttribute("errorMessage", "Errore durante la registrazione. Per favore, riprova.");
	             	            request.getRequestDispatcher("login.jsp").forward(request, response);
	             	        }	                	
	                }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Errore durante la registrazione. Per favore, riprova.");
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	 }
	 
	 private void loginUtente(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		   
		 	String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        try {

		        if (ManagerUserDao.userValido(email, password)) {
		        	
		        	HttpSession sessione=request.getSession();
		        	
		        	sessione.setAttribute("User", ManagerUserDao.getUser(email));
		        	
		        	@SuppressWarnings("unchecked")
					List<ArticoloBean> carrello = (List<ArticoloBean>) sessione.getAttribute("carrello");
		        	
		        	if(carrello!=null) {		        		
		        		sessione.setAttribute("carrello", carrello );
		        	}
		        			        	
		            response.sendRedirect("greenHouseHome.jsp");
		            
		        } else {
		        	
		        	request.setAttribute("errorMessage", "Login fallito: Nome utente e/o password non validi. Riprova.");
		        	request.getRequestDispatcher("login.jsp").forward(request, response);
		        }    
		 
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	 }
	 
	 
		private void logoutUtente(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        
	        // Richiede la sessione corrente dalla richiesta
	        HttpSession session = request.getSession(false); // `false` non crea una nuova sessione se non esiste
	        
	        if (session != null) {
	            // Invalida la sessione
	            session.invalidate();
	        }

	        // Reindirizza l'utente alla pagina di login (o a qualsiasi altra pagina)
	        response.sendRedirect("greenHouseHome.jsp");
	    }
}
	 


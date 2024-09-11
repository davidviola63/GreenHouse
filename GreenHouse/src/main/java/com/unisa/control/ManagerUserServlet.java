package com.unisa.control;

import java.io.IOException;
import java.net.URLEncoder;
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
	                
	            	 String param = "L'email è già registrata.Account esistente. Per favore, usa un'altra email.";
	                 String encodedParam = URLEncoder.encode(param, "UTF-8");
	                response.sendRedirect("login.jsp?errorMessage="+encodedParam);
	                
	            } 
	            
	            
	            else {
	                
	            	boolean registered=ManagerUserDao.registerUser(user.getNome(), user.getCognome(), user.getEmail(), user.getPassword(),
	            			user.getCitta(),user.getVia(),user.getCivico());
	            	
	                	if(registered) {
	                		
	                		ManagerUserDao.registerCard(mp.getNumeroCarta(),user.getEmail(),mp.getData(),mp.getCircuito(),mp.getCvc());
	                		HttpSession sessione=request.getSession();
	                		
	                		sessione.setAttribute("User", ManagerUserDao.getUser(user.getEmail()));
	                		@SuppressWarnings("unchecked")
	    					List<ArticoloBean> carrello = (List<ArticoloBean>) sessione.getAttribute("carrello");
	    		        	
	    		        	if(carrello!=null) {		        		
	    		        		sessione.setAttribute("carrello", carrello );
	    		        	}
		        	
	                		response.sendRedirect("greenHouseHome.jsp");
	                	}
	                		else {
	                			 String param = "Errore durante la registrazione.";
	        	                 String encodedParam = URLEncoder.encode(param, "UTF-8");
	        	                response.sendRedirect("login.jsp?errorMessage="+encodedParam);
	             	        }	                	
	                }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            String param = "Errore durante la registrazione.";
                String encodedParam = URLEncoder.encode(param, "UTF-8");
                response.sendRedirect("login.jsp?errorMessage="+encodedParam);
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
		        	
		        	 String param = "Email o passowrd non validi. Riprova..";
	                 String encodedParam = URLEncoder.encode(param, "UTF-8");
	                response.sendRedirect("login.jsp?errorMessage="+encodedParam);
		        }    
		 
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	 }
	 
	 
		private void logoutUtente(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        	       
	        HttpSession session = request.getSession(false); 
	        if (session != null) {
	         
	            session.invalidate();
	        }

	        response.sendRedirect("greenHouseHome.jsp");
	    }
}
	 


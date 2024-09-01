package com.unisa.control;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unisa.dao.RegistrationDao;
import com.unisa.model.UtenteBean;
import com.unisa.model.MetodoPagamentoBean;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	        
		 // Preleva i dati dai campi del form che ha inviato la richiesta e li salva nei campi dell'UtenteBean
		 
		 	UtenteBean user=new UtenteBean();
		 	MetodoPagamentoBean mp=new MetodoPagamentoBean();
		 	
	        user.setNome(request.getParameter("nome"));
	        user.setCognome(request.getParameter("cognome")); 
	        user.setEmail(request.getParameter("email"));
	        user.setPassword(request.getParameter("password"));
	        user.setCitta(request.getParameter("citta"));
	        user.setVia(request.getParameter("via"));
	        user.setCivico(request.getParameter("civico"));
	        
	        mp.setNumeroCarta(request.getParameter("cartaCredito"));
	        mp.setData(LocalDate.parse(request.getParameter("dataScadenza")));
	        mp.setCircuito(request.getParameter("circuito"));
	        mp.setCvc(request.getParameter("cvc"));
	        
	        try {

	            /* 
	             * Controlla se l'email esiste già tramite la funzione apposita,
	             * Se l'email esiste aggiunge l'attributo errorMessage alla richiesta ed esegue un forwarding della richiesta alla pagina
	             * che l'ha inviata , in questo caso login.jsp
	             * 
	             */
	            if (RegistrationDao.isEmailRegistered(user.getEmail())) {
	                request.setAttribute("errorMessage", "L'email è già registrata.Account esistente. Per favore, usa un'altra email.");
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	            } 
	            
	            /* Se l'email non esiste allora inserisci il nuovo utente nel database
                 * crea una nuova sessione appartenente a quell'utente e reindirizza alla home
            	* dell'utente loggato
            	* infine chiude la connessione al database.
            	*/
	            
	            else {
	                
	            	boolean registered=RegistrationDao.registerUser(user.getNome(), user.getCognome(), user.getEmail(), user.getPassword(),
	            			user.getCitta(),user.getVia(),user.getCivico());
	            	
	                	if(registered) {
	                		
	                		RegistrationDao.registerCard(mp.getNumeroCarta(),user.getEmail(),mp.getData(),mp.getCircuito(),mp.getCvc());
	                		HttpSession session=request.getSession();
	                		session.setAttribute("User", RegistrationDao.getUser(user.getEmail()));
		        	
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
}
	 


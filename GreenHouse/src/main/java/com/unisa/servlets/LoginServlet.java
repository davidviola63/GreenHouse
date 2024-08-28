package com.unisa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unisa.dbconnection.DatabaseUtil;

import java.sql.*;
import java.sql.PreparedStatement;

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
       
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	        if (userValido(email, password)) {
	        	/*
	        	 * Crea una nuova sessione dell'utente loggato
	        	 */
	        	HttpSession session=request.getSession();
	        	session.setAttribute("Username", getUserName(email, password));
	        	
	            response.sendRedirect("greenHouseHomeLogged.jsp");
	            
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
	    }
	 
	 private boolean userValido(String email,String password) {
		 
		 /*
		  * Dichiaro la stringa di query e preparo l'oggetto PreparedStatement per accettare i parametri della funzione.
		  * Eseguo la connessione al database tramite DatabasaUtil 
		  * In seguito chiamo la funzione setString di PreparedStatement per sostituire i simboli '?' con i rispettivi valori
		  * Salvo il risultato della rispettiva query in un ResultSet e verifico che questo abbia un elemento con la funziona rs.next()
		  * Nel caso next sia true allora c'è l'user nel database e può loggarsi l'utente che ha inserito quelle credenziali.
		  * Nel caso sia falso si esegue il reindirizzamento su una pagina di errore
		  */
		
		 boolean isUserValido=false;
		 String query= "SELECT * FROM Utente WHERE Email= ? AND Password= ?" ; 
		 
		 try(Connection con= DatabaseUtil.getConnection(); PreparedStatement ps= con.prepareStatement(query) )
		 {
			
			 ps.setString(1, email);
			 ps.setString(2, password);
			 
			 ResultSet rs=ps.executeQuery();
			 isUserValido=rs.next();
		 
		 }
		 catch(SQLException e) 
		 {
			 e.printStackTrace();
		 }
			 
		 return isUserValido;
		 
	 }	 
	 
	 private String getUserName(String email,String password) {
		 
		 String nome="Guest";
		 
		 String query= "SELECT * FROM Utente WHERE Email= ? AND Password= ?" ; 
		 
		 try(Connection con= DatabaseUtil.getConnection(); PreparedStatement ps= con.prepareStatement(query) )
		 {
			
			 ps.setString(1, email);
			 ps.setString(2, password);
			 
			 ResultSet rs=ps.executeQuery();
			 rs.next();
			 nome=rs.getString("Nome");
			 con.close();
		 
		 }
		 catch(SQLException e) 
		 {
			 e.printStackTrace();
		 }
		
		 return nome;
	 }
		 
		 
}


package com.unisa.servlets;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unisa.dbconnection.DatabaseUtil;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
		 // Preleva i dati dai campi del form che ha inviato la richiesta
		 
	        String nome = request.getParameter("nome");
	        String cognome = request.getParameter("cognome");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String cartaCredito = request.getParameter("cartaCredito");

	        try {

	        	Connection con=DatabaseUtil.getConnection();
	            /* 
	             * Controlla se l'email esiste già tramite la funzione apposita,
	             * Se l'email esiste aggiunge l'attributo errorMessage alla richiesta ed esegue un forwarding della richiesta alla pagina
	             * che l'ha inviata , in questo caso login.jsp
	             * 
	             */
	            if (isEmailRegistered(con, email)) {
	                request.setAttribute("errorMessage", "L'email è già registrata. Per favore, usa un'altra email.");
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	            } 
	            
	            /* Se l'email non esiste allora inserisci il nuovo utente nel database
                 * crea una nuova sessione appartenente a quell'utente e reindirizza alla home
            	* dell'utente loggato
            	* infine chiude la connessione al database.
            	*/
	            
	            else {
	                
	            	int flag=registerUser(con, nome, cognome, email, password, cartaCredito);
	            	
	                	if(flag!=0) {
	                		HttpSession session=request.getSession();
	                		session.setAttribute("Username", getUserName(con,email, password));
		        	
	                		response.sendRedirect("greenHouseHomeLogged.jsp");
	                	}
	                		else {
	                			 request.setAttribute("errorMessage", "Errore durante la registrazione. Per favore, riprova.");
	             	            request.getRequestDispatcher("login.jsp").forward(request, response);
	             	        }
	                	
	                }
	            con.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Errore durante la registrazione. Per favore, riprova.");
	            request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	    }
	 
	 /*
	  * La funzione isEmailRegistered conta le occorrenze dell'email nella tabella utenti.
	  * Se l'email passata per parametro è presente nella tabella dopo aver eseguito la query allora 
	  * nell'oggetto ResultSet rs vi sarà almeno un elemento. 
	  */
	    private boolean isEmailRegistered(Connection con, String email) throws SQLException {
	        
	    	String query = "SELECT COUNT(*) FROM utente WHERE Email = ?";
	        
	        try(PreparedStatement ps = con.prepareStatement(query)) {	
	            ps.setString(1, email);
	            ResultSet rs = ps.executeQuery(); 
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        
	        return false;
	    }
	    
	    /*
	     * registerUser si occupa di registrare l'utente nel database
	     */

	    private int registerUser(Connection con, String nome, String cognome, String email, String password, String cartaCredito) throws SQLException {
	        
	    	int rowsInserted=0;
	    	String query = "INSERT INTO utente (Nome, Cognome, Email, Password, Numero_Carta, Ruolo) VALUES (?, ?, ?, ?, ?, ?)";
	       
	    	try (PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setString(1, nome);
	            ps.setString(2, cognome);
	            ps.setString(3, email);
	            ps.setString(4, password);
	            ps.setString(5, cartaCredito); 
	            ps.setString(6, "Cliente");
	            //ps.executeUpdate();
	            rowsInserted = ps.executeUpdate();

	            if (rowsInserted > 0) {
	                // Commit della transazione se l'inserimento è avvenuto con successo
	                con.commit();	                
	            } else {
	                // Rollback della transazione se l'inserimento non è riuscito
	                con.rollback();
	            }                    
	        }
	    	
	    	return rowsInserted;
	    	
	    }
	    
	    /*
	     * getUserName cerca nel database il nome dell'utente che si è registrato tramite la sua email e la sua password per poter iniziare
	     * una sessione a suo nome 
	     */
	    
	    private String getUserName(Connection con, String email,String password) throws SQLException {
			 
			 String nome="Guest";
			 
			 String query= "SELECT * FROM Utente WHERE Email= ? AND Password= ?" ; 
			 
			 try(PreparedStatement ps= con.prepareStatement(query) )
			 {
				
				 ps.setString(1, email);
				 ps.setString(2, password);
				 
				 ResultSet rs=ps.executeQuery();
				 rs.next();
				 nome=rs.getString("Nome");			
				
			 }
			
			 return nome;
		 }
}

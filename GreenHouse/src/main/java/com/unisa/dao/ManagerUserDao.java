package com.unisa.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.unisa.dbconnection.DatabaseUtil;
import com.unisa.model.UtenteBean;

public class ManagerUserDao {
	
		private static Connection con=null;
		private static PreparedStatement ps=null;
	 /*
	  * La funzione isEmailRegistered conta le occorrenze dell'email nella tabella utenti.
	  * Se l'email passata per parametro è presente nella tabella dopo aver eseguito la query allora 
	  * nell'oggetto ResultSet rs vi sarà almeno un elemento. 
	  */
	    public static boolean isEmailRegistered(String email) throws SQLException {
	        
	    	String query = "SELECT COUNT(*) FROM utente WHERE Email = ?";
	    	
	        try{	
	        	
	        	con=DatabaseUtil.getConnection();
	        	ps = con.prepareStatement(query);
	            ps.setString(1, email);
	            ResultSet rs = ps.executeQuery(); 
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }finally {
	            	con.close();
	            }
	        
	        return false;
	    }
	    
	    /*
	     * registerUser si occupa di registrare l'utente nel database
	     */

	    public static boolean registerUser(String nome, String cognome, String email, String password, String citta,
	    		String via, String civico) throws SQLException {
	        
	    	String queryUser = "INSERT INTO utente (Nome, Cognome, Email, Password, Citta, Via, Civico, Ruolo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    		    	
	    	try {
	    		
	    		con=DatabaseUtil.getConnection();
	    		ps = con.prepareStatement(queryUser);    		
	    		
	            ps.setString(1, nome);
	            ps.setString(2, cognome);
	            ps.setString(3, email);
	            ps.setString(4, password);
	            ps.setString(5, citta); 
	            ps.setString(6, via);
	            ps.setString(7, civico);
	            ps.setString(8, "Cliente");
	            
	            if (ps.executeUpdate() > 0) {
	            		         
	            		// Commit della transazione se l'inserimento è avvenuto con successo
	            		con.commit();
	            		return true;
	            	
	            } else {
	                // Rollback della transazione se l'inserimento non è riuscito
	                con.rollback();
	                return false;
	            }
            
	        }finally {
	        	con.close();
	        }	    	
    	
	    	
	    }
	    
	    public static boolean registerCard(String numeroCarta, String email, LocalDate dataScadenza, String circuito, String cvc) throws SQLException {
	        
	    	boolean cardRegistered=false;
	    	String queryCard = "INSERT INTO metodo_pagamento (Numero_Carta,Email_Utente, Data, Circuito, CVC) VALUES (?, ?, ?, ?, ?)";
	    	
	    	try {
	    		
	    		con=DatabaseUtil.getConnection();	    		
	    		ps=con.prepareStatement(queryCard);	    		
	    		
	    			ps.setString(1, numeroCarta);
	    			ps.setString(2, email);
	    			ps.setDate(3, Date.valueOf(dataScadenza));
	    			ps.setString(4, circuito);
	    			ps.setString(5, cvc);	            	             
	    	       	            	            	             
	            if (ps.executeUpdate() > 0) {
            		
	            		// Commit della transazione se l'inserimento è avvenuto con successo
	            		cardRegistered=true;
	            		con.commit();
	            	
	            	}
	            else {
	                // Rollback della transazione se l'inserimento non è riuscito
	                con.rollback();
	            }  	    		               
	        }finally {
	        	con.close();
	        }
	    	return cardRegistered;
	    	
	    }
	    
	    	    
	    /*
	     * getUserName cerca nel database il nome dell'utente che si è registrato tramite la sua email e la sua password per poter iniziare
	     * una sessione a suo nome 
	     */
	    
	    public static UtenteBean getUser(String email) throws SQLException {
			 
			 UtenteBean user=new UtenteBean();
			 
			 String query= "SELECT * FROM Utente WHERE Email= ?" ; 
			 
			 try {
				 con=DatabaseUtil.getConnection();
				 ps= con.prepareStatement(query); 
				 
				 ps.setString(1, email);				
				 
				 ResultSet rs=ps.executeQuery();
				 rs.next();
				 user.setNome(rs.getString("Nome"));
				 user.setCognome(rs.getString("Cognome"));
				 user.setEmail(rs.getString("Email"));
				 user.setPassword(rs.getString("Password"));
				 user.setCitta(rs.getString("Citta"));
				 user.setVia(rs.getString("Via"));
				 user.setCivico(rs.getString("Civico"));
				 user.setRuolo(rs.getString("Ruolo"));
				 user.setIdBonus(rs.getInt("ID_Bonus"));
				
			 }finally {
				 con.close();
			 }
			
			 return user;
		 }
	    

		 public static boolean userValido(String email,String password) throws SQLException {
			 
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
			 
			 try{
				 con=DatabaseUtil.getConnection();
				 ps= con.prepareStatement(query); 
				 
				 ps.setString(1, email);
				 ps.setString(2, password);
				 
				 ResultSet rs=ps.executeQuery();
				 isUserValido=rs.next();
			 
			 }finally {
				 con.close();
			 }
			 
				 
			 return isUserValido;
			 
		 }
		 
		 
		 public static boolean isUserCardValid(String numeroCarta, String emailUtente, LocalDate data, String circuito, String cvc) throws SQLException {
			 
			 
			 boolean isValid=false;
			 String query = "SELECT COUNT(*) FROM Metodo_Pagamento WHERE Numero_Carta = ? AND Email_Utente = ? AND Data = ? AND Circuito = ? AND Cvc = ?";
			 
			 try {
				
				 con = DatabaseUtil.getConnection();
				 ps = con.prepareStatement(query);
				 
				 ps.setString(1, numeroCarta);
				 ps.setString(2, emailUtente);
				 ps.setDate(3, Date.valueOf(data));
				 ps.setString(4, circuito);
				 ps.setString(5, cvc);

				 ResultSet rs = ps.executeQuery();

				 if (rs.next()) {
					 
					 isValid = (rs.getInt(1) > 0);
					 
				 }
				 
			 }finally {
				 
				 con.close();
			 }

			 return isValid;
		 }
}


package com.unisa.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unisa.dbconnection.DatabaseUtil;

public class RecyclingDao {
	

	private static Connection con=null;
	private static PreparedStatement ps=null;
	
	
	public static boolean registerMobileRiciclato(String emailUtente, InputStream immagine, String tipoMobile,
    		String commento) throws SQLException {
        
    	String queryUser = "INSERT INTO mobile_riciclato (Email_Utente, Immagine, Tipo_Mobile ,Commento) VALUES (?, ?, ?, ?)";
    		    	
    	try {
    		
    		con=DatabaseUtil.getConnection();
    		ps = con.prepareStatement(queryUser);
    		   	   		
            ps.setString(1, emailUtente);
            ps.setBlob(2, immagine);
            ps.setString(3, tipoMobile);
            ps.setString(4, commento);
           
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
	
	public static boolean isPossibleToSubmitRequest(String email) throws SQLException {
		 boolean canSubmit = false;
	        String query = "SELECT COUNT(*) FROM Mobile_Riciclato WHERE Email_Utente = ?";

	        try {
	        	con = DatabaseUtil.getConnection();
	        	ps = con.prepareStatement(query);
	            
	            ps.setString(1, email);	            	      
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                // Se il risultato è 0, l'utente può sottomettere la richiesta
	                canSubmit = rs.getInt(1) == 0;
	            }
	            
	        }finally {
	        	con.close();
	        }

	        return canSubmit;
	    }
	}
	

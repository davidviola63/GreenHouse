package com.unisa.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unisa.dbconnection.DatabaseUtil;
import com.unisa.model.MobileRiciclatoBean;

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
	
	
	
	public static boolean setBonusForUser(String emailUtente,int bonusId) throws SQLException{
		
		String query = "UPDATE Utente SET ID_Bonus = ? WHERE Email = ?";
    	
    	try {
    		
    		con=DatabaseUtil.getConnection();
    		ps = con.prepareStatement(query);
    		   	   		
    		ps.setInt(1, bonusId);
    		ps.setString(2, emailUtente);
            
            if (ps.executeUpdate() > 0) {
            		         
            		con.commit();
            		return true;
            	
            } else {

                con.rollback();
                return false;
            }
        
        }finally {
        	con.close();
        }	    	
		
		}
	
	public static List<MobileRiciclatoBean> getListMobiliRiciclati() throws SQLException {
		
        String query = "SELECT * FROM Mobile_Riciclato";       
        List<MobileRiciclatoBean> mobiliRiciclati = new ArrayList<>();
        ResultSet rs = null;

        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
            	
                MobileRiciclatoBean mobile = new MobileRiciclatoBean();
                mobile.setId(rs.getInt("ID"));
                mobile.setEmailUtente(rs.getString("Email_Utente"));
                mobile.setImmagine(rs.getBlob("Immagine"));
                mobile.setTipoMobile(rs.getString("Tipo_Mobile"));
                mobile.setCommento(rs.getString("Commento"));

                mobiliRiciclati.add(mobile);
            }
        } finally {
            con.close();
        }

        return mobiliRiciclati;
    }
	
public static MobileRiciclatoBean getMobileRiciclato(int id) throws SQLException {
		
        String query = "SELECT * FROM Mobile_Riciclato WHERE ID= ? ";       
        ResultSet rs = null;
        MobileRiciclatoBean mobile = null;
        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
            	
            	mobile = new MobileRiciclatoBean();
                mobile.setId(rs.getInt("ID"));
                mobile.setEmailUtente(rs.getString("Email_Utente"));
                mobile.setImmagine(rs.getBlob("Immagine"));
                mobile.setTipoMobile(rs.getString("Tipo_Mobile"));
                mobile.setCommento(rs.getString("Commento"));
            }
        } finally {
            con.close();
        }

        return mobile;
    }


	
	public static boolean deleteMobileRiciclato(int id) throws SQLException {
        String query = "DELETE FROM Mobile_Riciclato WHERE ID = ?";

        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement(query);

            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
		         
        		con.commit();
        		return true;
        	
        } else {

            con.rollback();
            return false;
        }
        } finally {
          con.close();
        }
    }
}
	
	
	

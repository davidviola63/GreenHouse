package com.unisa.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.unisa.dbconnection.DatabaseUtil;
import com.unisa.model.ArticoloBean;

public class ManagerArticoloDao {
	
	private static Connection con=null;
	private static PreparedStatement ps=null;
	
	
	/*AGGIUNGE UN ARTICOLO NEL DATABASE*/
	public static boolean addArticolo(String tipologia,String nome,String descrizione, double prezzo, int quantita, double iva,InputStream immagine) throws SQLException{
						
		String query="INSERT INTO Articolo (Tipologia, Nome, Descrizione, Prezzo, Quantita_Disponibile, IVA, Immagine) VALUES (?, ?, ?, ?, ?, ?, ?)";;
		
		try {
			con=DatabaseUtil.getConnection();
			ps=con.prepareStatement(query);
			
			System.out.println(tipologia);
			ps.setString(1, tipologia);  
			ps.setString(2, nome); 
			ps.setString(3, descrizione);  
			ps.setDouble(4, prezzo);  
			ps.setInt(5, quantita);  
			ps.setDouble(6, iva);  
			ps.setBlob(7, immagine);
			
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
		
	
	/*ELIMINA L'ARTICOLO DAL DATABASE TRAMITE IL SUO ID*/
	
	public static boolean deleteArticolo(int id) throws SQLException{
	
	String query="DELETE FROM Articolo WHERE id= ?";
	
	try {
		con=DatabaseUtil.getConnection();
		ps=con.prepareStatement(query);
		
		ps.setInt(1, id);
		
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
	
	
	
	/*CERCA L'ARTICOLO NEL DATABASE TRAMITE L'ID*/
	public static ArticoloBean getArticolo(int id) throws SQLException{
		
		String query="SELECT * FROM Articolo WHERE id= ?";
		ArticoloBean articolo=null;
		try {
			con=DatabaseUtil.getConnection();
			ps=con.prepareStatement(query);
			
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
				if (rs.next()) {
		         
	    			articolo=new ArticoloBean();
	    			articolo.setId(rs.getInt("ID"));
	    			articolo.setTipologia(rs.getString("Tipologia"));
	    			articolo.setNome(rs.getString("Nome"));
	    			articolo.setDescrizione(rs.getString("Descrizione"));
	    			articolo.setPrezzo(rs.getDouble("Prezzo"));
	    			articolo.setQuantita(rs.getInt("Quantita_Disponibile"));
	    			articolo.setIva(rs.getDouble("IVA"));
	    			
	    			articolo.setImmagine(GetPictureDao.loadImmagineArticolo(articolo.getId()));
	    	
				} 
				
				
			}finally {
				con.close();
			}
		
			return articolo;
			
		}
	
	public static List<ArticoloBean> getListOfArticoloByType(String tipologia) throws SQLException{
		
		 String query = "SELECT * FROM articolo WHERE Tipologia = ? ";
		 List<ArticoloBean> articoli = new ArrayList<ArticoloBean>();

        try{
        	
        	con=DatabaseUtil.getConnection();
    		ps=con.prepareStatement(query);
                      
    		ps.setString(1, tipologia);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	
                ArticoloBean articolo = new ArticoloBean();
                articolo.setId(rs.getInt("ID"));
                articolo.setTipologia(rs.getString("Tipologia"));
                articolo.setNome(rs.getString("Nome"));
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setQuantita(rs.getInt("Quantita_Disponibile"));
                articolo.setIva(rs.getDouble("IVA"));
                
                articolo.setImmagine(GetPictureDao.loadImmagineArticolo(articolo.getId()));
                
                articoli.add(articolo);
            }
            
         }finally {
				con.close();

        }
        
        return articoli;
        
	}
	
	
	public static boolean reduceQuantityOfArticolo(int id, int quantita) throws SQLException {
		
	    String updateQuery = "UPDATE articolo SET Quantita_Disponibile = Quantita_Disponibile - ? WHERE ID = ?";
	    String selectQuery = "SELECT Quantita_Disponibile FROM articolo WHERE ID = ?";
	    
	    PreparedStatement psUpdate = null;
	    PreparedStatement psSelect = null;
	    ResultSet rs = null;

	    try {
	    	
	        con = DatabaseUtil.getConnection();
	        con.setAutoCommit(false);


	        psSelect = con.prepareStatement(selectQuery);
	        psSelect.setInt(1, id);
	        rs = psSelect.executeQuery();

	        if (rs.next()) {
	            int quantitaAttuale = rs.getInt("Quantita_Disponibile");
	            
	            /*
	             * Se la quantità  risulterebbe essere negativa annulla la transazione
	             */
	            if (quantitaAttuale - quantita < 0) {
	                con.rollback();
	                return false;  
	            }

	           
	            psUpdate = con.prepareStatement(updateQuery);
	            psUpdate.setInt(1, quantita);
	            psUpdate.setInt(2, id);
	            psUpdate.executeUpdate();

	            /*
	             *  Se la quantità arriva esattamente a 0, rimuovi l'articolo
	             */
	            if (quantitaAttuale - quantita == 0) {
	                ManagerArticoloDao.deleteArticolo(id);
	            }

	            con.commit();  
	            return true;   
	            
	        } else {
	        	/*
	        	 * Se l'articolo non è stato trovato annulla la transazione
	        	 */
	            con.rollback();  
	            return false;   
	            
	        }
	    } finally {
	       
	       con.close();
	       
	    }
	}
	
}

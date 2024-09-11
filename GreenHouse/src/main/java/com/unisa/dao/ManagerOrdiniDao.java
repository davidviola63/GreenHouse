package com.unisa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unisa.dbconnection.DatabaseUtil;
import com.unisa.model.OrdineBean;
import com.unisa.model.ArticoloBean;
import com.unisa.model.ComponeBean;

public class ManagerOrdiniDao {
	
	private static Connection con=null;
	private static PreparedStatement ps=null;
	
	
	public static boolean addOrdine(OrdineBean ordine, List<ArticoloBean> articoli, int idBonus) throws SQLException {
	    String queryOrdine = "INSERT INTO Ordine (Email_Utente, stato, data_acquisto, codice_fattura) VALUES (?, ?, ?, ?)";
	    String selectId = "SELECT MAX(ID) FROM Ordine WHERE Email_Utente= ? AND Codice_Fattura = ?";
	    boolean executed = true;
	    int idOrdine = -1;

	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = DatabaseUtil.getConnection();
	        con.setAutoCommit(false);  // Disabilita l'auto commit per gestire manualmente la transazione

	        // Inserisce l'ordine
	        ps = con.prepareStatement(queryOrdine);
	        ps.setString(1, ordine.getEmailUtente());
	        ps.setString(2, "In attesa");
	        ps.setDate(3, Date.valueOf(ordine.getDataAcquisto()));
	        ps.setString(4, ordine.getCodFattura());

	        if (ps.executeUpdate() > 0) {
	            con.commit();
	            ps = con.prepareStatement(selectId);
	            ps.setString(1, ordine.getEmailUtente());
	            ps.setString(2, ordine.getCodFattura());

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                idOrdine = rs.getInt(1);  // Ottiene il massimo ID

	                // Ciclo per ogni articolo
	                for (ArticoloBean articolo : articoli) {
	                    if (!ManagerOrdiniDao.addComponents(articolo, idOrdine, idBonus, ordine)) {
	                        System.out.println("Errore generato da ManagerOrdiniDao.addComponents");
	                        executed = false;
	                        break;
	                    } else {
	                        if (!ManagerArticoloDao.reduceQuantityOfArticolo(articolo.getId())) {
	                            System.out.println("Impossibile eseguire ManagerArticoloDao.reduceQuantityOfArticolo");
	                            
	                            ManagerOrdiniDao.ripristinaQuantitaArticoli(idOrdine);
	                            ManagerOrdiniDao.deleteAllComponentsOfOrdine(idOrdine);
	                            ManagerOrdiniDao.deleteOrdine(idOrdine);
	                            executed = false;
	                            break;
	                        }
	                    }
	                }
	            }
	        } else {
	            executed = false;
	        }

	        if (executed) {
	            con.commit();
	        } else {
	            con.rollback();
	        }

	    } catch (SQLException e) {
	        if (con != null) {
	            con.rollback();  
	        }
	        executed = false;
	    } finally {
	      con.close();
	    }

	    return executed;
	}
	
	
	private static boolean deleteAllComponentsOfOrdine(int idOrdine) throws SQLException {
	    String query = "DELETE FROM Compone WHERE ID_Ordine = ?";

	    try {
	        con = DatabaseUtil.getConnection();
	        ps = con.prepareStatement(query);

	        ps.setInt(1, idOrdine);
	        

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


	public static boolean deleteOrdine(int id) throws SQLException {
	    String query = "DELETE FROM Ordine WHERE ID = ?";

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
	
	
	public static void ripristinaQuantitaArticoli(int idOrdine) throws SQLException {
	    String updateQuery = "UPDATE Articolo " +
	    					"JOIN Compone ON Articolo.ID = Compone.ID_Articolo " +
	    					"SET Articolo.Quantita_Disponibile = Articolo.Quantita_Disponibile + Compone.Quantita_Selezionata - 1 " +
	    					"WHERE Compone.ID_Ordine = ?";
	    try {
	        con = DatabaseUtil.getConnection();
	        ps = con.prepareStatement(updateQuery);
	        ps.setInt(1, idOrdine);  

	        if( ps.executeUpdate()>0) {
	        	con.commit();
	        }
	        
	    }  finally {
	    	con.close();
	    }
	}
	
	public static OrdineBean getOrdine(int id) throws SQLException {
	    String query = "SELECT * FROM Ordine WHERE ID = ?";
	    OrdineBean ordine = null;

	    try {
	        con = DatabaseUtil.getConnection();
	        ps = con.prepareStatement(query);

	        ps.setInt(1, id);	        
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            ordine = new OrdineBean();
	            ordine.setIdOrdine(id);
	            ordine.setEmailUtente(rs.getString("Email_Utente"));
	            ordine.setStato(rs.getString("stato"));
	            ordine.setDataAcquisto(rs.getDate("Data_Acquisto").toLocalDate());

	        }

	    } finally {
	        con.close();
	    }

	    return ordine;
	}
	
	
	public static List<OrdineBean> getListOfOrdini() throws SQLException {
        String query = "SELECT * FROM Ordine";
        List<OrdineBean> ordini = new ArrayList<>();

        try {
            con = DatabaseUtil.getConnection();
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdineBean ordine = new OrdineBean();
                ordine.setIdOrdine(rs.getInt("ID"));
                ordine.setEmailUtente(rs.getString("Email_Utente"));
                ordine.setStato(rs.getString("stato"));
                ordine.setDataAcquisto(rs.getDate("data_acquisto").toLocalDate()); // Converte java.sql.Date in LocalDate
                

                ordini.add(ordine);
            }

        } finally {
             con.close();
        }

        return ordini;
    }
	
	
	 public static boolean updateStatoOrdine(int id, String stato) throws SQLException {
	        String query = "UPDATE Ordine SET stato = ? WHERE ID = ?";

	        try {
	            con = DatabaseUtil.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setString(1, stato); 
	            ps.setInt(2, id);       

	            int rowsUpdated = ps.executeUpdate();
	            if (rowsUpdated > 0) {
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


	public static List<ComponeBean> getComponentsOfOrdine(int id) throws SQLException {
	    	
	        String query = "SELECT * FROM Compone WHERE ID_Ordine = ?";
	        List<ComponeBean> components = new ArrayList<>();

	        try {
	            con = DatabaseUtil.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                ComponeBean compone = new ComponeBean();
	                compone.setIdArticolo(rs.getInt("ID_Articolo"));
	                compone.setIdOrdine(rs.getInt("ID_Ordine"));
	                compone.setNome(rs.getString("Nome_Articolo"));
	                compone.setPrezzo(rs.getDouble("Prezzo_Articolo"));
	                compone.setQuantita(rs.getInt("Quantita_Selezionata"));
	                compone.setValutazione(rs.getInt("Valutazione"));

	                components.add(compone);
	            }
	        } finally {
	           con.close();
	        }

	        return components;
	    }
	    

	public static List<OrdineBean> getOrdiniByUtente(String emailUtente) throws SQLException {
	        String query = "SELECT * FROM Ordine WHERE Email_Utente = ?";
	        List<OrdineBean> ordini = new ArrayList<>();

	        try {
	            con = DatabaseUtil.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setString(1, emailUtente); // Imposta l'email dell'utente

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                OrdineBean ordine = new OrdineBean();
	                ordine.setIdOrdine(rs.getInt("ID"));
	                ordine.setEmailUtente(rs.getString("Email_Utente"));
	                ordine.setStato(rs.getString("stato"));
	                ordine.setDataAcquisto(rs.getDate("data_acquisto").toLocalDate());	                
	                ordine.setPrezzoTotale(getPrezzoTotaleOrdine(ordine.getIdOrdine()));
	                ordini.add(ordine);
	            }
	        } finally {
	             con.close();
	        }

	        return ordini;
	    }
	
	    
	public static double getPrezzoTotaleOrdine(int idOrdine) {
		
		 double totaleOrdine = 0.0;
	        
	        String query = "SELECT SUM(Prezzo_Articolo * Quantita_Selezionata) AS TotaleOrdine " +
	                       "FROM Compone " +
	                       "WHERE ID_Ordine = ?";

	        try {
	        	con = DatabaseUtil.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setInt(1, idOrdine);	            

	            try (ResultSet resultSet = ps.executeQuery()) {
	            
	                if (resultSet.next()) {
	                    totaleOrdine = resultSet.getDouble("TotaleOrdine");
	                }
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return totaleOrdine;
	 	}
	
	

	
	
	public static boolean addComponents(ArticoloBean articolo, int idOrdine,int idBonus, OrdineBean ordine) throws SQLException {
		
        PreparedStatement psSelect = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psInsert = null; 
        try {

            con = DatabaseUtil.getConnection();

            // Verifica se l'articolo esiste già nella tabella `Compone` per quell'ordine
            
            String query = "SELECT Quantita_Selezionata FROM Compone WHERE ID_Articolo = ? AND ID_Ordine = ?";
            psSelect = con.prepareStatement(query);
            psSelect.setInt(1, articolo.getId());
            psSelect.setInt(2, idOrdine);

            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                // Se esiste, aggiorna la quantità
                                         
                query = "UPDATE Compone SET Quantita_Selezionata = Quantita_Selezionata + 1 WHERE ID_Articolo = ? AND ID_Ordine = ?";
                psUpdate = con.prepareStatement(query);
                
                psUpdate.setInt(1, articolo.getId());
                psUpdate.setInt(2, idOrdine);

                int rowsUpdated = psUpdate.executeUpdate();
                if (rowsUpdated > 0) {
	                con.commit();
	                return true;  
	            } else {
	                con.rollback();
	                return false;  
	            }
                
            } else {
            	
                // Se non esiste, inserisci un nuovo record
            	
            	
                query = "INSERT INTO Compone (ID_Articolo, ID_Ordine,Nome_Articolo, Prezzo_Articolo, Quantita_Selezionata) VALUES (?, ?, ?, ?, ?)";              
                
                psInsert = con.prepareStatement(query);
                psInsert.setInt(1, articolo.getId());
                psInsert.setInt(2, idOrdine);
                psInsert.setString(3, articolo.getNome());
                if(RecyclingDao.getValueBonus(idBonus)==0) {           
                	psInsert.setDouble(4, articolo.getPrezzo());
                }else {
                	double sconto= articolo.getPrezzo()*RecyclingDao.getValueBonus(idBonus)/100;
                	double prezzoScontato=articolo.getPrezzo()-sconto;
                	psInsert.setDouble(4, prezzoScontato);
                	RecyclingDao.setBonusForUser(ordine.getEmailUtente(), 1);
                }
                               
                psInsert.setInt(5, 1 );

                int rowsInserted = psInsert.executeUpdate();
                
                if (rowsInserted > 0) {
	                con.commit();  
	                return true;  
	            } else {
	                con.rollback();
	                return false;  
	            }
            }

        } finally {
        	
               con.close();
            
        }

    }


	public static boolean updateValutazioneComponente(int idOrdine, int idArticolo, int valutazione) throws SQLException {
	 	
		 String query = "UPDATE Compone SET Valutazione = ? WHERE ID_Ordine = ? AND ID_Articolo = ?";
		
	    try {
	    	 con = DatabaseUtil.getConnection();
	         ps = con.prepareStatement(query);
	       
	        
	        // 
	        ps = con.prepareStatement(query);
	        ps.setInt(1, valutazione); 
	        ps.setInt(2, idOrdine);    
	        ps.setInt(3, idArticolo);  
	        
	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
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


	public static List<OrdineBean> getOrdiniByDataRange(String dataInizio, String dataFine)throws SQLException {
		
		List<OrdineBean> ordini = new ArrayList<>();
        String query = "SELECT * FROM Ordine WHERE Data_Acquisto BETWEEN ? AND ?";

        try  {          
        	con = DatabaseUtil.getConnection();
        	ps = con.prepareStatement(query);
        	ps.setString(1, dataInizio);
            ps.setString(2, dataFine);

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                OrdineBean ordine = new OrdineBean();
                ordine.setIdOrdine(rs.getInt("ID"));
                ordine.setEmailUtente(rs.getString("Email_Utente"));
                ordine.setStato(rs.getString("stato"));
                ordine.setDataAcquisto(rs.getDate("data_acquisto").toLocalDate());	                
                ordine.setPrezzoTotale(getPrezzoTotaleOrdine(ordine.getIdOrdine()));
                ordini.add(ordine);
            
            }

        }finally {
        	con.close();
        }

        return ordini;
	}
	
	
}
	


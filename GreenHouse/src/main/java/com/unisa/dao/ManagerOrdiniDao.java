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
import com.unisa.model.ComponeBean;

public class ManagerOrdiniDao {
	
	private static Connection con=null;
	private static PreparedStatement ps=null;
	
	
	public static boolean addOrdine(OrdineBean ordine) throws SQLException {
	    String query = "INSERT INTO Ordine (Email_Utente, stato, data_acquisto, codice_fattura) VALUES (?, ?, ?, ?)";

	    try {
	        con = DatabaseUtil.getConnection();
	        ps = con.prepareStatement(query);

	        ps.setString(1, ordine.getEmailUtente());
	        ps.setString(2, ordine.getStato());
	        ps.setDate(3, Date.valueOf(ordine.getDataAcquisto()));
	        ps.setString(4, ordine.getCodFattura());

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
	            ordine.setCodFattura(rs.getString("codice_fattura"));
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
                ordine.setCodFattura(rs.getString("Codice_Fattura"));

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
	            ps.setString(1, stato);  // Imposta il nuovo stato
	            ps.setInt(2, id);        // Imposta l'ID dell'ordine da modificare

	            int rowsUpdated = ps.executeUpdate();
	            if (rowsUpdated > 0) {
	                con.commit();  // Commit della transazione
	                return true;   // L'aggiornamento è stato eseguito con successo
	            } else {
	                con.rollback(); // Rollback della transazione in caso di errore
	                return false;   // Nessuna riga è stata aggiornata
	            }

	        } finally {
	             con.close();
	        }
	    }

	    /* RESTITUISCE I COMPONENTI DI UN ORDINE SOTTO FORMA DI LISTA DI ComponeBean */
	    public static List<ComponeBean> getComponentsOfOrdine(int id) throws SQLException {
	    	
	        String query = "SELECT * FROM Compone WHERE ID_Ordine = ?";
	        List<ComponeBean> components = new ArrayList<>();

	        try {
	            con = DatabaseUtil.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setInt(1, id); // Imposta l'ID dell'ordine

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                ComponeBean compone = new ComponeBean();
	                compone.setIdArticolo(rs.getInt("ID_Articolo"));
	                compone.setIdOrdine(rs.getInt("ID_Ordine"));
	                compone.setPrezzo(rs.getDouble("Prezzo_Articolo"));
	                compone.setQuantita(rs.getInt("Quantita_Selezionata"));

	                components.add(compone);
	            }
	        } finally {
	           con.close();
	        }

	        return components;
	    }
	    
	    /* RESTITUISCE TUTTI GLI ORDINI DI UN UTENTE SOTTO FORMA DI LISTA DI OrdineBean */
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
	                ordine.setIdOrdine(0);
	                ordine.setEmailUtente(rs.getString("Email_Utente"));
	                ordine.setStato(rs.getString("stato"));
	                ordine.setDataAcquisto(rs.getDate("data_acquisto").toLocalDate());
	                ordine.setCodFattura(rs.getString("Codice_Fattura"));

	                ordini.add(ordine);
	            }
	        } finally {
	             con.close();
	        }

	        return ordini;
	    }
	
	
}

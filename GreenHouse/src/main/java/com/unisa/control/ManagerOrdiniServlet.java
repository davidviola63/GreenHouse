package com.unisa.control;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unisa.dao.ManagerOrdiniDao;
import com.unisa.model.ComponeBean;
import com.unisa.model.OrdineBean;


@WebServlet("/ManagerOrdiniServlet")
public class ManagerOrdiniServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");

            try {
            	if ("visualizzaOrdini".equals(action)) {
            		mostraOrdini(request,response);           		
            	}else if("visualizzaComponentiOrdine".equals(action)) {
            		mostraComponentiOrdine(request,response);
            	}else if("modificaStatoOrdine".equals(action)){
            		modificaStatoOrdine(request,response);
            	}else if("visualizzaOrdiniUtente".equals(action)){           	
            		mostraOrdiniOfUtente(request, response);
            	}else if("visualizzaOrdiniPerData".equals(action)) {
            		mostraOrdiniByDataRange(request, response);
            	}
            	else if("aggiornaValutazione".equals(action)) {
            		aggiornaValutazione(request,response);
            	}
            }catch (SQLException e) {
                e.printStackTrace();
                String param = "Exception rilevata.";
	             String encodedParam = URLEncoder.encode(param, "UTF-8");
	             response.sendRedirect("error.jsp?errorMessage="+encodedParam);
            }
        }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	doPost(request,response);
    	
    }
	  
    private void aggiornaValutazione(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
    	int idOrdine= Integer.parseInt(request.getParameter("idOrdine"));
    	int idArticolo=Integer.parseInt(request.getParameter("idArticolo"));
    	int valutazione=Integer.parseInt(request.getParameter("valutazione"));
    	
    	boolean isUpdated =ManagerOrdiniDao.updateValutazioneComponente(idOrdine,idArticolo,valutazione);
    	
    			   if (isUpdated) {
    				   String param = "Valutazione inserita con successo.";
    		             String encodedParam = URLEncoder.encode(param, "UTF-8");
    		             response.sendRedirect("ordini.jsp?message="+encodedParam);
    	        } else {
    	        	 String param = "Bonus aggiunto con successo.";
    	             String encodedParam = URLEncoder.encode(param, "UTF-8");
    	             response.sendRedirect("ordini.jsp?message="+encodedParam);
    	        }
	}

	private void modificaStatoOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
  
    	int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
        String nuovoStato = request.getParameter("statoOrdine");

        boolean isUpdated = ManagerOrdiniDao.updateStatoOrdine(idOrdine, nuovoStato);
            
        if (isUpdated) {
        	 String param = "Stato ordine aggiornato con successo.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("panelAdmin.jsp?message="+encodedParam);
        } else {
        	 String param = "Errore nell'aggiornamento dell'ordine.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
        }      
	}
	
	private void mostraOrdini(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
    	List<OrdineBean> ordini = ManagerOrdiniDao.getListOfOrdini();        
    	
    	StringBuilder html = new StringBuilder();
        
        if (ordini != null && !ordini.isEmpty()) {
            for (OrdineBean ordine : ordini) {
                html.append("<div class='ordine'>");
                html.append("<h3>Ordine #").append(ordine.getIdOrdine()).append("</h3>");
                html.append("<p>Email Utente: ").append(ordine.getEmailUtente()).append("</p>");
                html.append("<p>Stato consegna: ").append(ordine.getStato()).append("</p>");
                html.append("<p>Data: ").append(ordine.getDataAcquisto()).append("</p>");
                html.append("<p>Codice Fattura: ").append(ordine.getCodFattura()).append("</p>");
                html.append("<p>Totale:&euro;").append(ManagerOrdiniDao.getPrezzoTotaleOrdine(ordine.getIdOrdine())).append("</p>");
                html.append("<button onclick='getDettagliOrdine(").append(ordine.getIdOrdine()).append(")'>Mostra Dettagli</button>");
                
                String cambiaStato= request.getParameter("cambiaStato");
                if(cambiaStato!= null) {
                	html.append("<form action=\"ManagerOrdiniServlet\" method=\"post\">")                    
                    .append("<input type=\"hidden\" name=\"action\" value=\"modificaStatoOrdine\">")
                    .append("<input type=\"hidden\" id=\"idOrdine\" name=\"idOrdine\" value=\"").append(ordine.getIdOrdine()).append("\" required>")
                    .append("<label for=\"statoOrdine\">Nuovo Stato:</label>")
                    .append("<select id=\"statoOrdine\" name=\"statoOrdine\" required>")
                    .append("<option value=\"sospeso\">Sospeso</option>")
                    .append("<option value=\"spedito\">Spedito</option>")
                    .append("<option value=\"consegnato\">Consegnato</option>")
                    .append("</select>")
                    .append("<button type=\"submit\">Modifica Stato</button>")
                    .append("</form>");
                }
                
                
                html.append("</div>");
            }
        } else {
            html.append("<p>Nessun ordine trovato</p>");
        }

        response.setContentType("text/html");
        response.getWriter().write(html.toString());		
    }
	  
    private void mostraComponentiOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
	 int id = Integer.parseInt(request.getParameter("ordineId"));
 
     List<ComponeBean> componenti = ManagerOrdiniDao.getComponentsOfOrdine(id);

     StringBuilder html = new StringBuilder();
     
     if (componenti != null && !componenti.isEmpty()) {
    	 
         html.append("<div class='dettagli-ordine'>");
         html.append("<h4>Dettagli dell'Ordine:</h4>");
         html.append("<ul>");
         for (ComponeBean componente : componenti) {
             html.append("<li>");
             html.append("Prodotto: ").append(componente.getNome()).append(", ");
             html.append("Prezzo: &euro;").append(componente.getPrezzo()).append(", ");
             html.append("Quantità: ").append(componente.getQuantita()).append(", ");
             html.append("Valutazione Inserita: ").append(componente.getValutazione()).append(", ");
             html.append("</li>");
         }
         html.append("</ul>");                 
         html.append("</div>");
     } else {
         html.append("<p>Nessun componente trovato per questo ordine</p>");
     }

     response.setContentType("text/html");
     response.getWriter().write(html.toString());
         
 	}
    
    private void mostraOrdiniOfUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	    	
    	List<OrdineBean> ordini = ManagerOrdiniDao.getOrdiniByUtente((String) request.getParameter("email"));        
    	
    	StringBuilder html = new StringBuilder();
        
        if (ordini != null && !ordini.isEmpty()) {
            for (OrdineBean ordine : ordini) {
                html.append("<div class='ordine'>");
                html.append("<h3>Ordine #").append(ordine.getIdOrdine()).append("</h3>");
                html.append("<p>Email Utente: ").append(ordine.getEmailUtente()).append("</p>");
                html.append("<p>Stato consegna: ").append(ordine.getStato()).append("</p>");
                html.append("<p>Data: ").append(ordine.getDataAcquisto()).append("</p>");
                html.append("<p>Codice Fattura: ").append(ordine.getCodFattura()).append("</p>");
                html.append("<p>Totale: &euro;").append(ManagerOrdiniDao.getPrezzoTotaleOrdine(ordine.getIdOrdine())).append("</p>");
                html.append("<button onclick='getDettagliOrdine(").append(ordine.getIdOrdine()).append(")'>Mostra Dettagli</button>");
               
                String cambiaStato= request.getParameter("cambiaStato");
                if(cambiaStato!= null) {
                	html.append("<form action=\"ManagerOrdiniServlet\" method=\"post\">")                    
                    .append("<input type=\"hidden\" name=\"action\" value=\"modificaStatoOrdine\">")
                    .append("<input type=\"hidden\" id=\"idOrdine\" name=\"idOrdine\" value=\"").append(ordine.getIdOrdine()).append("\" required>")
                    .append("<label for=\"statoOrdine\">Nuovo Stato:</label>")
                    .append("<select id=\"statoOrdine\" name=\"statoOrdine\" required>")
                    .append("<option value=\"sospeso\">Sospeso</option>")
                    .append("<option value=\"spedito\">Spedito</option>")
                    .append("<option value=\"consegnato\">Consegnato</option>")
                    .append("</select>")
                    .append("<button type=\"submit\">Modifica Stato</button>")
                    .append("</form>");
                }
                html.append("</div>");
            }
        } else {
            html.append("<p>Nessun ordine trovato</p>");
        }

        response.setContentType("text/html");
        response.getWriter().write(html.toString());
        
    }
      
    private void mostraOrdiniByDataRange (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	
    	List<OrdineBean> ordini = ManagerOrdiniDao.getOrdiniByDataRange(request.getParameter("dataInizio"),request.getParameter("dataFine"));        
    	
    	StringBuilder html = new StringBuilder();
        
        if (ordini != null && !ordini.isEmpty()) {
            for (OrdineBean ordine : ordini) {
                html.append("<div class='ordine'>");
                html.append("<h3>Ordine #").append(ordine.getIdOrdine()).append("</h3>");
                html.append("<p>Email Utente: ").append(ordine.getEmailUtente()).append("</p>");
                html.append("<p>Stato consegna: ").append(ordine.getStato()).append("</p>");
                html.append("<p>Data: ").append(ordine.getDataAcquisto()).append("</p>");
                html.append("<p>Codice Fattura: ").append(ordine.getCodFattura()).append("</p>");
                html.append("<p>Totale: &euro;").append(ManagerOrdiniDao.getPrezzoTotaleOrdine(ordine.getIdOrdine())).append("</p>");
                html.append("<button onclick='getDettagliOrdine(").append(ordine.getIdOrdine()).append(")'>Mostra Dettagli</button>");
                
                String cambiaStato= request.getParameter("cambiaStato");
                if(cambiaStato!= null) {
                	html.append("<form action=\"ManagerOrdiniServlet\" method=\"post\">")                    
                    .append("<input type=\"hidden\" name=\"action\" value=\"modificaStatoOrdine\">")
                    .append("<input type=\"hidden\" id=\"idOrdine\" name=\"idOrdine\" value=\"").append(ordine.getIdOrdine()).append("\" required>")
                    .append("<label for=\"statoOrdine\">Nuovo Stato:</label>")
                    .append("<select id=\"statoOrdine\" name=\"statoOrdine\" required>")
                    .append("<option value=\"sospeso\">Sospeso</option>")
                    .append("<option value=\"spedito\">Spedito</option>")
                    .append("<option value=\"consegnato\">Consegnato</option>")
                    .append("</select>")
                    .append("<button type=\"submit\">Modifica Stato</button>")
                    .append("</form>");
                }
                
                html.append("</div>");
            }
        } else {
            html.append("<p>Nessun ordine trovato</p>");
        }

        response.setContentType("text/html");
        response.getWriter().write(html.toString());
        
    }
    
    
    
    
    
}

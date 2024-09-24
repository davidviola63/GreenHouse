package com.unisa.control;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unisa.dao.ManagerArticoloDao;
import com.unisa.model.ArticoloBean;

@MultipartConfig(maxFileSize = 16177215) // Dimensione massima 16MB
@WebServlet("/ManagerArticoloServlet")
public class ManagerArticoloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    	String action = request.getParameter("action");
    	
         try {
             if ("add".equals(action)) {           	 
                 aggiungiArticolo(request, response);
             } else if ("rimuovi".equals(action)) {
                 rimuoviArticolo(request, response);
             }else if("modifica".equals(action)){
            	 modificaArticolo(request,response);
             }else if ("mostraPerTipologia".equals(action)) {
            	  mostraArticoliPerTipologia(request, response);
             }else if ("mostraTutti".equals(action)) {
            	 mostraArticoli(request, response);
             }
             else {
            	 request.setAttribute("errorMessage", "Si è verificato un errore durante l'elaborazione della richiesta.");
                 request.getRequestDispatcher("error.jsp").forward(request, response);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }

    private void aggiungiArticolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
    	String tipologia = request.getParameter("tipologia");
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        int quantita = Integer.parseInt(request.getParameter("quantita_disponibile"));
        double iva = Double.parseDouble(request.getParameter("iva"));

        // Recupera l'immagine come InputStream
        InputStream immagine = request.getPart("immagine").getInputStream();
        
        boolean success = ManagerArticoloDao.addArticolo(tipologia, nome, descrizione, prezzo, quantita, iva, immagine);
        if (success) {
        	
        	String param = "Caricamento articolo avvenuto con successo.";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("panelAdmin.jsp?message="+encodedParam); 
        } else {
        	
        	String param = "Errore durante l'aggiunta dell'articolo.";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
        }
    }

    private void rimuoviArticolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("idArticolo"));

        boolean success = ManagerArticoloDao.deleteArticolo(id);
        if (success) {
        	String param = "Rimozione articolo avvenuta con successo.";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("panelAdmin.jsp?message="+encodedParam);
        } else {
        	String param = "Errore durante la rimozione dell'articolo.";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
        }
    }
    
    
    
    

    private void modificaArticolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  {
		
    	int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
        String nome = request.getParameter("nome");
        String tipologia = request.getParameter("tipologia");
        String descrizione = request.getParameter("descrizione");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        double iva = Double.parseDouble(request.getParameter("iva"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));


        boolean success=ManagerArticoloDao.updateArticolo(idArticolo, nome, tipologia,descrizione, prezzo,iva, quantita);

        if (success) {
        	String param = "Articolo aggiornato con successo!";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("panelAdmin.jsp?message="+encodedParam);
        } else {
        	String param = "Errore durante l'aggiornamento dei dati dell'articolo.";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
        }
	}

  
    
    private void mostraArticoli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Recupera la lista degli articoli
    	
        List<ArticoloBean> articoli = ManagerArticoloDao.getListOfArticolo();
        String pagina=(String) request.getParameter("pagina");
        
        StringBuilder html = new StringBuilder();
        for (ArticoloBean articolo : articoli) {
        	
        		/* Sono presenti due div: uno per il contenuto e uno per l'immagine  */
        		html.append("<div class='articolo'>");
        		  
        		html.append("<div class='articolo-immagine'>");
        		   if (articolo.getImmagine() != null && articolo.getImmagine().length > 0) {
            	       
           	        html.append("<img src='GetPictureServlet?action=articoloPicture&id=")
           	            .append(articolo.getId())
           	            .append("' alt='Immagine dell'articolo'/>");
           	    } else {
           	        html.append("<p><em>Nessuna immagine disponibile</em></p>");
           	    }
        		html.append("</div>");
        		   
        		   
        		   
        		//blocco contenuti  
        		html.append("<div class='articolo-contenuto'>"); 
        	    html.append("<h3>").append(articolo.getNome()).append("</h3>");
        	    html.append("<p><strong>Tipologia:</strong> ").append(articolo.getTipologia()).append("</p>");
        	    html.append("<p><strong>Prezzo:</strong>&euro;").append(String.format("%.2f", articolo.getPrezzo())).append("</p>");
        	    html.append("<p><strong>Quantità:</strong> ").append(articolo.getQuantita()).append("</p>");;
        	    
        	 
        	    
        	    if("panelAdmin".equals(pagina)) {
        	    	
        	    	
        	    	//BOTTONE MODIFICA
        	    	html.append("<form action=\"modificaArticolo.jsp\" method=\"GET\">")
       	         	.append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\" />")
       	         	.append("<button type=\"submit\">Modifica Articolo</button>")
       	         	.append("</form>");
       	    	 
        	    	//BOTTONE RIMUOVI
        	    	html.append("<form action=\"ManagerArticoloServlet\" method=\"post\" onsubmit=\"return confermaRimozione();\" >")
        	        .append("<div id=\"messageContainer\"></div>")
        	        .append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\">")
        	        .append("<button type=\"submit\" name=\"action\" value=\"rimuovi\">Rimuovi Articolo</button>")
        	        .append("</form>");
        	    	
        	    	
        	    }
        	    else {
        	    	if(articolo.getQuantita()>0 ) {
        	    		html.append("<form action=\"ManagerCartServlet\" method=\"get\">")
        	    		.append("<input type=\"hidden\" name=\"action\" value=\"aggiungiAlCarrello\">")
        	    		.append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\">")
        	    		.append("<button type=\"submit\">Aggiungi al carrello</button>")
        	    		.append("</form>");       	           		
        	    }else {
        	    	 html.append("<p>Articolo non disponibile.</p>");
        	    	}
        	    }
        	    
        	    
        	    html.append("<form action=\"articolo.jsp\" method=\"GET\">")
        	    .append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\" />")
        	    .append("<button type=\"submit\">Visualizza Articolo</button>")
        	    .append("</form>");
        	    html.append("</div>");
        	    
        	    html.append("</div>");
        }
        
        if("panelAdmin".equals(pagina)) {
            html.append("<form action=\"aggiungiArticolo.jsp\" method=\"GET\">")
            .append("<button type=\"submit\">Aggiungi Articolo</button>")
            .append("</form>");
        }

        response.setContentType("text/html");
        response.getWriter().write(html.toString());
        
    }

    private void mostraArticoliPerTipologia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
       
    	String tipologia=(String) request.getParameter("tipologia");
    	String pagina=(String) request.getParameter("pagina");
    	
    	if("Tutti".equals(tipologia)) {
    		mostraArticoli(request, response);
    	}else {
    		
    		List<ArticoloBean> articoli = ManagerArticoloDao.getListOfArticoloByType(tipologia);
        
    		StringBuilder html = new StringBuilder();
            for (ArticoloBean articolo : articoli) {
            	
            	/* Sono presenti due div: uno per il contenuto e uno per l'immagine  */
        		html.append("<div class='articolo'>");
        		  
        		html.append("<div class='articolo-immagine'>");
        		   if (articolo.getImmagine() != null && articolo.getImmagine().length > 0) {
            	       
           	        html.append("<img src='GetPictureServlet?action=articoloPicture&id=")
           	            .append(articolo.getId())
           	            .append("' alt='Immagine dell'articolo'>");
           	    } else {
           	        html.append("<p><em>Nessuna immagine disponibile</em></p>");
           	    }
        		html.append("</div>");
        		   
        		   
        		   
        		//blocco contenuti  
        		html.append("<div class='articolo-contenuto'>"); 
        	    html.append("<h3>").append(articolo.getNome()).append("</h3>");
        	    html.append("<p><strong>Tipologia:</strong> ").append(articolo.getTipologia()).append("</p>");
        	    html.append("<p><strong>Prezzo:</strong>&euro;").append(String.format("%.2f", articolo.getPrezzo())).append("</p>");
        	    html.append("<p><strong>Quantità:</strong> ").append(articolo.getQuantita()).append("</p>");;
        	    
        	 
        	    
        	    if("panelAdmin".equals(pagina)) {
        	    	
        	    	//BOTTONE MODIFICA
        	    	html.append("<form action=\"modificaArticolo.jsp\" method=\"GET\">")
       	         	.append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\" />")
       	         	.append("<button type=\"submit\">Modifica Articolo</button>")
       	         	.append("</form>");
       	    	 
        	    	//BOTTONE RIMUOVI
        	    	html.append("<form action=\"ManagerArticoloServlet\" method=\"post\" onsubmit=\"return confermaRimozione();\" >")
        	        .append("<div id=\"messageContainer\"></div>")
        	        .append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\">")
        	        .append("<button type=\"submit\" name=\"action\" value=\"rimuovi\">Rimuovi Articolo</button>")
        	        .append("</form>");
        	    	
        	    }
        	    else {
        	    	if(articolo.getQuantita()>0 ) {
        	    		html.append("<form action=\"ManagerCartServlet\" method=\"get\">")
        	    		.append("<input type=\"hidden\" name=\"action\" value=\"aggiungiAlCarrello\">")
        	    		.append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\">")
        	    		.append("<button type=\"submit\">Aggiungi al carrello</button>")
        	    		.append("</form>");       	           		
        	    }else {
        	    	 html.append("<p>Articolo non disponibile.</p>");
        	    	}
        	    }
        	    
        	    
        	    html.append("<form action=\"articolo.jsp\" method=\"GET\">")
        	    .append("<input type=\"hidden\" name=\"idArticolo\" value=\"").append(articolo.getId()).append("\" />")
        	    .append("<button type=\"submit\">Visualizza Articolo</button>")
        	    .append("</form>");
        	    html.append("</div>");
        	    
        	    html.append("</div>");
            }
            
            if("panelAdmin".equals(pagina)) {
	            html.append("<form action=\"aggiungiArticolo.jsp\" method=\"GET\">")
	            .append("<button type=\"submit\">Aggiungi Articolo</button>")
	            .append("</form>");
            }
            
            response.setContentType("text/html");
            response.getWriter().write(html.toString());
    	}
    	
    }
   }



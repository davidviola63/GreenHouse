package com.unisa.control;
import java.io.IOException;
import java.io.InputStream;
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
    	String pathOrigin=request.getParameter("pathOrigin");
    	
         try {
             if ("add".equals(action)) {           	 
                 aggiungiArticolo(request, response);
             } else if ("rimuovi".equals(action)) {
                 rimuoviArticolo(request, response);
             } else if ("mostraPerTipologia".equals(action)) {
                 mostraArticoliPerTipologia(request, response,pathOrigin);
             }else if ("mostraTutti".equals(action)) {
            	 mostraArticoli(request, response, pathOrigin);
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
        	
        	request.setAttribute("message", "Caricamento articolo avvenuto con successo!");
            request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);  
        } else {
        	
            request.setAttribute("errorMessage", "Errore durante l'aggiunta dell'articolo.");
            request.getRequestDispatcher("panelAdmin.jsp").forward(request, response); 
        }
    }

    private void rimuoviArticolo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        boolean success = ManagerArticoloDao.deleteArticolo(id);
        if (success) {
            response.sendRedirect("panelAdmin.jsp?message=Articolo rimosso con successo");
        } else {
            request.setAttribute("errorMessage", "Errore durante la rimozione dell'articolo.");
            request.getRequestDispatcher("panelAdmin.jsp").forward(request, response); 
        }
    }
    
    private void mostraArticoli(HttpServletRequest request, HttpServletResponse response, String pathOrigin) throws ServletException, IOException, SQLException {
        // Recupera la lista degli articoli
    	
        List<ArticoloBean> articoli = ManagerArticoloDao.getListOfArticolo();
        
        request.setAttribute("articoli", articoli);

        // Inoltra la richiesta alla pagina JSP
        request.getRequestDispatcher(pathOrigin).forward(request, response); 
    }

    private void mostraArticoliPerTipologia(HttpServletRequest request, HttpServletResponse response,String pathOrigin) throws ServletException, IOException, SQLException {
        // Recupera la lista degli articoli
    	String tipologia=(String) request.getParameter("tipologia");
    	if("Tutti".equals(tipologia)) {
    		mostraArticoli(request, response, pathOrigin);
    	}else {
    		List<ArticoloBean> articoli = ManagerArticoloDao.getListOfArticoloByType(tipologia);

        
    		request.setAttribute("articoli", articoli);

        
    		request.getRequestDispatcher(pathOrigin).forward(request, response);
    	}
    }
}


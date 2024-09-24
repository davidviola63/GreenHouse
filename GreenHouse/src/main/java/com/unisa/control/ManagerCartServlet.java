package com.unisa.control;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unisa.dao.ManagerArticoloDao;
import com.unisa.model.ArticoloBean;


@WebServlet("/ManagerCartServlet")
public class ManagerCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
        	if ("aggiungiAlCarrello".equals(action)) {
        		addToCart(request,response);
        	}else if("rimuoviDalCarrello".equals(action)) {
        		removeFromCart(request,response);
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	doPost(request,response);
    
    }
    
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	   	   	
    	try {
    		HttpSession session = request.getSession();
    	 
    		@SuppressWarnings("unchecked")
    		List<ArticoloBean> carrello = (List<ArticoloBean>) session.getAttribute("carrello");
    		if (carrello == null) {
    			carrello = new ArrayList<>();
    			session.setAttribute("carrello", carrello);
    		}

    		int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
    		ArticoloBean articolo = ManagerArticoloDao.getArticolo(idArticolo);
    		if(carrello.add(articolo)) {
   			 String param = "Articolo aggiunto al carrello.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("catalogo.jsp?message="+encodedParam);
    		}else {
   			 String param = "Si è verificato un errore durante l'elaborazione della richiesta.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("catalogo.jsp?errorMessage="+encodedParam);
    		}
    		
    		
    	}
    	catch (Exception e) {
	        e.printStackTrace();
			 String param = "Errore durante la registrazione.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("catalogo.jsp?errorMessage="+encodedParam);
	    }
    	
    }
    
    
    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	boolean removed=false;
    	
    	try {
    		HttpSession sessione = request.getSession(false);
    	 
    		@SuppressWarnings("unchecked")
    		List<ArticoloBean> carrello = (List<ArticoloBean>) sessione.getAttribute("carrello");
    		
    		if (carrello == null) {
   			 String param = "Errore durante la creazione del carrello.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("carrello.jsp?errorMessage="+encodedParam);
    		}

    		int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
    		
    		for (ArticoloBean a : carrello){
    			
    			if(a.getId()==idArticolo) {
    				
    				carrello.remove(a);
    				removed=true;
    				break;
    			}
    		}
    		
    		if(removed) {
    			String param ="Articolo rimosso dal carrello";
    			sessione.setAttribute("carrello", carrello );   	    	    	
    	    	String encodedParam= URLEncoder.encode(param, "UTF-8");
    	    	response.sendRedirect("carrello.jsp?message="+encodedParam);
    		}else {
    			
    			 String param = "Errore nella rimozione dell'articolo dal carrello.Errore.";
                 String encodedParam = URLEncoder.encode(param, "UTF-8");
                 response.sendRedirect("carrello.jsp?errorMessage="+encodedParam);
    		}
    		
    		
    	}
    	catch (Exception e) {
			 String param = "Exception rilevata.Errore.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("error.jsp?errorMessage="+encodedParam);
	    }
    	
    }
}
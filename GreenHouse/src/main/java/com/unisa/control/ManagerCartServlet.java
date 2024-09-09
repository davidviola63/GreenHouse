package com.unisa.control;

import java.io.IOException;
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
        String pathOrigin= request.getParameter("pathOrigin");
        try {
        	if ("aggiungiAlCarrello".equals(action)) {
        		addToCart(request,response,pathOrigin);
        	}else if("rimuoviDalCarrello".equals(action)) {
        		removeFromCart(request,response,pathOrigin);
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	doPost(request,response);
    
    }
    
    private void addToCart(HttpServletRequest request, HttpServletResponse response,String pathOrigin) throws ServletException, IOException{
    	
    	String message, errorMessage;
    	
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
    			message="Articolo aggiunto al carrello";
    	    	request.setAttribute("message", message);
    		    request.getRequestDispatcher(pathOrigin).forward(request, response);
    		}else {
    			errorMessage="Si è verificato un errore durante l'elaborazione della richiesta.";
    			request.setAttribute("errorMessage", errorMessage);
    		    request.getRequestDispatcher(pathOrigin).forward(request, response);
    		}
    		
    		
    	}
    	catch (Exception e) {
	        e.printStackTrace();
	        errorMessage = "Si è verificato un errore durante l'elaborazione della richiesta.";
	        request.getRequestDispatcher("error.jsp").forward(request, response);
	    }
    	
    }
    
    
    private void removeFromCart(HttpServletRequest request, HttpServletResponse response,String pathOrigin) throws ServletException, IOException{
    	
    	String message, errorMessage;
    	boolean removed=false;
    	
    	try {
    		HttpSession sessione = request.getSession(false);
    	 
    		@SuppressWarnings("unchecked")
    		List<ArticoloBean> carrello = (List<ArticoloBean>) sessione.getAttribute("carrello");
    		
    		if (carrello == null) {
    			errorMessage="Si è verificato un errore durante l'elaborazione della richiesta.";
    			request.setAttribute("errorMessage", errorMessage);
    		    request.getRequestDispatcher(pathOrigin).forward(request, response);
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
    			message="Articolo rimosso dal carrello";
    			sessione.setAttribute("carrello", carrello );
    	    	request.setAttribute("message", message);
    		    request.getRequestDispatcher(pathOrigin).forward(request, response);
    		}else {
    			
    			errorMessage="Si è verificato un errore durante l'elaborazione della richiesta.";
    			request.setAttribute("errorMessage", errorMessage);
    		    request.getRequestDispatcher(pathOrigin).forward(request, response);
    		}
    		
    		
    	}
    	catch (Exception e) {
	        e.printStackTrace();
	        errorMessage = "Si è verificato un errore durante l'elaborazione della richiesta.";
	        request.getRequestDispatcher("error.jsp").forward(request, response);
	    }
    	
    }
}
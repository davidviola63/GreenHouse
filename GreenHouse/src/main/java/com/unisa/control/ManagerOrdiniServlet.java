package com.unisa.control;

import java.io.IOException;
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
            	}else if("modificaStatoOrdine".equals(action));
            		modificaStatoOrdine(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Errore durante il recupero degli ordini.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    
    private void modificaStatoOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
  
    	int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
        String nuovoStato = request.getParameter("statoOrdine");

        boolean isUpdated = ManagerOrdiniDao.updateStatoOrdine(idOrdine, nuovoStato);
            
        if (isUpdated) {
                request.setAttribute("message", "Stato ordine aggiornato con successo.");
        } else {
                request.setAttribute("errorMessage", "Errore durante l'aggiornamento dello stato dell'ordine.");
        }
        request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
	}

	private void mostraOrdini(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
    	List<OrdineBean> ordiniList = ManagerOrdiniDao.getListOfOrdini();        
    	
		request.setAttribute("ordini", ordiniList);               
		
		request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
		
    }
    
    private void mostraComponentiOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	
	 int id = Integer.parseInt(request.getParameter("id"));
     
	 OrdineBean ordine = ManagerOrdiniDao.getOrdine(id);
     List<ComponeBean> componenti = ManagerOrdiniDao.getComponentsOfOrdine(id);

     request.setAttribute("ordine", ordine);
     request.setAttribute("componenti", componenti);

     request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
         
 	}
    
    
    
}

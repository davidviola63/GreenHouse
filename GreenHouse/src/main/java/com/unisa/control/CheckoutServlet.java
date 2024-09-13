package com.unisa.control;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unisa.dao.ManagerOrdiniDao;
import com.unisa.dao.ManagerUserDao;
import com.unisa.model.*;;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * verificare che l'utente abbia inserito la sua carta correttamente
		 * 
		 *		se è corretta deve creare un ordine nel db 
		 *			-- salva l'ordine con ManagerOrdineDao.addOrdine
		 *		salvare i collegamenti tra articoli e ordine nella tabella compone
		 * 			--preleva l'id dell'ordine appena creato
		 * 			--preleva la lista di articoli nel carrello
		 * 			--li passa alla funzione ManagerOrdineDao.addListComponentOrdine
		 * 		ridurre le quantità degli articoli nel db
		 * 			--per ogni articolo usa ManagerArticoloServlet.reduceQuantityOfArticolo
		 * 			--se la funzione restituisce false allora dico all'utente che la quantità di quell'articolo non è disponibile
		 * 		rimuovere gli articoli dal carrello
		 * 			--cancella l'attributo carrello dalla sessione corrente
		 * 
		 * se è sbagliata la carta reindirizza l'utente sulla stessa pagina chiedendogli di inserire la carta correttamente
		 */
		
		MetodoPagamentoBean mp=new MetodoPagamentoBean();
        mp.setNumeroCarta(request.getParameter("cartaCredito"));
        mp.setEmail(request.getParameter("email"));
        mp.setData(LocalDate.parse(request.getParameter("dataScadenza")));
        mp.setCircuito(request.getParameter("circuito"));
        mp.setCvc(request.getParameter("cvc"));
		
		try {
			HttpSession sessione = request.getSession();
			boolean checkoutExecuted=false;
			
			if(ManagerUserDao.isUserCardValid(mp.getNumeroCarta(),mp.getEmail(),mp.getData(),mp.getCircuito(),mp.getCvc())) {
				
				OrdineBean ordine=new OrdineBean();
				ordine.setEmailUtente(request.getParameter("email"));
				ordine.setDataAcquisto(LocalDate.now());
						
				int idBonus=Integer.parseInt(request.getParameter("bonus"));
				
				
				System.out.println(idBonus +"checkoutServlet");
				@SuppressWarnings("unchecked")
	    		List<ArticoloBean> carrello = (List<ArticoloBean>) sessione.getAttribute("carrello");
				
				checkoutExecuted=ManagerOrdiniDao.addOrdine(ordine, carrello,idBonus);
				
				if(checkoutExecuted) {
					
		    		if (carrello != null) {
		    			carrello = new ArrayList<>();
		    			sessione.setAttribute("carrello", carrello);
		    		}
					 String param = "Checkout eseguito con successo.";
	                 String encodedParam = URLEncoder.encode(param, "UTF-8");
	                response.sendRedirect("carrello.jsp?message="+encodedParam);
				}else {
					 String param = "Errore durante la procedura di checkout. Verifica la disponibilità degli articoli!";
	                 String encodedParam = URLEncoder.encode(param, "UTF-8");
	                response.sendRedirect("carrello.jsp?message="+encodedParam);
				}
				
				
			}else {
				
				 String param = "Inserisci correttamente i dati della carta.";
                 String encodedParam = URLEncoder.encode(param, "UTF-8");
                response.sendRedirect("carrello.jsp?message="+encodedParam);
			}
			
			
			
		} catch (SQLException e) {
			
			 e.printStackTrace();
			 String param = e.getMessage();
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("error.jsp?message="+encodedParam);
		}		
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	
}

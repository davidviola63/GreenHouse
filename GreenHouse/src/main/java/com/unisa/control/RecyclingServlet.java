package com.unisa.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.unisa.dao.RecyclingDao;
import com.unisa.model.MobileRiciclatoBean;
import com.unisa.model.UtenteBean;


@MultipartConfig(maxFileSize = 16177215) // Dimensione massima 16MB
@WebServlet("/RecyclingServlet")
public class RecyclingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		String action = request.getParameter("action");

	    if ("aggiungi".equals(action)) {
	    	
	        addMobileRiciclato(request, response);
	        
	    } else if ("bonus".equals(action)) {
	        
	    	setBonusAndDeleteMobileRiciclato(request, response);
	    	
	    }else if("getMobiliRiciclati".equals(action)) {
	    	
	    	getMobiliriciclati(request, response);
	    	
		}else if("aggiungiBonus".equals(action)) {
			addBonus(request,response);
		}	
	    else {
	    	
	        // Gestisci il caso in cui l'azione non è riconosciuta
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Azione non valida");
	    }
	}
	
	private void addMobileRiciclato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean user=null;
		
		HttpSession session=request.getSession();
    	user= (UtenteBean) session.getAttribute("User");
		
	    String emailUtente=user.getEmail();
	    String tipoMobile=request.getParameter("tipo");
	    String commento=request.getParameter("commento");
	    
	    Part immagine = request.getPart("immagine");
        InputStream inputStreamImmagine = null;
        
        if(immagine==null) {
        	request.setAttribute("errorMessage", "Errore durante il caricamento.Formato immagine non accetato.");
            request.getRequestDispatcher("riciclo.jsp").forward(request, response);
        }else{       	
            inputStreamImmagine = immagine.getInputStream();		
		try {				
			if(RecyclingDao.isPossibleToSubmitRequest(user.getEmail())){
				boolean recycled=false;
				
				recycled=RecyclingDao.registerMobileRiciclato(emailUtente, inputStreamImmagine , tipoMobile, commento);
				if(recycled) {					
                    response.sendRedirect("riciclo.jsp?message=Caricamento avvenuto con successo");
                } else {              	
                	request.setAttribute("errorMessage", "Errore durante il caricamento");
                    request.getRequestDispatcher("riciclo.jsp").forward(request, response);
                }							
			}
			else {
				response.sendRedirect("riciclo.jsp?message=Riciclo già avvenuto. Bonus ottenuto o richiesta in sospeso.");				
			}						
		} catch (Exception e) {
						
			e.printStackTrace();		
			}		
        }
	}

	private void setBonusAndDeleteMobileRiciclato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String emailUtente = request.getParameter("emailUtente");
	    int id = Integer.parseInt(request.getParameter("id"));
	    int bonus = Integer.parseInt(request.getParameter("bonus"));

	    String message;

	    try {
	        // Assegna il bonus all'utente
	        boolean bonusAssegnato = RecyclingDao.setBonusForUser(emailUtente, bonus);

	        if (bonusAssegnato) {
	            // Rimuovi il mobile riciclato
	            boolean mobileRimosso = RecyclingDao.deleteMobileRiciclato(id);

	            if (mobileRimosso) {
	                message = "Il mobile è stato rimosso e il bonus è stato assegnato con successo!";
	            } else {
	                message = "Errore nella rimozione del mobile. Nessun cambiamento effettuato.";
	            }
	        } else {
	            message = "Errore nell'assegnazione del bonus.";
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        message = "Si è verificato un errore durante l'elaborazione della richiesta.";
	    }

	    request.setAttribute("message", message);
	    request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
	    
	}
	
	private void getMobiliriciclati (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  List<MobileRiciclatoBean> mobiliRiciclati = null;

		    try {
		        // Chiama il metodo del DAO per ottenere la lista dei mobili riciclati
		        mobiliRiciclati = RecyclingDao.getListMobiliRiciclati();
		        
		        // Aggiungi la lista alla richiesta
		        request.setAttribute("mobiliRiciclati", mobiliRiciclati);

		    } catch (Exception e) {
		        e.printStackTrace();
		        // Gestisci eventuali errori e imposta un messaggio di errore nella richiesta
		        request.setAttribute("errorMessage", "Si è verificato un errore durante il recupero dei mobili riciclati.");
		    }

		    request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
	}
	
	private void addBonus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message;
		
		try {
			if(RecyclingDao.addBonus(Integer.parseInt(request.getParameter("percentuale")),request.getParameter("descrizione"))) {
				 message = "Il bonus è stato aggiunto con successo!";
				 request.setAttribute("message", message);
				 request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "Errore durante il caricamento");
                request.getRequestDispatcher("riciclo.jsp").forward(request, response);
			}
			
			
			
		}catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Si è verificato un errore durante il recupero dei mobili riciclati.");
	    }
	}
}

package com.unisa.control;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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
        	 String param = "Errore. Formato immagine non valido.";
             String encodedParam = URLEncoder.encode(param, "UTF-8");
             response.sendRedirect("riciclo.jsp?errorMessage="+encodedParam);
        }else{       	
            inputStreamImmagine = immagine.getInputStream();		
		try {				
			if(RecyclingDao.isPossibleToSubmitRequest(user.getEmail())){
				boolean recycled=false;
				
				recycled=RecyclingDao.registerMobileRiciclato(emailUtente, inputStreamImmagine , tipoMobile, commento);
				if(recycled) {		
					String param = "Caricamento avvenuto con successo";
   	                String encodedParam = URLEncoder.encode(param, "UTF-8");
                    response.sendRedirect("riciclo.jsp?message="+encodedParam);
                } else {              	
                	 String param = "Errore durante il caricamento del mobile riciclato.";
    	             String encodedParam = URLEncoder.encode(param, "UTF-8");
    	             response.sendRedirect("riciclo.jsp?errorMessage="+encodedParam);
                }							
			}
			else {
				 String param = "Riciclo già avvenuto.Richiesta in sospeso o bonus ottenuto!";
	             String encodedParam = URLEncoder.encode(param, "UTF-8");
	             response.sendRedirect("riciclo.jsp?errorMessage="+encodedParam);				
			}						
		} catch (Exception e) {
						
			e.printStackTrace();		
			}		
        }
	}

	private void setBonusAndDeleteMobileRiciclato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String emailUtente = request.getParameter("emailUtente");
	    int idMobile = Integer.parseInt(request.getParameter("idMobile"));
	    int bonus = Integer.parseInt(request.getParameter("bonus"));
	    String param="Exception rilevata.Errore";

	    try {
	        // Assegna il bonus all'utente
	        boolean bonusAssegnato = RecyclingDao.setBonusForUser(emailUtente, bonus);

	        if (bonusAssegnato) {
	            // Rimuovi il mobile riciclato
	            boolean mobileRimosso = RecyclingDao.deleteMobileRiciclato(idMobile);

	            if (mobileRimosso) {
	                param = "Il mobile è stato rimosso e il bonus è stato assegnato con successo!";
	                String encodedParam = URLEncoder.encode(param, "UTF-8");
	                response.sendRedirect("panelAdmin.jsp?message="+encodedParam);
	                
	            } else {
	                param = "Errore nella rimozione del mobile. Nessun cambiamento effettuato.";
	                String encodedParam = URLEncoder.encode(param, "UTF-8");
	                response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
	            }
	        } else {
	            param = "Errore nell'assegnazione del bonus.";
	            String encodedParam = URLEncoder.encode(param, "UTF-8");
	            response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        param = "Errore nell'assegnazione del bonus Controllare il valore!";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
	    }

	
	    
	}
	
	private void getMobiliriciclati (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		List<MobileRiciclatoBean> mobiliRiciclati = null;
			try {
		    	 mobiliRiciclati = RecyclingDao.getListMobiliRiciclati();
		    	 StringBuilder html = new StringBuilder();
		    	 
		    	 if(mobiliRiciclati.isEmpty()) {
		    		 
		    		 html.append("<div id='mobiliRiciclati'>");
		    		 html.append("<p><em>Nessun mobile riciclato disponibile</em></p>");
		    		 html.append("</div>");
		    		 
			            response.setContentType("text/html");
			            response.getWriter().write(html.toString());
		    	 }else {
		        

		    		 html.append("<div id='mobiliRiciclati'>");
		        
		    		 for (MobileRiciclatoBean mobile : mobiliRiciclati) {
		    			 html.append("<div class='mobile-item'>")
		    			 .append("<h3>Tipo: ").append(mobile.getTipoMobile()).append("</h3>")
		                 .append("<p>Email Utente: ").append(mobile.getEmailUtente()).append("</p>")
		                 .append("<p>Commento: ").append(mobile.getCommento()).append("</p>");
		            
		         	            
		    			 if (mobile.getImmagine() != null && mobile.getImmagine().length > 0) {
		        	       
		            		html.append("<img src='GetPictureServlet?action=mobileRiciclatoPicture&id=")
	        	        	.append(mobile.getId())
	        	            	.append("' alt='Immagine del mobile' style='width:200px; height:auto;'/>");
		            	} else {
		            		html.append("<p><em>Nessuna immagine disponibile</em></p>");
	        	    	}
		    			 
		    			 html.append("<form action=\"RecyclingServlet\" method=\"post\">")
		    		      .append("<input type=\"hidden\" name=\"action\" value=\"bonus\"/>")
		    		      .append("<input type=\"hidden\" name=\"idMobile\" value=\"").append(mobile.getId()).append("\">")
		    		      .append("<input type=\"hidden\" name=\"emailUtente\" value=\"").append(mobile.getEmailUtente()).append("\">")
		    		      .append("<input type=\"number\" name=\"bonus\" step=\"1\" required> ")		    		      
		    		      .append("<input type=\"submit\" value=\"Submit Bonus\"/>")
		    		      .append("</form>");
		            
		            	html.append("</div>");
		    		 }
		        
		        html.append("</div>");

	            response.setContentType("text/html");
	            response.getWriter().write(html.toString());
	            
		    	 }
		    	 
			}catch(Exception e) {
				e.printStackTrace();
		        String param = "Exception rilevata!";
	            String encodedParam = URLEncoder.encode(param, "UTF-8");
	            response.sendRedirect("error.jsp?errorMessage="+encodedParam);
			}
			
	}
	
	private void addBonus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			if(RecyclingDao.addBonus(Integer.parseInt(request.getParameter("percentuale")),request.getParameter("descrizione"))) {
				 String param = "Bonus aggiunto con successo.";
	             String encodedParam = URLEncoder.encode(param, "UTF-8");
	             response.sendRedirect("panelAdmin.jsp?message="+encodedParam);
			}
			else {
				 String param = "Errore durante l'aggiunta del bonus.";
	             String encodedParam = URLEncoder.encode(param, "UTF-8");
	             response.sendRedirect("panelAdmin.jsp?errorMessage="+encodedParam);
			}
			
			
			
		}catch (Exception e) {
	        e.printStackTrace();
	        String param = "Exception rilevata!";
            String encodedParam = URLEncoder.encode(param, "UTF-8");
            response.sendRedirect("error.jsp?errorMessage="+encodedParam);
	    }
	}
}

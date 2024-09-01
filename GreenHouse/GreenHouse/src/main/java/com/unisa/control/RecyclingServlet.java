package com.unisa.control;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.unisa.dao.RecyclingDao;
import com.unisa.model.UtenteBean;


@MultipartConfig(maxFileSize = 16177215) // Dimensione massima 16MB
@WebServlet("/RecyclingServlet")
public class RecyclingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("flag1");
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
}

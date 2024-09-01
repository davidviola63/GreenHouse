package com.unisa.control;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unisa.dao.RecyclingDao;
import com.unisa.model.MobileRiciclatoBean;

@WebServlet("/MostraImmagineMobile")
public class GetImageById extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);
        MobileRiciclatoBean mobile = null;
        
        
        try {
        	
            mobile = RecyclingDao.getMobileRiciclato(id);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero dell'immagine");
            return;
        }

        if (mobile != null) {
        	
            Blob immagineBlob = mobile.getImmagine();
            
            try {
            	           	
                byte[] immagineBytes = immagineBlob.getBytes(1, (int) immagineBlob.length());
                response.setContentType("image/jpeg");

                OutputStream out = response.getOutputStream();
                out.write(immagineBytes);
                out.flush();
                               
                
            } catch (SQLException e) {
            	
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante la lettura dell'immagine");
            }
        } else {
            // Se il mobile non è stato trovato, invia un errore 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Immagine non trovata");
        }
    }
}
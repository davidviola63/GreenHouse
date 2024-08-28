package com.unisa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Richiede la sessione corrente dalla richiesta
        HttpSession session = request.getSession(false); // `false` non crea una nuova sessione se non esiste
        
        if (session != null) {
            // Invalida la sessione
            session.invalidate();
        }

        // Reindirizza l'utente alla pagina di login (o a qualsiasi altra pagina)
        response.sendRedirect("greenHouseHome.jsp");
    }
}
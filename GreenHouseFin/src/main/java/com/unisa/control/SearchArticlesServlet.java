package com.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unisa.dao.ManagerArticoloDao;
import com.unisa.model.ArticoloBean;



@WebServlet("/SearchArticlesServlet")
public class SearchArticlesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String query = request.getParameter("query");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
	        if (query != null && !query.trim().isEmpty()) {
	          try {
	        	  List<ArticoloBean> risultati = ManagerArticoloDao.suggestArticoli(query);  
	        	  
	        	  for (ArticoloBean articolo : risultati) {
	                  out.println("<div class='suggestion-item' onclick='selezionaArticolo(" + articolo.getId() + ")'>" 
	                              + articolo.getNome() + "</div>");
	                  
	              }
	        	  
	          }catch(SQLException e) {
	        	  e.printStackTrace();
	          }
    
        }
    }
}

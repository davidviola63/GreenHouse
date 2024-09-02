package com.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unisa.dao.GetPictureDao;

@WebServlet("/GetPictureServlet")
public class GetPictureServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static boolean isDataSource = true;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("id") != null) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			byte[] bt = null;
			
			try {
				
				bt = GetPictureDao.loadImmagineArticolo(id);
		
			
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
			ServletOutputStream out = response.getOutputStream();
			
			if(bt != null) {
				
				System.out.println("Immagine:");
				System.out.println(bt);
				System.out.println("----------------------");
				
				out.write(bt);
				response.setContentType("image/*");
				
			}
			out.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
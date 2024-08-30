package com.unisa.control;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unisa.dbconnection.DatabaseUtil;

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        List<String> userNames = new ArrayList<>();

        try (Connection con = DatabaseUtil.getConnection()) {
        	
            String query = "SELECT Nome FROM utente";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userNames.add(rs.getString("Nome"));
            }

            // Aggiungi la lista alla request
            request.setAttribute("userNames", userNames);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Inoltra la richiesta alla pagina JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("presentazione.jsp");
        dispatcher.forward(request, response);
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.unisa.model.ArticoloBean"%>
<%@ page import="com.unisa.dao.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Scheda Articolo</title>
</head>
<body>
	
	<%@include file="header.jsp" %>
	
    <%
        int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
        ArticoloBean articolo = ManagerArticoloDao.getArticolo(idArticolo);
        
        if (articolo != null) {
    %>
        <h1><%= articolo.getNome() %></h1>
        <p><strong>Tipologia:</strong> <%= articolo.getTipologia() %></p>
        <p><strong>Descrizione:</strong> <%= articolo.getDescrizione() %></p>
        <p><strong>Prezzo:</strong> €<%= articolo.getPrezzo() %></p>
        <p><strong>IVA:</strong> <%= articolo.getIva() %>%</p>
        <p><strong>Quantità Disponibile:</strong> <%= articolo.getQuantita() %></p>
        
        <img src="GetPictureServlet?id=<%= articolo.getId() %>" alt="Immagine del prodotto">

        <form action="ManagerCartServlet" method="post">
        	<input type="hidden" name="pathOrigin" value="articolo.jsp"> 
            <input type="hidden" name="action" value="aggiungiAlCarrello">
            <input type="hidden" name="idArticolo" value="<%= articolo.getId() %>">
            <button type="submit">Aggiungi al Carrello</button>
        </form>

    <%
        } else {
    %>
        <p>Articolo non trovato.</p>
    <%
        }
    %>
    
    <%@include file="footer.jsp" %>

</body>
</html>
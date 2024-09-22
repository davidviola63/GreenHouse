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
	
	<div class="articolo-singolo">
	
    <%
        int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
        ArticoloBean articolo = ManagerArticoloDao.getArticolo(idArticolo);
        
        if (articolo != null) {
    %>
    	
    	 <div class="articolo-singolo-immagine">
        	<img src="GetPictureServlet?action=articoloPicture&id=<%= articolo.getId() %>" alt="Immagine del prodotto">
		</div>
    	
    	<div class="articolo-singolo-contenuto">
        	<h1><%= articolo.getNome() %></h1>
        	<p><strong>Tipologia:</strong> <%= articolo.getTipologia() %></p>
        	<p><strong>Descrizione:</strong> <%= articolo.getDescrizione() %></p>
        	<p><strong>Prezzo:</strong> €<%= articolo.getPrezzo() %></p>
        	<p><strong>IVA:</strong> <%= articolo.getIva() %>%</p>
       	 	<p><strong>Quantità Disponibile:</strong> <%= articolo.getQuantita() %></p>
       	 	
       	 		<%if(articolo.getQuantita()>0){ %>
        <form action="ManagerCartServlet" method="post">
        	<input type="hidden" name="pathOrigin" value="articolo.jsp"> 
            <input type="hidden" name="action" value="aggiungiAlCarrello">
            <input type="hidden" name="idArticolo" value="<%= articolo.getId() %>">
            <button type="submit">Aggiungi al Carrello</button>
        </form>
	<%}else{ %>
	<p><strong>ARTICOLO NON DISPONIBILE</strong></p>
	<%} %>
    <%
        } else {
    %>
        <p>Articolo non trovato.</p>
    <%
        }
    %>
       	 	
       	 	
        </div>
            
    </div>
    
    <%@include file="footer.jsp" %>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.unisa.dao.ManagerArticoloDao"%>
 <%@ page import="com.unisa.model.ArticoloBean"%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica Articolo</title>
</head>
<body>

<%@ include file="header.jsp" %>

<%

    int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
    
    ArticoloBean articolo = ManagerArticoloDao.getArticolo(idArticolo);
    
%>


<div class="classForm">

		<h1 align="center">Modifica Articolo</h1>
		
		<form action="ManagerArticoloServlet" method="POST" onsubmit="return validateAggiungiArticoloForm()">
			
			<input type="hidden" name="action" value="modifica">
		    <input type="hidden" name="idArticolo" value="<%= articolo.getId() %>">
		    
		    <label for="nome">Nome:</label>
		    <input type="text" id="nome" name="nome" value="<%= articolo.getNome() %>" required>
		    
		    <label for="tipologia">Tipologia:</label>
		    <input type="text" id="tipologia" name="tipologia" value="<%= articolo.getTipologia() %>" required>
		    
		    <label for="descrizione">Tipologia:</label>
		    <input type="text" id="descrizione" name="descrizione" value="<%= articolo.getDescrizione() %>" required>
		    
		    <label for="prezzo">Prezzo:</label>
		    <input type="number" id="prezzo" name="prezzo" value="<%= articolo.getPrezzo() %>" step="0.01" required>
		    
		    <label for="iva">Iva:</label>
		    <input type="number" id="iva" name="iva" value="<%= articolo.getIva() %>" required>
		        
		    <label for="quantita">Quantità:</label>
		    <input type="number" id="quantita" name="quantita" value="<%= articolo.getQuantita() %>" required>
		      
		    
		    <button type="submit">Salva Modifiche</button>
		</form>

</div>

<%@include file="footer.jsp" %>

<script type="text/javascript" src="./script/validateAggiungiArticoloForm.js"></script>

</body>
</html>
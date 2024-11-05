
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.unisa.dao.ManagerArticoloDao"%>
 <%@ page import="com.unisa.model.ArticoloBean"%>
 <%@ page import="java.net.URLEncoder" %>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica Articolo</title>
<style type="text/css">
		.form-group {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    gap: 10px; 
		}
		
		label {
		    flex: 0 0 auto; 
		    position: relative; 
		}
		
</style>


</head>
<body>

<%@ include file="header.jsp" %>

<%

if(user.getRuolo().equalsIgnoreCase("cliente")){
	 String param = "Sessione scaduta.";
    String encodedParam = URLEncoder.encode(param, "UTF-8");
    response.sendRedirect("greenHouseHome.jsp?errorMessage="+encodedParam);
}

    int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
    
    ArticoloBean articolo = ManagerArticoloDao.getArticolo(idArticolo);
    
%>


<div class="container_content">

		<h1 align="center">Modifica Articolo</h1>
		
		<form class="loginForm" action="ManagerArticoloServlet" method="POST" onsubmit="return validateAggiungiArticoloForm()">
			
			<input type="hidden" name="action" value="modifica">
		    <input type="hidden" name="idArticolo" value="<%= articolo.getId() %>">
		    
		    <label for="nome">Nome:</label>
		    <input type="text" id="nome" name="nome" value="<%= articolo.getNome() %>" required><br>
		    
		    <label for="tipologia">Tipologia:</label>
		    <input type="text" id="tipologia" name="tipologia" value="<%= articolo.getTipologia() %>" required><br>
		    
		    <div class="form-group">
		    	<label for="descrizione">Descrizione:</label>
		    	<textarea id="descrizione" name="descrizione" maxlength="250" required><%= articolo.getDescrizione() %></textarea><br>
		    </div>
		    <label for="prezzo">Prezzo:</label>
		    <input type="number" id="prezzo" name="prezzo" value="<%= articolo.getPrezzo() %>" step="0.01" required><br>
		    
		    <label for="iva">Iva:</label>
		    <input type="number" id="iva" name="iva" value="<%= articolo.getIva() %>" required><br>
		        
		    <label for="quantita">Quantità:</label>
		    <input type="number" id="quantita" name="quantita" value="<%= articolo.getQuantita() %>" required><br>
		      
		    
		    <button type="submit">Salva Modifiche</button>
		</form>

</div>

<%@include file="footer.jsp" %>

<script type="text/javascript" src="./script/validateAggiungiArticoloForm.js"></script>

</body>
</html>
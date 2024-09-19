<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.unisa.model.*" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Riciclo Mobili</title>
</head>
<body>

<div class="container_content">

	<%@ include file="header.jsp"%>
	<%
	if(user.getNome().equalsIgnoreCase("guest")){
		 String param = "Sessione scaduta.";
         String encodedParam = URLEncoder.encode(param, "UTF-8");
         response.sendRedirect("greenHouseHome.jsp?errorMessage="+encodedParam);
	}
%>
	
	<h1>Hey <%= user.getNome() %>! Qui ci diamo una mano a vicenda. Effettua la richiesta del riciclo di un mobile per ottenere vantaggi sui tuoi futuri acquisti!</h1>
   	
   	<div id="messageContainer"></div> 
   	
   		 <h2>Modulo di Riciclo Mobili</h2>
    
    	<form action="RecyclingServlet" method="post" enctype="multipart/form-data" >
    
    	
    	  <input type="hidden" name="action" value="aggiungi">
    	
          <label for="immagine">Carica immagine del mobile:</label>
          <input  class="file" type="file" id="immagine" name="immagine" value="" maxlength="255" required><br><br>

       	  <label for="tipo">Seleziona il tipo di mobile:</label>
      	  <select id="tipo" name="tipo" required>
            <option value="tavolo">Tavolo</option>
            <option value="sedie">Sedie</option>
            <option value="divano">Divano</option>
            <option value="soggiorno">Soggiorno</option>
            <option value="mobile_generico">Mobile Generico</option>
       	  </select><br><br>
	
      	  <label for="commento">Commento (max 200 caratteri):</label><br>
      	  <textarea id="commento" name="commento" maxlength="200"></textarea><br><br>

       	 <button type="submit">Invia</button>
               
    	</form>
  
  </div>
  
  
   <%@ include file="footer.jsp" %>
  
   <script type="text/javascript" src="./script/mostraMessaggi.js"> </script>
   
</body>
</html>
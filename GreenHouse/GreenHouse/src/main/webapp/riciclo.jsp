<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.unisa.model.*" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Riciclo Mobili</title>
    <style>
    
	.container_content{
		background-color:white;
		border: medium solid green;
		width:auto;
		height:auto;
		background-image: url("./images/background_homepage.jpg");
	}
	
	</style>
</head>
<body>

<div class="container_content">


	<% 
	// Recupera la sessione corrente se esiste
	
	HttpSession sessione = request.getSession(false); 
	UtenteBean user= null;
	if(sessione == null || (user = (UtenteBean) sessione.getAttribute("User")) == null){
		response.sendRedirect("error.jsp");
		return;
			
	}
	
	%>
	
	<h1>Hey <%= user.getNome() %>! Qui ci diamo una mano a vicenda. Effettua la richiesta del riciclo di un mobile per ottenere vantaggi sui tuoi futuri acquisti!</h1>
   		
   	<%
        String message = request.getParameter("message");
        if (message != null) {
            out.println("<p>" + message + "</p>");
        }
   	%>
   
    <h2>Modulo di Riciclo Mobili</h2>
    <form action="RecyclingServlet" method="post" enctype="multipart/form-data" >
    
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
        
        <p id="errorMessage" style="color: red;">
        	<% String errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) {
               out.println(errorMessage);
       	    }
      		%>
      	</p>
      	
    </form>
    
    
  </div>
</body>
</html>
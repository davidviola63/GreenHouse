<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Catalogo</title>
    
    
</head>

<body>

<%@ include file="header.jsp" %>
	
	
    <h1 style="text-align: center">Elenco Prodotti</h1>
    
	<div id="messageContainer" class="messageContainer"></div> 
    <!-- Filtri -->
   		<label for="tipologia">Tipologia:</label>
			 <select id="tipologia" name="tipologia" required>
			 	<option value="Tutti">Tutti</option>
				<option value="tavolo">Tavolo</option>
				<option value="sedie">Sedie</option>
				<option value="divano">Divano</option>
				<option value="soggiorno">Soggiorno</option>
				<option value="mobile_generico">Mobile Generico</option>
			</select><br><br>


    <div id="catalogoContainer" class="catalogo"></div>
    
    <%@ include file="footer.jsp" %>
    
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="./script/mostraMessaggi.js"> </script>
    <script type="text/javascript" src="./script/mostraPerTipologia.js"></script>
    
</body>
</html>
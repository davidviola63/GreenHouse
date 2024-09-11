<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Catalogo</title>
    
    <style type="text/css">
		
		.catalogoContainer{
			 display: flex; 
   			 border: 1px solid #000;
    		 margin-bottom: 10px;
    		 padding: 10px;
    		 justify-content: flex-start;
    		 align-items: center; /* Allinea verticalmente al centro */
    		  gap: 20px; /* Distanza tra gli elementi */
		}
		
</style>

</head>
<body>

<%@ include file="header.jsp" %>
	
	
    <h1>Catalogo Articoli</h1>
    
	<div id="messageContainer"></div> 
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


    <div id="catalogoContainer" class="catalogoContainer"></div>
    
    <%@ include file="footer.jsp" %>
    
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="./script/mostraMessaggi.js"> </script>
    <script type="text/javascript" src="./script/mostraPerTipologia.js"></script>
    
</body>
</html>
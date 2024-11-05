<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.unisa.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unisa.dao.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>GreenHouse Panel Admin</title>
</head>


<body>

<%@ include file="header.jsp" %>

<%
	if(user.getRuolo().equalsIgnoreCase("cliente")){
		 String param = "Sessione scaduta.";
         String encodedParam = URLEncoder.encode(param, "UTF-8");
         response.sendRedirect("greenHouseHome.jsp?errorMessage="+encodedParam);
	}
%>

	<div id="messageContainer"></div>
	

    <h1>Catalogo Articoli</h1>
    
	<div id="messageContainer"></div> 
    
   		<label for="tipologiaCatalogoAdmin">Tipologia:</label>
			 <select id="tipologiaCatalogoAdmin" name="tipologiaCatalogoAdmin" required>
			 	<option value="Tutti">Tutti</option>
				<option value="tavolo">Tavolo</option>
				<option value="sedie">Sedie</option>
				<option value="divano">Divano</option>
				<option value="soggiorno">Soggiorno</option>
				<option value="mobile_generico">Mobile Generico</option>
			</select><br><br>


    <div class="catalogo" id="catalogoContainerAdmin"></div>
	
	<br><br>
    <h2>Visualizza Ordini</h2>

    <!-- Filtri -->
    <div>
        <label for="email">Email Utente:</label>
        <input type="text" id="email">
        <button onclick="getOrdiniByUserAdmin()">Cerca per Utente</button>
    </div>
    
    <br>

    <div>
        <label for="dataInizio">Data Inizio:</label>
        <input type="date" id="dataInizio">
        <label for="dataFine">Data Fine:</label>
        <input type="date" id="dataFine">
        <button onclick="getOrdiniByDateAdmin()">Cerca per Intervallo di Date</button>
    </div>
    
    <br>
    
    <button onclick="getAllOrdiniAdmin()">Mostra Tutti gli Ordini</button>

    <div id="ordiniContainer"></div>
    
    <div id="dettagliContainer"></div>
			
	
	<h1>Mobili Riciclati</h1>
	
	<div class="mobiliRiciclatiContainer" id="mobiliRiciclatiContainer"></div>


	<div>
	
		<h1> Aggiungi nuovo bonus</h1>
	
		
	<form action="RecyclingServlet" method="post">
	
		<div id="messageContainer"></div><br>
		
    	<input type="hidden" name="action" value="aggiungiBonus">
    
    	<label for="percentuale">Percentuale di Sconto (%):</label>
    	<input type="number" id="percentuale" name="percentuale" min="1" max="100" required><br><br>
    
    	<label for="descrizione">Descrizione del Bonus:</label>
   	 	<textarea id="descrizione" name="descrizione" maxlength="100" required></textarea><br><br>
    
    	<button type="submit">Aggiungi Bonus</button>
	</form>
	
	</div>


<%@include file="footer.jsp" %>

<script type="text/javascript" src="./script/validateAggiungiArticoloForm.js"></script>
<script type="text/javascript" src="./script/mostraMessaggi.js"> </script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./script/mostraPerTipologiaAdmin.js"></script>
<script type="text/javascript" src="./script/mostraOrdini.js"></script>
<script type="text/javascript" src="./script/mostraMobiliRiciclati.js"></script>

 <script>
    	window.onload = function() {
    		getMobiliRiciclati(); 
    	};
 </script>
 
 <script>
        function confermaRimozione() {
        	
            let conferma = confirm("Sei sicuro di voler rimuovere questo articolo dal catalogo?");
            
            return conferma;
        }
</script>

</body>
</html>
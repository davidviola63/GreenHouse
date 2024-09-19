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
<title>GreenHouse-Pannello di Controllo per Admin</title>
</head>


<body>

<%@ include file="header.jsp" %>

<%
	if(user.getNome().equalsIgnoreCase("guest")){
		 String param = "Sessione scaduta.";
         String encodedParam = URLEncoder.encode(param, "UTF-8");
         response.sendRedirect("greenHouseHome.jsp?errorMessage="+encodedParam);
	}
%>

	<div id="messageContainer"></div>
	
	<div class="classForm">
		<h1>Aggiungi articolo</h1>
		

		<form action="ManagerArticoloServlet" method="post" enctype="multipart/form-data" onsubmit="return validateAggiungiArticoloForm()">
			
			<div id="messageContainer"></div>
			
			<label for="tipologia">Tipo:</label>
			 <select id="tipologia"
				name="tipologia" required>
				<option value="tavolo">Tavolo</option>
				<option value="sedie">Sedie</option>
				<option value="divano">Divano</option>
				<option value="soggiorno">Soggiorno</option>
				<option value="mobile_generico">Mobile Generico</option>
			</select><br><br> 
			
			<label for="nome">Nome:</label>
			<input type="text" id="nome" name="nome" maxlength="60" required>
        	<div id ="message1nome" style="display: none;">
					<p>Inserire <b>formato corretto,solo lettere</b></p>
				</div>	
			<br><br>
			
			<label for="descrizione">Descrizione:</label>
			<textarea id="descrizione" name="descrizione" maxlength="250" required></textarea>
			<br><br>
			
			 <label for="prezzo">Prezzo:</label>
			 <input type="number" step="0.01" id="prezzo" name="prezzo" required>
        	 <div id ="message1prezzo" style="display: none;">
					<p>Inserire <b>numero positivo con massimo due cifre decimali.</b></p>
				</div>	
			 <br><br>
			 
			  <label for="quantita_disponibile">Quantità Disponibile:</label>			   
			  <input type="number" id="quantita_disponibile" name="quantita_disponibile" required>
			  <div id ="message1quantita" style="display: none;">
					<p>Inserire <b>una quantità valida (>0).</b></p>
				</div>	
			  <br><br>
			  
			 <label for="iva">IVA (%):</label> 
			 <input type="number" step="0.01" id="iva" name="iva" required>
        	 <div id ="message1iva" style="display: none;">
					<p>Inserire <b>numero positivo con massimo due cifre decimali.</b></p>
				</div>	
			<br><br> 
			
			 <label for="immagine">Immagine del prodotto:</label>
			  <input class="file" type="file" id="immagine" name="immagine" value="" maxlength="255" required><br><br>

			<button type="submit" name="action" value="add">Aggiungi Articolo</button>

		</form>

	</div>





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


    <div class="catalogoContainer" id="catalogoContainerAdmin"></div>

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

</body>
</html>
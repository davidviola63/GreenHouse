<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="header.jsp" %>

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
	
	<%@include file="footer.jsp" %>
	
	<script type="text/javascript" src="./script/validateAggiungiArticoloForm.js"></script>

</body>
</html>
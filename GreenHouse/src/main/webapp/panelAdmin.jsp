<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.unisa.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unisa.dao.*" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GreenHouse-Pannello di Controllo per Admin</title>
</head>



<body>
	<%

String errorMessage=null;
String message=null;
HttpSession sessione = request.getSession(false); 
UtenteBean user= null;
if(sessione == null || (user = (UtenteBean) sessione.getAttribute("User")) == null ){
	response.sendRedirect("error.jsp");
	return;
		
}

%>

	<p>
		Tu sei
		<%= user.getNome() %>
		ed hai il ruolo di
		<%= user.getRuolo() %>. Qui puoi gestire la pagina.
	</p>
	<form action="greenHouseHome.jsp" method="get">
		<button type="submit">Torna alla Home</button>
	</form>

	<div>
		<h1>Aggiungi articolo</h1>

		<% 
        errorMessage = (String) request.getAttribute("errorMessage");
    	message = (String) request.getAttribute("message");    
		if (errorMessage != null) { 
   		 %>
		<p style="color: red;"><%= errorMessage %></p>
		<% 
        } 
        if(message!=null){ %>
		<p style="color: green;"><%= message %></p>
		<% 
   		 }
		%>

		<form action="ManagerArticoloServlet" method="post"
			enctype="multipart/form-data">

			<label for="tipologia">Tipo:</label> <select id="tipologia"
				name="tipologia" required>
				<option value="tavolo">Tavolo</option>
				<option value="sedie">Sedie</option>
				<option value="divano">Divano</option>
				<option value="soggiorno">Soggiorno</option>
				<option value="mobile_generico">Mobile Generico</option>
			</select><br>
			<br> <label for="nome">Nome:</label> <input type="text"
				id="nome" name="nome" maxlength="60" required><br>
			<br> <label for="descrizione">Descrizione:</label>
			<textarea id="descrizione" name="descrizione" maxlength="250"
				required></textarea>
			<br>
			<br> <label for="prezzo">Prezzo:</label> <input type="number"
				step="0.01" id="prezzo" name="prezzo" required><br>
			<br> <label for="quantita_disponibile">Quantità
				Disponibile:</label> <input type="number" id="quantita_disponibile"
				name="quantita_disponibile" required><br>
			<br> <label for="iva">IVA (%):</label> <input type="number"
				step="0.01" id="iva" name="iva" required><br>
			<br> <label for="immagine">Immagine del prodotto:</label> <input
				class="file" type="file" id="immagine" name="immagine" value=""
				maxlength="255" required><br>
			<br>



			<button type="submit" name="action" value="add">Aggiungi
				Articolo</button>

		</form>

	</div>




	<div>
		<h1>Mostra gli articoli per tipologia:</h1>

		<form action="ManagerArticoloServlet" method="post">
			
			<input type="hidden" name="pathOrigin" value="panelAdmin.jsp"> 
			<label for="tipologia">Seleziona il tipo di mobile:</label>
			<select id="tipologia" name="tipologia" required>
				<option value="Tavolo">Tavolo</option>
				<option value="Sedie">Sedie</option>
				<option value="Divano">Divano</option>
				<option value="Soggiorno">Soggiorno</option>
				<option value="Mobile generico">Mobile Generico</option>
			</select><br>
			<br>
			<button type="submit" name="action" value="mostraPerTipologia">Visualizza
				Articoli</button>
		</form>


		<ul>
			<% 
       	           	     
			@SuppressWarnings("unchecked")        	 
       	     List<ArticoloBean> articoli = (List<ArticoloBean>) request.getAttribute("articoli");
	
        	    if (articoli != null) {
        	        for (ArticoloBean articolo : articoli) {
        	        	
      	  %>
			<li><strong>ID:</strong> <%= articolo.getId() %><br> <strong>Nome:</strong>
				<%= articolo.getNome() %><br> <strong>Tipologia:</strong> <%= articolo.getTipologia() %><br>
				<strong>Descrizione:</strong> <%= articolo.getDescrizione() %><br>
				<strong>Prezzo:</strong> €<%= articolo.getPrezzo() %><br> <strong>Quantità
					Disponibile:</strong> <%= articolo.getQuantita() %><br> <strong>IVA:</strong>
				<%= articolo.getIva() %>%<br> <!-- <strong>Immagine:</strong> <img src="GetPictureServlet?id=" alt="Immagine di : " onError="this.src=images/logo.jpg">  -->
				<strong>Immagine:</strong> <img
				src="GetPictureServlet?id=<%= articolo.getId() %>" width="200"
				height="200" alt="Immagine di : <%= articolo.getNome() %>"></li>
			<% 
            }
        } else {
    %>
			<li>Nessun articolo trovato.</li>
			<% 
        }
    %>
		</ul>

	</div>




	<div>
		<h1>Rimuovi articolo</h1>


		<% 
        errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { 
    %>
		<p style="color: red;"><%= errorMessage %></p>
		<% 
        } 
    %>

		<form action="ManagerArticoloServlet" method="post">

			<label for="id">ID Articolo:</label> <input type="number" id="id"
				name="id" required> <br>
			<br>

			<button type="submit" name="action" value="rimuovi">Rimuovi
				Articolo</button>
		</form>
	</div>


	<div>

		<h1>Gestisci ricicli</h1>



		<form action="RecyclingServlet" method="get">
			<button type="submit" name="action" value="getMobiliRiciclati">Visualizza
				Mobili Riciclati</button>
		</form>
		<% 
        message = (String) request.getAttribute("message");
        if (message != null) { 
 %>
		<p style="color: red;"><%= message %></p>
		<% 
        } 
 %>
		<% 
	@SuppressWarnings("unchecked")
    List<MobileRiciclatoBean> mobiliRiciclati = (List<MobileRiciclatoBean>) request.getAttribute("mobiliRiciclati");
    
    if (mobiliRiciclati != null && !mobiliRiciclati.isEmpty()) {
 %>
		<h2>Lista dei Mobili Riciclati</h2>

		<%
    	
        for (MobileRiciclatoBean mobile : mobiliRiciclati) {
%>
		<div>

			<p>
				Tipo di Mobile:
				<%= mobile.getTipoMobile() %>
			<p>
			<p>
				Email Utente:
				<%= mobile.getEmailUtente() %></p>
			<p>
				Commento:
				<%= mobile.getCommento() %></p>
			<img src="GetImageById?id=<%= mobile.getId() %>"
				alt="Immagine del Mobile Riciclato" />

			<!-- Form per rimuovere l'articolo e aggiungere un bonus -->
			<form action="RecyclingServlet" method="post">

				<input type="hidden" name="action" value="bonus"> 
				<input type="hidden" name="id" value="<%= mobile.getId() %>"> 
				<input type="hidden" name="emailUtente" value="<%= mobile.getEmailUtente() %>"> 
				<label for="bonus"> Assegna un Bonus:</label> 
				<select id="bonus" name="bonus" required>
					<option value="1">1( 0% )</option>
					<option value="2">2( 20% )</option>
					<option value="3">3( 50% )</option>
					<option value="4">4( 10% )</option>
				</select>

				<button type="submit">Rimuovi Mobile Riciclato ed Assegna
					Bonus</button>
			</form>
		</div>
		<% 
        }
    } else { 
%>
		<p>Nessun mobile riciclato trovato.</p>
		<% 
    } 
%>
	</div>



	<div id="ordiniList">

		<h1>Visualizza Ordini</h1>

		<form action="ManagerOrdiniServlet" method="post">
			<input type="hidden" name="action" value="visualizzaOrdini">
			<button type="submit">Visualizza Ordini</button>
		</form>

		<br>

    <h2>Lista degli Ordini</h2>
	
    <% 
    
    @SuppressWarnings("unchecked")
    List<OrdineBean> ordiniList = (List<OrdineBean>) request.getAttribute("ordini");
    
    if (ordiniList != null && !ordiniList.isEmpty()) { 
    	DecimalFormat df = new DecimalFormat("#.00");
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID Ordine</th>
                    <th>Data acquisto</th>
                    <th>Stato</th>
                    <th>Codice fattura</th>
                    <th>Totale (€)</th>
                </tr>
            </thead>
            <tbody>
                <% for (OrdineBean ordine : ordiniList) { %>
                    <tr>
                        <td><%= ordine.getIdOrdine() %></td>
                        <td><%= ordine.getDataAcquisto() %></td>
                        <td><%= ordine.getStato() %></td>
                        <td><%= ordine.getCodFattura() %></td>
                        <!-- Formatta il valore double per visualizzare fino alla seconda cifra decimale -->
                        <td><%= df.format(ManagerOrdiniDao.getPrezzoTotaleOrdine(ordine.getIdOrdine())) %></td>
                         
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Non ci sono ordini disponibili per questo utente.</p>
    <% } %>

	</div>







	<div>
		<h1>Visualizza composizione ordine</h1>

		<form action="ManagerOrdiniServlet" method="post">
			<input type="hidden" name="action" value="visualizzaComponentiOrdine">
			<label for="id">ID Ordine:</label> 
			<input type="number" id="id" name="id" required>
			<button type="submit">Visualizza Ordine</button>
		</form>


		<h3>Dettagli Ordine</h3>
		<%
		
    	OrdineBean ordine = (OrdineBean) request.getAttribute("ordine");
		
		@SuppressWarnings("unchecked") 
    	List<ComponeBean> componenti = (List<ComponeBean>) request.getAttribute("componenti");

   		 if (ordine != null) {
		%>
		<div>
			<p>
				ID Ordine: <%= ordine.getIdOrdine() %></p>
			<p>
				Email Utente: <%= ordine.getEmailUtente() %></p>
			<p>
				Stato: <%= ordine.getStato() %></p>
			<p>
				Data Acquisto: <%= ordine.getDataAcquisto() %></p>
			<p>
				Codice Fattura: <%= ordine.getCodFattura() %></p>

			<h4>Componenti dell'Ordine:</h4>
		<%
            if (componenti != null && !componenti.isEmpty()) {
                for (ComponeBean componente : componenti) {
                	ArticoloBean articolo = ManagerArticoloDao.getArticolo(componente.getIdArticolo());
		%>
			<div>
				<p><strong>ID Articolo:</strong> <%= articolo.getId() %></p>
                <p><strong>Nome:</strong> <%= articolo.getNome() %></p>
                <p><strong>Descrizione:</strong> <%= articolo.getDescrizione() %></p>
				<p><strong>Prezzo:</strong> <%= componente.getPrezzo() %></p>
				<p><strong>Quantità:</strong> <%= componente.getQuantita() %></p>
				<hr>
			</div>

		<%				
                }
            } else {
		%>
			<p>Nessun componente trovato per questo ordine.</p>
		<%
            }
		%>
			<hr>
		</div>
		<%
    } else {
		%>
		<p>Ordine non trovato.</p>
		<%
    }
		%>
	</div>









	<div>
		<h1>Modifica stato ordini</h1>
		
		<% 
        errorMessage = (String) request.getAttribute("errorMessage");
    	message = (String) request.getAttribute("message");    
		if (errorMessage != null) { 
   		 %>
		<p style="color: red;"><%= errorMessage %></p>
		<% 
        } 
        if(message!=null){ %>
		<p style="color: red;"><%= message %></p>
		<% 
    	}
		%>
		
		<form action="ManagerOrdiniServlet" method="post">
    
			<input type="hidden" name="action" value="modificaStatoOrdine">
		  
    		<label for="idOrdine">ID Ordine:</label>
   			<input type="number" id="idOrdine" name="idOrdine" required>
    
   			<label for="statoOrdine">Nuovo Stato:</label>
   			<select id="statoOrdine" name="statoOrdine" required>
    	   		<option value="sospeso">Sospeso</option>
        		<option value="spedito">Spedito</option>
        		<option value="consegnato">Consegnato</option>
   	 		</select>
    
   		 	<button type="submit">Modifica Stato</button>
		</form>
	</div>

	
	<div>
	
		<h1> Aggiungi bonus</h1>
		
	<% 
        errorMessage = (String) request.getAttribute("errorMessage");
        message = (String) request.getAttribute("message");    
        if (errorMessage != null) { 
    %>
        <p style="color: red;"><%= errorMessage %></p>
    <% 
        } 
        if (message != null) { 
    %>
        <p style="color: green;"><%= message %></p>
    <% 
        } 
    %>
		
	<form action="RecyclingServlet" method="post">
    	<input type="hidden" name="action" value="aggiungiBonus">
    
    	<label for="percentuale">Percentuale di Sconto (%):</label>
    	<input type="number" id="percentuale" name="percentuale" min="1" max="100" required><br><br>
    
    	<label for="descrizione">Descrizione del Bonus:</label>
   	 	<textarea id="descrizione" name="descrizione" maxlength="100" required></textarea><br><br>
    
    	<button type="submit">Aggiungi Bonus</button>
	</form>
	
	</div>

</body>
</html>
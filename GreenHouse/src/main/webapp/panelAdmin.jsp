<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.unisa.model.*" %>
<%@ page import="java.util.List" %>

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

<p>Tu sei <%= user.getNome() %> ed hai il ruolo di <%= user.getRuolo() %>. Qui puoi gestire la pagina.</p>
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
        <p style="color:red;"><%= errorMessage %></p>
    <% 
        } 
        if(message!=null){ %>
           <p style="color:red;"><%= message %></p>
     	<% 
    }
		%>
		
	<form action="ManagerArticoloServlet" method="post" enctype="multipart/form-data">
                
        <label for="tipo">Tipo:</label>
        <select id="tipo" name="tipo" required>
            <option value="tavolo">Tavolo</option>
            <option value="sedie">Sedie</option>
            <option value="divano">Divano</option>
            <option value="soggiorno">Soggiorno</option>
            <option value="mobile_generico">Mobile Generico</option>
        </select><br><br>	
        
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" maxlength="60" required><br><br>

        <label for="descrizione">Descrizione:</label>
        <textarea id="descrizione" name="descrizione" maxlength="250" required></textarea><br><br>

        <label for="prezzo">Prezzo:</label>
        <input type="number" step="0.01" id="prezzo" name="prezzo" required><br><br>

        <label for="quantita_disponibile">Quantità Disponibile:</label>
        <input type="number" id="quantita_disponibile" name="quantita_disponibile" required><br><br>

        <label for="iva">IVA (%):</label>
        <input type="number" step="0.01" id="iva" name="iva" required><br><br>

        <label for="immagine">Immagine del prodotto:</label>
        <input  class="file" type="file" id="immagine" name="immagine" value="" maxlength="255" required><br><br>
		
		
		
        <button type="submit" name="action" value="add">Aggiungi Articolo</button>
	
	</form>
	
</div>




<div>
		<h1>Mostra tutti gli articoli</h1>
		
	<form action="ManagerArticoloServlet" method="post">

		<label for="tipo">Seleziona il tipo di mobile:</label>
        <select id="tipo" name="tipo" required>
            <option value="tavolo">Tavolo</option>
            <option value="sedie">Sedie</option>
            <option value="divano">Divano</option>
            <option value="soggiorno">Soggiorno</option>
            <option value="mobile_generico">Mobile Generico</option>
        </select><br><br>	
        <button type="submit" name="action" value="mostra">Visualizza Articoli</button>
    </form>
    
    
		<ul>
       	 <% 
       	           	     
			@SuppressWarnings("unchecked")        	 
       	     List<ArticoloBean> articoli = (List<ArticoloBean>) request.getAttribute("articoli");
	
        	    if (articoli != null) {
        	        for (ArticoloBean articolo : articoli) {
      	  %>
       	         <li>
       	         	<strong>ID:</strong> <%= articolo.getId() %><br>
                    <strong>Nome:</strong> <%= articolo.getNome() %><br>
                    <strong>Tipologia:</strong> <%= articolo.getTipologia() %><br>
                    <strong>Descrizione:</strong> <%= articolo.getDescrizione() %><br>
                    <strong>Prezzo:</strong> €<%= articolo.getPrezzo() %><br>
                    <strong>Quantità Disponibile:</strong> <%= articolo.getQuantita() %><br>
                    <strong>IVA:</strong> <%= articolo.getIva() %>%<br>
                </li>
    <% 
            }
        } else {
    %>
        <li>Nessun articolo trovato.</li>
    <% 
        }
    %>    	</ul>

</div>




<div>
	<h1>Rimuovi articolo</h1>
	
	
	<% 
        errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { 
    %>
        <p style="color:red;"><%= errorMessage %></p>
    <% 
        } 
    %>
	
	<form action="ManagerArticoloServlet" method="post">
		
        <label for="id">ID Articolo:</label>
        <input type="number" id="id" name="id" required>
        <br><br>
        
        <button type="submit" name="action" value="rimuovi">Rimuovi Articolo</button>
    </form>
</div>


<div>

	<h1>Gestisci ricicli</h1>
	
	
	
	 <form action="RecyclingServlet" method="get">
        <button type="submit" name="action" value="getMobiliRiciclati">Visualizza Mobili Riciclati</button>
    </form>
<% 
        message = (String) request.getAttribute("message");
        if (message != null) { 
 %>
        <p style="color:red;"><%= message %></p>
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
        	
            <p>Tipo di Mobile: <%= mobile.getTipoMobile() %><p>
            <p>Email Utente: <%= mobile.getEmailUtente() %></p>
            <p>Commento: <%= mobile.getCommento() %></p>
            <img src="GetImageById?id=<%= mobile.getId() %>" alt="Immagine del Mobile Riciclato"/>
            
            <!-- Form per rimuovere l'articolo e aggiungere un bonus -->
            <form action="RecyclingServlet" method="post">
            
            	<input type="hidden" name="action" value="bonus">
                <input type="hidden" name="id" value="<%= mobile.getId() %>">
                <input type="hidden" name="emailUtente" value="<%= mobile.getEmailUtente() %>">

                <label for="bonus">Assegna un Bonus:</label>
                <select id="bonus" name="bonus" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>

                <button type="submit">Rimuovi Mobile Riciclato ed Assegna Bonus</button>
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


</body>
</html>
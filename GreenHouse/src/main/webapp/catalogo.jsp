<%@page import="java.net.http.HttpRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.unisa.model.ArticoloBean"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Catalogo Articoli</title>
        
</head>


<body>
	
	<form action="greenHouseHome.jsp" method="get">
		<button type="submit">Torna alla Home</button>
	</form>
	
	<div>
		<h1>Catalogo Articoli</h1>

		<form action="ManagerArticoloServlet" method="post">
			
			<input type="hidden" name="pathOrigin" value="catalogo.jsp"> 
			<label for="tipologia">Seleziona il tipo di mobile:</label>
			<select id="tipologia" name="tipologia" required>
				<option value="Tavolo">Tavolo</option>
				<option value="Sedie">Sedie</option>
				<option value="Divano">Divano</option>
				<option value="Soggiorno">Soggiorno</option>
				<option value="Mobile generico">Mobile Generico</option>
				<option value="Tutti">Tutti</option>
			</select>
			<br><br>
			
			<button type="submit" name="action" value="mostraPerTipologia">Visualizza
				Articoli</button>
		</form>

		   
    
		<ul>
		
		<% 
       	      	     
			@SuppressWarnings("unchecked")        	 
       	     List<ArticoloBean> articoli = (List<ArticoloBean>) request.getAttribute("articoli");
				
        	    if (articoli != null ) {
        	    
        	        for (ArticoloBean articolo : articoli) {
        	        	
      	  %>
			<li>
				<strong>ID:</strong> <%= articolo.getId() %><br> 
				<strong>Nome:</strong><%= articolo.getNome() %><br> 
				<strong>Tipologia:</strong> <%= articolo.getTipologia() %><br>
				<strong>Descrizione:</strong> <%= articolo.getDescrizione() %><br>
				<strong>Prezzo:</strong> €<%= articolo.getPrezzo() %><br> 
				<strong>Quantità Disponibile:</strong> <%= articolo.getQuantita() %><br>
				<strong>IVA:</strong> <%= articolo.getIva() %>%<br> 				
				<strong>Immagine:</strong> <img src="GetPictureServlet?id=<%= articolo.getId() %>" width="200" height="200" alt="Immagine di : <%= articolo.getNome() %>">
			
				<form action="articolo.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="idArticolo" value="<%= articolo.getId() %>">
                    <button type="submit">Visualizza</button>
                </form>
                
                <form action="ManagerCartServlet" method="post" style="display:inline;">
                	<input type="hidden" name="pathOrigin" value="ManagerArticoloServlet?action=mostraTutti&pathOrigin=catalogo.jsp"> 
                    <input type="hidden" name="action" value="aggiungiAlCarrello">
                    <input type="hidden" name="idArticolo" value="<%= articolo.getId() %>">
                    <button type="submit">Aggiungi al Carrello</button>
                </form>			
			
			
			</li>
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

   

</body>
</html>
<%@page import="com.unisa.dao.ManagerOrdiniDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unisa.model.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.unisa.dao.*" %>

<%

	HttpSession sessione= request.getSession(false);
	
    @SuppressWarnings("unchecked")
    List<OrdineBean> ordiniList = (List<OrdineBean>) request.getAttribute("ordini");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista degli Ordini</title>
</head>
<body>
    <h2>Lista degli Ordini</h2>
	
	<div id="bloccoOrdine">
    <% 
    if (ordiniList != null && !ordiniList.isEmpty()) { 
    	DecimalFormat df = new DecimalFormat("#.00");
    	
    	for (OrdineBean ordine : ordiniList) {
    		
    		List<ComponeBean> componenti =  ManagerOrdiniDao.getComponentsOfOrdine(ordine.getIdOrdine());
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
                    <tr>
                        <td><%= ordine.getIdOrdine() %></td>
                        <td><%= ordine.getDataAcquisto() %></td>
                        <td><%= ordine.getStato() %></td>
                        <td><%= ordine.getCodFattura() %></td>
                        <!-- Formatta il valore double per visualizzare fino alla seconda cifra decimale -->
                        <td><%= df.format(ManagerOrdiniDao.getPrezzoTotaleOrdine(ordine.getIdOrdine())) %></td>                         
                    </tr>                                 
            </tbody>
        </table>
      	</div>
      	 
        <div id="bloccoComponenti">
        			<h4>Componenti dell'Ordine:</h4>
		<%
		
            if (componenti != null && !componenti.isEmpty()) {
                for (ComponeBean componente : componenti) {
                	ArticoloBean articolo = ManagerArticoloDao.getArticolo(componente.getIdArticolo());
                	
                			
               	  
               	 	 if(ordine.getStato().equalsIgnoreCase("consegnato") && componente.getValutazione() == -1){
               		  
        %>
        
        <div id=inserisciValutazione>
        
        		<h2>Valuta l'Articolo</h2>
    
    			<form action="ManagerOrdiniServlet" method="post">
        			
        				<input type="hidden" name="idArticolo" value=<%= componente.getIdArticolo() %>>
        				<input type="hidden" name="idOrdine" value=<%= componente.getIdOrdine() %>>
        				<input type="hidden" name="action" value="aggiornaValutazione">
        				

        				<label for="valutazione">Come valuteresti l'articolo appena acquistato?:</label><br><br>

       			 		
        				<input type="radio" id="1" name="valutazione" value="1" required>
        				<label for="1">1</label><br>

       				 	<input type="radio" id="2" name="valutazione" value="2">
        				<label for="2">2</label><br>

        				<input type="radio" id="3" name="valutazione" value="3">
        				<label for="3">3</label><br>

       					<input type="radio" id="4" name="valutazione" value="4">
        				<label for="4">4</label><br>

        				<input type="radio" id="5" name="valutazione" value="5">
        				<label for="5">5</label><br><br>

        				<button type="submit" >Invia Valutazione</button>
    				</form>
    				
    		</div>
        <%
               		  
               	  }     	  
               	
		%>
			<div id=mostraComponente>
				<p><strong>ID Articolo:</strong> <%= articolo.getId() %></p>
                <p><strong>Nome:</strong> <%= articolo.getNome() %></p>
                <p><strong>Descrizione:</strong> <%= articolo.getDescrizione() %></p>
				<p><strong>Prezzo:</strong> <%= componente.getPrezzo() %></p>
				<p><strong>Quantità:</strong> <%= componente.getQuantita() %></p>
				<p><strong>Valutazione:</strong> <%=componente.getValutazione() %>
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
          <% } %>
    <% } else { %>
        <p>Non ci sono ordini disponibili per questo utente.</p>
    <% } %>

    <form action="ManagerOrdiniServlet" method="post">
    	 <input type="hidden" name="pathOrigin" value="ordini.jsp">
        <input type="hidden" name="action" value="visualizzaOrdiniUtente">
        <button type="submit">Aggiorna Lista Ordini</button>
    </form>
       
</body>
</html>


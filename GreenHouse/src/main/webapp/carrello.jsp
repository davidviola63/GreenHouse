<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.unisa.model.ArticoloBean"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unisa.model.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
    
    
    <style>
        
        #checkoutForm {
            display: none;
        }
        
    </style>
    
    <script>
        
        function mostraCheckout() {
            var form = document.getElementById("checkoutForm");
            form.style.display = "block";
        }
    </script>
    
</head>
<body>

<%

HttpSession sessione = request.getSession(false); // Crea una nuova sessione se non esiste

UtenteBean user = (UtenteBean) sessione.getAttribute("User");

%>
	
	<form action="greenHouseHome.jsp" method="get">
		<button type="submit">Torna alla Home</button>
	</form>
	
    <h1>Il tuo Carrello</h1>


	
		<% 
		String message,errorMessage;
		
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
		
    <%
    	@SuppressWarnings("unchecked")
        List<ArticoloBean> carrello = (List<ArticoloBean>) sessione.getAttribute("carrello");

        if (carrello != null && !carrello.isEmpty()) {
    %>
        <ul>
            <%
                for (ArticoloBean articolo : carrello) {
            %>
                <li>
                	
                    <strong>Nome:</strong> <%= articolo.getNome() %><br>
                    <strong>Prezzo:</strong> €<%= articolo.getPrezzo() %>
                    <hr>
                    
                <form action="ManagerCartServlet" method="get">
                	<input type="hidden" name="action" value="rimuoviDalCarrello"> 
                	<input type="hidden" name="idArticolo" value="<%=articolo.getId() %>">
                	<input type="hidden" name="pathOrigin" value="carrello.jsp">
					<button type="submit">Rimuovi</button>
				</form>		
                </li>
                 
                
            <%
                }
            %>
        </ul>
        
    <%
    if(user.getNome().equalsIgnoreCase("guest")){    		
    %>	
    
    <form action="login.jsp" method="get">
    	<h2>Devi essere loggato per eseguire il checkout!</h2>
		<button type="submit">Esegui il login</button>
	</form>
    <%
    }else{
    %>     
    	<button onclick="mostraCheckout()">Procedi al Checkout</button>
    <%
    	
    }
    %>
    
    
   
    <form id="checkoutForm" action="CheckoutServlet" method="post">
    
        <h2>Modulo di Checkout</h2>
	
		<input type="hidden" name="email" value=<%= user.getEmail()  %>>
		
        <label for="cartaCredito">Carta di Credito:</label>
        <input type="text" id="cartaCredito" name="cartaCredito" maxlength="16" required 
               pattern="\d{16}" title="La carta di credito deve contenere esattamente 16 cifre"><br><br>
        <!-- pattern="\d{16}" indica che il campo carta di credito accetta solo numeri da 0 a 9 grazie a \d e deve essere di esattamente 16 cifre -->
        
        <label for="dataScadenza">Data di Scadenza:</label>
        <input type="text" id="dataScadenza" name="dataScadenza" placeholder="YYYY-MM-DD" 
               pattern="\d{4}-\d{2}-\d{2}" maxlength="10" required><br><br>
        <!-- pattern="\d{4}-\d{2}-\d{2}" indica che il campo accetta la data nel formato YYYY-MM-DD -->
        
        <label for="circuito">Circuito:</label>
        <input type="text" id="circuito" name="circuito" maxlength="50" required><br><br>
        
        <label for="cvc">CVC:</label>
        <input type="text" id="cvc" name="cvc" maxlength="4" pattern="\d{3,4}" required><br><br>
        <!-- pattern="\d{3,4}" indica che il campo accetta solo numeri e può essere di 3 o 4 cifre -->
        
        <label for="applyBonus">Vuoi applicare bonus?</label><br><br>
	

		
        <input type="radio" id="bonus" name="bonus" value="<%=user.getIdBonus() %>" required>
        <label for="yes">Sì</label>

        <input type="radio" id="bonus" name="bonus" value="<%= 1 %>" required>
        <label for="no">No</label><br><br>

        <button type="submit">Conferma Ordine</button>
    </form>
    	 
    <%
        } else {
    %>
        <p>Il carrello è vuoto.</p>
    <%
        }
    %>


</body>
</html>
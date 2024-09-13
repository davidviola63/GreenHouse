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
    

   
</head>


<body>

<%@ include file="header.jsp" %>
	
    <h1>Il tuo Carrello</h1>
    
	<div id="messageContainer"></div>	
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
			<select id="circuito" name="circuito" required>
    			<option value="">-- Seleziona il circuito --</option>
    			<option value="visa">Visa</option>
    			<option value="mastercard">Mastercard</option>
    			<option value="postepay">PostePay</option>
    			<option value="paypal">PayPal</option>
    			<option value="altro">Altro</option>
			</select>
			<br><br>
        
        <label for="cvc">CVC:</label>
        <input type="text" id="cvc" name="cvc" maxlength="4" pattern="\d{3,4}" required><br><br>
        <!-- pattern="\d{3,4}" indica che il campo accetta solo numeri e può essere di 3 o 4 cifre -->
        
        <label for="applyBonus">Vuoi applicare bonus?</label><br><br>

		<input type="radio" id="bonusYes" name="bonus" value="<%=user.getIdBonus() %>" required>
		<label for="bonusYes">Sì</label>
		
		<input type="radio" id="bonusNo" name="bonus" value="1" required>
		<label for="bonusNo">No</label><br><br>
		
		
        <button type="submit">Conferma Ordine</button>
    </form>
    	 
    <%
        } else {
    %>
        <p>Il carrello è vuoto.</p>
    <%
        }
    %>
	
	<%@include file="footer.jsp" %>
			
  <script type="text/javascript" src="./script/mostraMessaggi.js"> </script>
  <script type="text/javascript" src=./script/showCheckoutForm.js> </script> 
</body>
</html>
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
        <div class="cart-items">
            <%
                for (ArticoloBean articolo : carrello) {
            %>
                <div class="cart-item">
                    <img src="GetPictureServlet?action=articoloPicture&id=<%= articolo.getId() %>" alt="<%= articolo.getNome() %>">
                    <div class="cart-item-content">
                        <strong>Nome:</strong> <%= articolo.getNome() %><br>
                        <strong>Prezzo: </strong> €<%= articolo.getPrezzo() %><br>
						<form action="ManagerCartServlet" method="get">
                            <input type="hidden" name="action" value="rimuoviDalCarrello"> 
                            <input type="hidden" name="idArticolo" value="<%= articolo.getId() %>">
                            <input type="hidden" name="pathOrigin" value="carrello.jsp">
                            <button type="submit">Rimuovi</button>
                        </form>        
                    </div>
                </div>
            <%
                }
            %>
        </div>
        
        <%
        if(user.getNome().equalsIgnoreCase("guest")){  
        %>    
            <div class="checkout-msg">
                <form action="login.jsp" method="get">
                    <h2>Devi essere loggato per eseguire il checkout!</h2>
                    <button type="submit">Esegui il login</button>
                </form>
            </div>
        <%
        } else {
        %>     
            <button class="checkout-button" onclick="mostraCheckout()">Procedi al Checkout</button>
        <%
        }
        %>
    
        <form id="checkoutForm" class="checkout-form" action="CheckoutServlet" method="post">
            <h2>Modulo di Checkout</h2>
            <input type="hidden" name="email" value="<%= user.getEmail() %>">
            
            <label for="cartaCredito">Carta di Credito:</label>
            <input type="text" id="cartaCredito" name="cartaCredito" maxlength="16" required 
                   pattern="\d{16}" title="La carta di credito deve contenere esattamente 16 cifre">
            
            <label for="dataScadenza">Data di Scadenza:</label>
            <input type="text" id="dataScadenza" name="dataScadenza" placeholder="YYYY-MM-DD" 
                   pattern="\d{4}-\d{2}-\d{2}" maxlength="10" required>
            
            <label for="circuito">Circuito:</label>
            <select id="circuito" name="circuito" required>
                <option value="">-- Seleziona il circuito --</option>
			<%
        		if(user.getRuolo().equalsIgnoreCase("admin")){  
        	%>
				<option value="admin">Admin</option>
			<%
        	}
			%>
                <option value="visa">Visa</option>
                <option value="mastercard">Mastercard</option>
                <option value="postepay">PostePay</option>
                <option value="paypal">PayPal</option>
                <option value="altro">Altro</option>
            </select>
            
            <label for="cvc">CVC:</label>
            <input type="text" id="cvc" name="cvc" maxlength="4" pattern="\d{3,4}" required>
            
            <div class="radio-group">
                <label>Vuoi applicare bonus?</label><br>
                <input type="radio" id="bonusYes" name="bonus" value="<%=user.getIdBonus() %>" required>
                <label for="bonusYes">Sì</label>
                
                <input type="radio" id="bonusNo" name="bonus" value="1" required>
                <label for="bonusNo">No</label>
            </div>
            
            <button type="submit">Conferma Ordine</button>
        </form>
    <%
        } else {
    %>
        <p class="empty-cart">Il carrello è vuoto.</p>
    <%
        }
    %>
    
    <%@ include file="footer.jsp" %>
    
    <script type="text/javascript" src="./script/mostraMessaggi.js"></script>
    <script type="text/javascript" src="./script/showCheckoutForm.js"></script>
    <script type="text/javascript">
        function mostraCheckout() {
            var form = document.getElementById('checkoutForm');
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        }
    </script>
</body>
</html>
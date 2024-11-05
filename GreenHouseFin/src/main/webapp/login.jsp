<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Login-GreenHouse</title>


</head>


<body>

	<%@ include file="header.jsp" %>
	
	<br>
	
	<div id="messageContainer"></div>
	
	<div class="container_content">
	
		<h2>Sei già un nostro amico? Esegui il login:</h2>			
			
		<form class="loginForm" id="loginForm" action="ManagerUserServlet" method="post" onsubmit="return validateLoginInput()">
													
			<input type="hidden" name="action" value="login"> 
			
			<label for="email1">Email:</label>
        	<input type="text" id="email1" name="email" required>
        	<div id ="message1login" style="display: none;">
					<p>Inserire <b>formato corretto</b></p>
				</div>	
			<br><br>
			
			
       		<label for="password1">Password:</label>
        	<input type="password" id="password1" name="password" required>
        	<div id ="message2login" style="display: none;">
					<p>Inserire <b>solo caratteri alfanumerici</b></p>
				</div>
			<br><br>
        	
        	<button type="submit">Accedi</button>
			       	
		</form>
		
	</div>
		
		
	<br><br> 
	
	
	<div class="container_content">
	
		<button onclick="displayForm()">Apri form registrazione</button>			
		 
   		 <form class="registrationForm" id="registrationForm" action="ManagerUserServlet" method="post" onsubmit="return validateRegistrationInput()">  		    		 
   		 	   		 	 		 	  		   		 	
   		 	<input type="hidden" name="action" value="registra"> 
   		 	
       		<label for="nome">Nome:</label>
        	<input type="text" id="nome" name="nome" required>
        	<div id ="message1" style="display: none;">
					<p>Inserire <b>solo caratteri,</b> numeri <b>non consentiti</b></p>
				</div>
			<br><br>
        	

        	<label for="cognome">Cognome:</label>
        	<input type="text" id="cognome" name="cognome" required>
        	<div id ="message2" style="display: none;">
					<p>Inserire <b>solo caratteri,</b> numeri <b>non consentiti</b></p>
				</div>
			<br><br>
        	

        	<label for="email">Email:</label>
        	<input type="email" id="email" name="email" onkeyup="checkEmailAvailability()" required>
        	<span id="emailCheckResult"></span> 
        	<div id ="message5" style="display: none;">
					<p>Inserire <b>formato corretto</b></p>
				</div>		
        	<br><br>
        	
       		<label for="password">Password:</label>
        	<input type="password" id="password" name="password" required>
        	<div id ="message4" style="display: none;">
					<p>Inserire <b>solo caratteri alfanumerici</b></p>
				</div>
			<br><br>
        	
        	      
			<label for="citta">Città:</label> 
			<input type="text" id="citta" name="citta" maxlength="25" required> <br> <br>

			<label for="via">Via:</label> 
			<input type="text" id="via" name="via" maxlength="40" required> <br> <br>

			<label for="civico">Civico:</label> 
			<input type="text" id="civico" name="civico" maxlength="5" pattern="\d*" required> <br> <br>
			
			<h3>Inserisci il metodo di pagamento:</h3>
					
			<label for="cartaCredito">Carta di Credito:</label>
        	<input type="text" id="cartaCredito" name="cartaCredito" maxlength=16 required>
			<div id ="message6" style="display: none;">
					<p>Inserire <b> solo cifre</b></p>
				</div>
			<br><br>
			
    		<label for="dataScadenza">Data di scadenza:</label>
    		<input type="text" id="dataScadenza" name="dataScadenza" placeholder="YYYY-MM-DD" maxlength="10" required>
			<div id ="message7" style="display: none;">
					<p>Inserire <b>formato corretto YYYY-MM-DD </b></p>
				</div>
			<br><br>
				
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
    		<input type="text" id="cvc" name="cvc" maxlength="4" required>
			<div id ="message8" style="display: none;">
					<p>Inserire <b>formato corretto CCC</b></p>
				</div>
			<br><br>			
			
        	<button type="submit">Registrati</button>
    	</form>

	</div>
	
	<%@ include file="footer.jsp" %>

<!-- -----------------------------SCRIPT---------------------------------------------------------------------------------- -->	
	
<script type="text/javascript" src="./script/mostraMessaggi.js"> </script>

<script type="text/javascript" src="./script/registrazioneScript.js"></script>

<script src="./script/checkEmailAvailability.js"></script>

<script>
	function displayForm(){
		const form = document.getElementById("registrationForm");
		form.classList.toggle('active');
	}
</script>


</body>
</html>
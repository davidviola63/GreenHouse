<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Login-GreenHouse</title>
<style>
	
	.container_content{
		background-color:white;
		border: medium solid green;
		width:auto;
		height:auto;
		 /*background-image: url("./images/background_homepage.jpg");*/
	}

</style>
</head>


<body>

	<div class="container_content">
		<h2>Sei già un nostro amico? Esegui il login:</h2>	
		<form action="ManagerUserServlet" method="post">
			
			<input type="hidden" name="action" value="login"> 
			<label for="email">Email:</label>
        	<input type="email" id="email" name="email" required><br><br>

       		<label for="password">Password:</label>
        	<input type="password" id="password" name="password" required><br><br>
        	
        	<button type="submit">Accedi</button>
			
			<p id="errorMessage" style="color: red;">
        	<% 
        	String errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) {
               out.println(errorMessage);
       	    }
      		  %>
    		</p>
		</form>
	</div>
	
	<br><br> <!--  doppio breakline per separare un blocco dall'altro -->
	
	<div class="container_content">
		<h2>Se sei nuovo possiamo conoscerci meglio! Registrati al nostro sito:</h2>	
		 
   		 <form id="registrationForm" action="ManagerUserServlet" method="post">
   		 	
   		 	<input type="hidden" name="action" value="registra"> 
       		<label for="nome">Nome:</label>
        	<input type="text" id="nome" name="nome" required><br><br>

        	<label for="cognome">Cognome:</label>
        	<input type="text" id="cognome" name="cognome" required><br><br>

        	<label for="email">Email:</label>
        	<input type="email" id="email" name="email" required><br><br>

       		<label for="password">Password:</label>
        	<input type="password" id="password" name="password" required><br><br>
        	      
			<label for="citta">Città:</label> 
			<input type="text" id="citta" name="citta" maxlength="25" required> <br> <br>

			<label for="via">Via:</label> 
			<input type="text" id="via" name="via" maxlength="40" required> <br> <br>

			<label for="civico">Civico:</label> 
			<input type="text" id="civico" name="civico" maxlength="5" pattern="\d*" required> <br> <br>
			
			<h3>Per completare la registrazione inserisci anche il metodo di pagamento:</h3>
					
			<label for="cartaCredito">Carta di Credito:</label>
        	<input type="text" id="cartaCredito" name="cartaCredito" maxlength=16 required pattern="\d{16}" title="La carta di credito deve contenere esattamente 16 cifre"><br><br>
			<!-- pattern="\d16" indica che che il campo carta di credito accetta solo numeri da 0 a 9 grazie a \d e deve essere di esattamente 16 cifre -->
			
    		<label for="dataScadenza">Data di scadenza:</label>
    		<input type="text" id="dataScadenza" name="dataScadenza" placeholder="YYYY-MM-DD" pattern="\d{4}-\d{2}-\d{2}" maxlength="10" required><br><br>

    		<label for="circuito">Circuito:</label>
   			<input type="text" id="circuito" name="circuito" maxlength="50" required><br><br>

    		<label for="cvc">CVC:</label>
    		<input type="text" id="cvc" name="cvc" maxlength="4" pattern="\d{3}" required><br><br>
			
			
        	<button type="submit">Registrati</button>
    	</form>

    	<p id="errorMessage" style="color: red;">
        	<% errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) {
               out.println(errorMessage);
       	    }
      		  %>
    	</p>
	</div>
	
</body>
</html>
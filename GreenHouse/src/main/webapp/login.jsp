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
		<form action="LoginServlet" method="post">
		
			<label for="email">Email:</label>
        	<input type="email" id="email" name="email" required><br><br>

       		<label for="password">Password:</label>
        	<input type="password" id="password" name="password" required><br><br>
        	
        	<button type="submit">Accedi</button>

			
			<!-- Lo script qui sotto fa comparire il messaggio di errore nel form nel caso le credenziali dell'utente non siano valide
			intercettando l'attributo errorMerssage nella request che verrà aggiornato dalla servlet con la stringa di errore -->
			
			<p id="errorMessage" style="color: red;">
        	<% String errorMessage = (String) request.getAttribute("errorMessage");
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
		 
   		 <form id="registrationForm" action="RegistrationServlet" method="post">
       		 <label for="nome">Nome:</label>
        	<input type="text" id="nome" name="nome" required><br><br>

        	<label for="cognome">Cognome:</label>
        	<input type="text" id="cognome" name="cognome" required><br><br>

        	<label for="email">Email:</label>
        	<input type="email" id="email" name="email" required><br><br>

       		<label for="password">Password:</label>
        	<input type="password" id="password" name="password" required><br><br>

        	<label for="cartaCredito">Carta di Credito:</label>
        	<input type="text" id="cartaCredito" name="cartaCredito" required pattern="\d{16}" title="La carta di credito deve contenere esattamente 16 cifre"><br><br>
			<!-- pattern="\d16" indica che che il campo carta di credito accetta solo numeri da 0 a 9 grazie a \d e deve essere di esattamente 16 cifre -->
			
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
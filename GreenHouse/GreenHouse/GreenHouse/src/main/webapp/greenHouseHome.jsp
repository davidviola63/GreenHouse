<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.unisa.model.*" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>GreenHouse-home</title>

<style>
	
	body{
		background-color:rgb(144, 238, 144); /* colore dello sfondo verde chiaro*/
		font-family: Georgia, serif; /*font del sito*/
		margin: 30px 50px;
	}
	
	
	
	
	/*************************************************************************************************************/
	/* QUI VENGONO RACCOLTE LE CLASSI DELLA NAVBAR*/
	.navbar{
		display:flex; /* Piazza tutto su una riga (row)*/
		background-color:rgb(170, 260, 170);
		overflow: hidden;
		border: medium solid white;
		width:100%;
		max-width:1200px;
		
	}
	
	.container_logo{ 
		display: flex;    
		margin: 10px;
		gap: 15px;
		align-items: center;
		float:left;
	}
		
	.titolo_logo{
            font-size: 32px;               /* Dimensione del font */
            color: #006400;                /* Colore del testo verde scuro */
	}
	
	/*************************************************************************************************************/
	
	
	/*************************************************************************************************************/
	/* QUI VENGONO RACCOLTE LE CLASSI DEL CONTENT*/
	
	.container_content{
		background-color:white;
		border: medium solid green;
		width:auto;
		height:auto;
		 /*background-image: url("./images/background_homepage.jpg");*/
	}
	
	.subtitle{
		
		color: red;
		
	
	}

</style>

</head>


<body>

<%

HttpSession sessione = request.getSession(false); //inizia la pagina con una sessione invalida
UtenteBean user= null;
if(sessione != null){
	user= (UtenteBean) sessione.getAttribute("User");
	
}

%>

<!---------------------------- /*NAVBAR*/ ---------------------------->


											
<div class="navbar">
	<div class="container_logo">
	
		<a href="./greenHouseHome.jsp"> <img src="images/logo.jpg" alt="GreenHouseHome" width="60" height="60" > </a>
		
		<h2 class="titolo_logo"> GreenHouse </h2>	
	
		<a href="UserListServlet">Chi siamo</a>	
		
		<a href="./carrello.jsp">Carrello</a>
			
		<a href="./riciclo.jsp">Riciclo</a>
		
		<%
		if(user!=null){
		%> 
		<a href="LogoutServlet">Logout</a>
		<%
		}else{	
		%>
		<a href="./login.jsp">Ci conosciamo? Registrati o esegui il login!</a>
				
		<%
		}
		%>
	</div>
</div>
<!---------------------------- ----------- --------------------------->


<!---------------------------- /*INTRO DEL SITO*/ ---------------------------->
										
							
<h1 align="center">GREEN HOUSE</h1>
<p>Ciao  <%= (user!=null) ? user.getNome() : "User" %>! Questa è la breve descrizione del sito/ frase ad effetto </p>

<!---------------------------- ----------- --------------------------->

<div class="container_content">
	
	<h2 class="subtitle" align=left > SCOPRI LE NOSTRE OFFERTE!</h2>

</div>



</body>
</html>
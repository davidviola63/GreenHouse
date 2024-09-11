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

<%@ include file="header.jsp" %>
<div id="messageContainer"></div>											
							
<h1 align="center">GREEN HOUSE</h1>
<p>Ciao  <%= user.getNome() %>! Questa è la breve descrizione del sito/ frase ad effetto </p>


<div class="container_content">
	
	<h2 class="subtitle" align=left > SCOPRI LE NOSTRE OFFERTE!</h2>

</div>


<%@ include file="footer.jsp" %>

<script type="text/javascript" src="./script/mostraMessaggi.js"> </script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>GreenHouse-home</title>


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
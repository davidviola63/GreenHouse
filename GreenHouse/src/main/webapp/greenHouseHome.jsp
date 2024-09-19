<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 
STRUTTURA HOMEPAGE 

NAVBAR
1. Breve descrizione del sito e presentazione con singola frase (indirizzamento CHI SIAMO)
2. Slideshow di 5 prodotti in evidenza
3. Immagine paesaggio con link al riciclo/Descrizione della funzionalità riciclo
FOOTER
-->

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>GreenHouse-home</title>


</head>


<body>

<%@ include file="header.jsp" %>
<div id="messageContainer"></div>											
				


<div class="container_content">	
	<h2 class="subtitle" align=left > Descrizione GreenHouse</h2>
	<p>Ciao  <%= user.getNome() %>! Questa è la breve descrizione del sito/ frase ad effetto </p>
</div>



<%@ include file="footer.jsp" %>

<script type="text/javascript" src="./script/mostraMessaggi.js"> </script>

</body>
</html>
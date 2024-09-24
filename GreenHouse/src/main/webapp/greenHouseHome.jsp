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

<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css"/>


</head>


<body>

<%@ include file="header.jsp" %>

<div id="messageContainer"></div>											
				


<div class="container_content">	
	<h2 class="subtitle" align=left > Descrizione GreenHouse</h2>
	<p>Ciao  <%= user.getNome() %>! Cosa stai cercando?  </p>
	<input type="text" id="barraDiRicerca" onkeyup="cercaArticoli()" placeholder="Cerca articoli...">
	<div id="suggestions"></div>
		
</div>

    <div class="slider">
        <div><a href="./articolo.jsp?idArticolo=39"  ><img src="GetPictureServlet?action=articoloPicture&id=39" alt="Divano verde"></a></div>
        <div><a href="./articolo.jsp?idArticolo=42"  ><img src="GetPictureServlet?action=articoloPicture&id=42" alt="Tavolo Greenhouse"></a></div>
        <div><a href="./articolo.jsp?idArticolo=45"  ><img src="GetPictureServlet?action=articoloPicture&id=45" alt="Sedie Folk"></a></div>
        <div><a href="./articolo.jsp?idArticolo=50"  ><img src="GetPictureServlet?action=articoloPicture&id=50" alt="Soggiorno Greenhouse"></a></div>
        <div><a href="./articolo.jsp?idArticolo=52"  ><img src="GetPictureServlet?action=articoloPicture&id=52" alt="Tavolino Greenhouse"></a></div>
    </div>




<%@ include file="footer.jsp" %>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>

<script type="text/javascript" src="./script/greenHouseSuggest.js"> </script>
<script type="text/javascript" src="./script/mostraMessaggi.js"> </script>
<script type="text/javascript" src="./script/slidingImages.js"> </script>

</body>
</html>
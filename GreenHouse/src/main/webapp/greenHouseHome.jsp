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
	<title>Homepage</title>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css"/>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css"/>
</head>


<body>

<%@ include file="header.jsp" %>

<div class="container_content">	
	<h1>Benvenuto in GreenHouse</h1>
	<p>Ciao, <%= user.getNome() %>! Qui puoi trovare i migliori prodotti di arredo per la tua casa.</p>
	
	<p>Cosa stai cercando?  </p>
	<input type="text" id="barraDiRicerca" onkeyup="cercaArticoli()" placeholder="Cerca articoli...">
	<div id="suggestions"></div>
		

	<!--  Sezione Slider -->
    <div class="slider">
        <div><a href="./articolo.jsp?idArticolo=39"><img src="GetPictureServlet?action=articoloPicture&id=39" alt="Divano verde"></a></div>
        <div><a href="./articolo.jsp?idArticolo=42"><img src="GetPictureServlet?action=articoloPicture&id=42" alt="Tavolo Greenhouse"></a></div>
        <div><a href="./articolo.jsp?idArticolo=45"><img src="GetPictureServlet?action=articoloPicture&id=45" alt="Sedie Folk"></a></div>
        <div><a href="./articolo.jsp?idArticolo=50"><img src="GetPictureServlet?action=articoloPicture&id=50" alt="Soggiorno Greenhouse"></a></div>
        <div><a href="./articolo.jsp?idArticolo=52"><img src="GetPictureServlet?action=articoloPicture&id=52" alt="Tavolino Greenhouse"></a></div>
    </div>

	<!-- Sezione Categorie -->
    <div class="categories">
        <div class="category" onclick="location.href='catalogo.jsp?tipologiaParameter=divano';" style="cursor: pointer;">
				<img src="GetPictureServlet?action=articoloPicture&id=39" alt="Divano verde">
            	<h3>Divani</h3>
        </div>
        
        <div class="category" onclick="location.href='catalogo.jsp?tipologiaParameter=sedie';" style="cursor: pointer;">
            <img src="GetPictureServlet?action=articoloPicture&id=45" alt="Sedie Folk">
            <h3>Sedie</h3>
        </div>
        
        <div class="category" onclick="location.href='catalogo.jsp?tipologiaParameter=tavolo';" style="cursor: pointer;">
            <img src="GetPictureServlet?action=articoloPicture&id=42" alt="Tavolo Greenhouse">
            <h3>Tavoli</h3>
        </div>
        
        <div class="category" onclick="location.href='catalogo.jsp?tipologiaParameter=soggiorno';" style="cursor: pointer;">
            <img src="GetPictureServlet?action=articoloPicture&id=50" alt="Soggiorno Greenhouse">
            <h3>Soggiorni</h3>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<script type="text/javascript" src="./script/greenHouseSuggest.js"> </script>
<script type="text/javascript" src="./script/mostraMessaggi.js"> </script>
<script type="text/javascript" src="./script/slidingImages.js"> </script>

</body>
</html>
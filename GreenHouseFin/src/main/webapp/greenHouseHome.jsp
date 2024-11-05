<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<div id="suggestions" class="suggestions-box"></div>
		

	<!--  Sezione Slider -->
	<h1>Ecco alcuni dei nostri articoli migliori!</h1>
    <div class="slider">
        <div><a href="./articolo.jsp?idArticolo=41"><img src="GetPictureServlet?action=articoloPicture&id=41" alt="Mobile consigliato1"></a></div>
        <div><a href="./articolo.jsp?idArticolo=43"><img src="GetPictureServlet?action=articoloPicture&id=43" alt="Mobile consigliato2"></a></div>
        <div><a href="./articolo.jsp?idArticolo=47"><img src="GetPictureServlet?action=articoloPicture&id=47" alt="Mobile consigliato3"></a></div>
        <div><a href="./articolo.jsp?idArticolo=49"><img src="GetPictureServlet?action=articoloPicture&id=49" alt="Mobile consigliato4"></a></div>
        <div><a href="./articolo.jsp?idArticolo=53"><img src="GetPictureServlet?action=articoloPicture&id=53" alt="Mobile consigliato5"></a></div>
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
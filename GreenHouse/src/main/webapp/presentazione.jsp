<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi Siamo-GreenHouse</title>
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
	
		<h2>Membri della nostra piccola realtà:</h2>
	
		<ul>
       	 <% 
       	    
       	     
       	    //sopprime il warning riguardante il mancato controllo del casting da Object a List<String>
			@SuppressWarnings("unchecked") 
       	 
       	 	// Recupera la lista dei nomi dall'attributo della request
       	     List<String> userNames = (List<String>) request.getAttribute("userNames");
	
       	     // Itera attraverso la lista e stampa ogni nome
	
        	    if (userNames != null) {
        	        for (String nome : userNames) {
      	  %>
       	             <li><%= nome %></li>
       	 <% 
      	          }
      	      } else {
      	  %>
       	     <li>Nessun utente trovato.</li>
       	 <% 
       	     }
        	%>
    	</ul>
    
    </div>


</body>
</html>
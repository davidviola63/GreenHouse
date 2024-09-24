<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Ordini</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>   
  
<body>
	
	<%@ include file="header.jsp" %>
	<%
	if(user.getNome().equalsIgnoreCase("guest")){
		 String param = "Sessione scaduta.";
         String encodedParam = URLEncoder.encode(param, "UTF-8");
         response.sendRedirect("greenHouseHome.jsp?errorMessage="+encodedParam);
	}
%>
	
    <h2>Visualizza Ordini</h2>
	
    <div>        
        <input type="hidden" id="email" value="<%=user.getEmail() %>">
    </div>
       

    <div id="ordiniContainer"></div>
    
    <div id="dettagliContainer"></div>

    <script type="text/javascript" src="./script/mostraOrdini.js"></script>
    
    <script>
    	window.onload = function() {
       		 getOrdiniByUser(); 
    	};
    </script>

    
	<%@include file="footer.jsp" %>
	
</body>
</html>
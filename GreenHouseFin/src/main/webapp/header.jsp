<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.unisa.model.*" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Header</title>
	<link rel="stylesheet" href="./css/greenhouse.css">
</head>

<body>

<%

HttpSession sessione = request.getSession(true); // Crea una nuova sessione se non esiste

UtenteBean user = (UtenteBean) sessione.getAttribute("User");
if (user == null) {
    //Se l'utente non è loggato, impostiamo lo stato come "guest"; 
    user=new UtenteBean();
    user.setNome("Guest");
    user.setRuolo("Cliente");
    sessione.setAttribute("User", user);  
}

%>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script>
        $(document).ready(function() {
            $.ajax({
                url: "ManagerUserServlet",
                method: "GET",
                data: {
					action : "refreshBonus"
				}
			});
        });
    </script>

<!---------------------------- /*NAVBAR*/ ---------------------------->
											
<div class="navbar">
	<div class="container_logo">
		<a href="./greenHouseHome.jsp"> <img src="images/LogoTextWhite.png" alt="GreenHouseHome" width="80" height="80"></a>		
    </div>	
	
	<div class="navbar_links">
		<a href="chisiamo.jsp">Chi siamo</a>		
		<a href="catalogo.jsp">Catalogo</a>			
		<a href="./carrello.jsp">Carrello</a>
			
		<%
		if(!user.getNome().equals("Guest")){
		%>
		
		<a href="./ordini.jsp">Ordini</a>		
		<a href="./riciclo.jsp">Riciclo</a>		
		<a href="ManagerUserServlet?action=logout">Logout</a>		
		<a href="./link_inesistente">404</a>
		
		
		<%
			
			if(user.getRuolo().compareToIgnoreCase("Admin")==0){				
		%>	
			<a href="./panelAdmin.jsp">Admin</a>			
		<%	
			}

		}else{
			
		%>
			
			<a href="./login.jsp">Ci conosciamo? Registrati o esegui il login!</a>
					
		<%
		}
		%>
	</div>	
</div>
</body>
</html>
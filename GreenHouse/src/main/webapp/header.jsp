<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.unisa.model.*" %>
 
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>HEADER</title>

<style type="text/css">


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

</style>
</head>
<body>

<%

HttpSession sessione = request.getSession(true); // Crea una nuova sessione se non esiste

UtenteBean user = (UtenteBean) sessione.getAttribute("User");
if (user == null) {
    // Se l'utente non è loggato, impostiamo lo stato come "guest"; 
    user=new UtenteBean();
    user.setNome("Guest");
    user.setRuolo("Cliente");
    sessione.setAttribute("User", user);
    
}

%>

<!---------------------------- /*NAVBAR*/ ---------------------------->


											
<div class="navbar">
	<div class="container_logo">
	
		<a href="./greenHouseHome.jsp"> <img src="images/logo.jpg" alt="GreenHouseHome" width="60" height="60" > </a>
		
		<h2 class="titolo_logo"> GreenHouse </h2>	
	
		<a href="chisiamo.jsp">Chi siamo</a>
		
		<a href="catalogo.jsp">Catalogo</a>	
		
		<a href="./carrello.jsp">Carrello</a>
			
		
		
		<%
		if(!user.getNome().equals("Guest")){
		%>
		
		<a href="./ordini.jsp">Ordini</a>
		
		<a href="./riciclo.jsp">Riciclo</a>
		
		<a href="ManagerUserServlet?action=logout">Logout</a>
		
		<%
			
			if(user.getRuolo().compareToIgnoreCase("Admin")==0){				
		%>	
			<a href="./panelAdmin.jsp">Pannello Admin</a>			
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

<!---------------------------- ----------- --------------------------->
</body>
</html>
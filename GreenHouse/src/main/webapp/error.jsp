<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accesso Richiesto</title>
</head>
<body>

<%


	session = request.getSession(false);
	String username = (session != null) ? (String) session.getAttribute("username") : null;
	
    if (username == null) {
  %>
  
          <h2>Devi effettuare il login per accedere a questa pagina. Oppure torna alla Home</h2>
          <form action="login.jsp" method="get">
              <button type="submit">Vai alla pagina di Login</button>
          </form>
           <form action="greenHouseHome.jsp" method="get">
              <button type="submit">Torna alla Home</button>
          </form>
          
          
          
  <%
      } else if(exception != null) {
    		  	out.println("Exception: "+exception.getMessage());
    		  	out.println("<br><br>");
    		 	 StackTraceElement[] st = exception.getStackTrace();
    		  	for(StackTraceElement e: st){
    			  	out.println(e.toString()+"<br>");
    		  }    	
      }
  %>

</body>
</html>
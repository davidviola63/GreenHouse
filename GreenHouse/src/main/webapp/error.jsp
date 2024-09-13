<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ERROR PAGE</title>
</head>
<body>

      <% 
		String message,errorMessage;
		
        errorMessage = (String) request.getAttribute("errorMessage");
    	message = (String) request.getAttribute("message");    
		if (errorMessage != null) { 
   		 %>
		<p style="color: red;"><%= errorMessage %></p>
		<% 
        } 
        if(message!=null){ %>
		<p style="color: green;"><%= message %></p>
		<% 
   		 }
		%>
          
  <%
      if(exception != null) {
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
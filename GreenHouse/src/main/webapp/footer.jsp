<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Footer</title>
<style>

.footer{
		display:flex; /* Piazza tutto su una riga (row)*/
		background-color:rgb(170, 260, 170);
		overflow: hidden;
		border: medium solid white;
		width:100%;
		
			
	}

</style>
	
</head>
<body>

<footer>
	<div class="footer">
		<div class="first-footer">
			<h3> Link Rapidi </h3>
			<ul >
        		<li class="nav-link"><a href="./greenHouseHome.jsp"> Home </a></li>
           		<li class="nav-link"><a href="./presentazione.jsp"> Chi Siamo </a></li>
           		<li class="nav-link"><a href="./carrello.jsp"> Carrello </a></li> 
       		</ul>
		</div>
		
 		<div class = "name-contact">
 			<h3 align="center"> Contatti </h3>
 				<ul class="footer-nav">
 					<li class="nav-link"><a href="mailto:d.viola2@studenti.unisa.it"> Davide Viola</a></li>
               		<li class="nav-link"><a href="mailto:a.amato@studenti.unisa.it"> Antonio Amato</a></li>
               		<li class="nav-link"><a href="mailto:a.sangiovanni@studenti.unisa.it"> Antonio Sangiovanni</a></li>
                </ul>
 		</div>
 		
 		<div class = "second-footer">
 			<div class = "legal-footer">
 				<ul class = "footer-nav">
 					<li class = "nav-legal"><a href = "https://www.ecommercestrategies.it/privacy/"> Privacy e Policy </a> </li>
 					<li class = "nav-legal"><a href = "https://www.ecommercestrategies.it/cookie-policy/"> Cookie Policy </a> </li>
 				</ul>
 			</div>
 		<div class = "copyright">
			<p> &copy;2024,Greenhouse
		</div>
	</div> 
 	</div>
 	
</footer>   
	
</body>
</html>
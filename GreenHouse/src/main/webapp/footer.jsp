<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Footer</title>

<style>
        /* Stile generale del footer */
        .footer {
            display: flex; 
            justify-content: space-between;
            background-color: #2f4f4f; 
            padding: 20px;
            box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
            color: #fff;
            max-width: auto; 
            margin: auto;
        }

        .footer div {
            flex: 1;
            padding: 10px; 
        }

        /* Stile per i titoli delle sezioni */
        .footer h3 {
            font-size: 20px; 
            color: #fff; 
            margin-bottom: 15px; 
            font-weight: bold;
            letter-spacing: 1px; 
        }

        /* Stile degli elenchi nelle sezioni */
        .footer ul {
            list-style: none; 
            padding: 0; 
        }

        /* Stile dei link */
        .footer a {
            color: #fff; 
            text-decoration: none; 
            display: block; 
            margin-bottom: 10px; 
            font-size: 16px; 
            transition: color 0.3s ease; /* Transizione morbida per il cambio di colore */
        }

        /* Effetto hover sui link del footer */
        .footer a:hover {
            color: #00b300; 
        }

        /* Stile responsive per il footer su dispositivi mobile */
        @media (max-width: 768px) {
            .footer {
                flex-direction: column; 
                text-align: center;
            }

            .footer div {
                margin-bottom: 20px; 
            }
        }

        /* Stile della sezione copyright */
        .copyright {
            text-align: center; 
            padding: 10px; 
            background-color: #1f1f1f; 
            color: #fff; 
            font-size: 14px; 
            border-top: 1px solid #006400; 
        }

        /* Stile extra per enfatizzare i titoli delle sezioni */
        .footer h3::after {
            content: ''; /* Crea una linea decorativa sotto il titolo */
            display: block;
            width: 50px; 
            height: 2px; 
            background-color: #00b300; 
            margin-top: 5px; 
        }

        /* Sezione social e pagamenti */
        .footer-logos {
            display: flex; 
            justify-content: center; 
            margin-top: 20px; 
        }

        /* Stile per i loghi social */
        .social-logos img {
            width: 30px; 
            height: 30px; 
            margin-right: 15px; 
            transition: transform 0.3s ease; 
        }
        
        .payment-logos img {
        	width: 42px; 
            height: 42px; 
            margin-right: 15px; 
            transition: transform 0.3s ease; 
        }

        /* Effetto hover sui loghi */
        .social-logos img:hover, .payment-logos img:hover {
            transform: scale(1.1); /* Ingrandisce leggermente il logo al passaggio del mouse */
        }

</style>
	
</head>

<body>
<footer>
	<div class="footer">
        <!-- Prima sezione: Link rapidi per la navigazione del sito -->
        <div class="first-footer">
            <h3>Link Rapidi</h3>
            <ul>
                <li class="nav-link"><a href="./greenHouseHome.jsp">Home</a></li>
                <li class="nav-link"><a href="./chisiamo.jsp">Chi Siamo</a></li>
                <li class="nav-link"><a href="./carrello.jsp">Carrello</a></li>
            </ul>
        </div>
        
        <!-- Seconda sezione: Informazioni di contatto -->
        <div class="name-contact">
            <h3>Contatti</h3>
            <ul>
                <li class="nav-link"><a href="mailto:d.viola2@studenti.unisa.it">Davide Viola</a></li>
                <li class="nav-link"><a href="mailto:a.amato88@studenti.unisa.it">Antonio Amato</a></li>
                <li class="nav-link"><a href="mailto:a.sangiovanni9@studenti.unisa.it">Antonio Sangiovanni</a></li>
            </ul>
        </div>

        <!-- Terza sezione: Legal (Privacy e Cookie Policy) -->
        <div class="second-footer">
            <h3>Legal</h3>
            <ul>
                <li class="nav-legal"><a href="https://www.ecommercestrategies.it/privacy/">Privacy e Policy</a></li>
                <li class="nav-legal"><a href="https://www.ecommercestrategies.it/cookie-policy/">Cookie Policy</a></li>
            </ul>
        </div>
    </div>

    <!-- Sezione loghi social e pagamenti -->
    <div class="footer-logos">
        <!-- Loghi Social -->
        <div class="social-logos">
            <a href="https://www.facebook.com"><img src="images/facebook.svg" alt="Facebook"></a>
            <a href="https://www.instagram.com"><img src="images/instagram.svg" alt="Instagram"></a>
            <a href="https://www.x.com"><img src="images/twitter.svg" alt="Twitter"></a>
        </div>

        <!-- Loghi Pagamenti -->
        <div class="payment-logos">
            <a href="https://www.visaitalia.com/"><img src="images/visa.png" alt="Visa"></a>
            <a href="https://www.mastercard.it/it-it.html"><img src="images/mastercard.png" alt="Mastercard"></a>
		</div>
    </div>

    <!-- Sezione Copyright -->
    <div class="copyright">
        &copy; 2024, Greenhouse - Tutti i diritti riservati.
    </div>	
</footer>   	
</body>
</html>
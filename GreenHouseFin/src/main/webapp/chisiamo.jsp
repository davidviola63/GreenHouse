<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Siamo</title>
    <style>
        body {
            font-family: Helvetica, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .container {
            max-width: 900px;
            margin: 40px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2.5rem;
            color: #2c3e50;
            text-align: center;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.2rem;
            line-height: 1.6;
            margin-bottom: 20px;
        }

        .section-title {
            font-size: 1.8rem;
            color: #4CAF50;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>


<div class="container">
    <h1>Chi Siamo</h1>

 
    <p class="section-title">La Nostra Missione</p>
    <p>
        Siamo un'azienda dedicata alla sostenibilità e al riciclo, con l'obiettivo di ridurre l'impatto ambientale dei mobili usati. La nostra missione è fornire un servizio di riciclo efficiente e conveniente per i nostri clienti, aiutandoli a gestire in modo responsabile i mobili che non desiderano più.
    </p>

    <p class="section-title">La Nostra Storia</p>
    <p>
        Fondata nel 2020, la nostra azienda è nata dalla passione per l'ambiente e il desiderio di fare la differenza. Con anni di esperienza nel settore e una squadra di esperti, ci siamo affermati come leader nel riciclo di mobili, offrendo soluzioni innovative e sostenibili.
    </p>


    <p class="section-title">Il Nostro Team</p>
    <p>
        Il nostro team è composto da professionisti qualificati e appassionati del settore del riciclo e della sostenibilità. Lavoriamo insieme per garantire che ogni aspetto del nostro servizio sia gestito con la massima cura e attenzione ai dettagli.
    </p>


    <p class="section-title">Contattaci</p>
    <p>
        Se hai domande o desideri maggiori informazioni sui nostri servizi, non esitare a contattarci. Siamo qui per aiutarti e fornire supporto in ogni fase del processo di riciclo dei tuoi mobili.
    </p>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
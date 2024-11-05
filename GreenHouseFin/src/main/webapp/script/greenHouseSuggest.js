function cercaArticoli() {
	
	//preleva il valore del campo di testo della barra di ricerca e lo salva in query
    let query = document.getElementById('barraDiRicerca').value;
    
	
	
    if (query.length == 0) {
        document.getElementById('suggestions').innerHTML = "";
        return;
    }
    
    // Creazione dell'oggetto XMLHttpRequest
    let xhr = new XMLHttpRequest();
    
    // Definisce la funzione da eseguire quando si riceve una risposta
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById('suggestions').innerHTML = this.responseText;
        }
    };
    
    // Invio richiesta GET con il parametro 'query'
    xhr.open("GET", "SearchArticlesServlet?query=" + query, true);
    xhr.send();
}

function selezionaArticolo(id) {

    window.location.href = "articolo.jsp?idArticolo=" + id;
	
}
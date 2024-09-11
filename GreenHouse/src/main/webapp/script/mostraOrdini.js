
function getAllOrdini() {

    $.ajax({
        url: 'ManagerOrdiniServlet',
        type: 'POST',
        data: { action: 'visualizzaOrdini'},
        success: function(response) {
            $("#ordiniContainer").html(response);
			$("#dettagliContainer").html(''); // Resetta i dettagli
        },
        error: function(error) {
            console.error("Errore durante il recupero degli ordini: ", error);
        }
    });
}

function getOrdiniByUser() {
    var email = $("#email").val();
    $.ajax({
        url: 'ManagerOrdiniServlet',
        type: 'POST',
        data: { action: 'visualizzaOrdiniUtente', email: email },
        success: function(response) {
            $("#ordiniContainer").html(response);
			$("#dettagliContainer").html(''); // Resetta i dettagli
        },
        error: function(error) {
            console.error("Errore durante il recupero degli ordini per utente: ", error);
        }
    });
}

function getOrdiniByDate() {
    var dataInizio = $("#dataInizio").val();
    var dataFine = $("#dataFine").val();
    $.ajax({
        url: 'ManagerOrdiniServlet',
        type: 'POST',
        data: { action: 'visualizzaOrdiniPerData', dataInizio: dataInizio, dataFine: dataFine},
        success: function(response) {
            $("#ordiniContainer").html(response);
			$("#dettagliContainer").html(''); // Resetta i dettagli
        },
        error: function(error) {
            console.error("Errore durante il recupero degli ordini per intervallo di date: ", error);
        }
    });
}

function getDettagliOrdine(ordineId) {
            $.ajax({
                url: 'ManagerOrdiniServlet',
                type: 'POST',
                data: { action: 'visualizzaComponentiOrdine', ordineId: ordineId },
                success: function(response) {
                    $("#dettagliContainer").html(response); // Mostra i dettagli
                },
                error: function(error) {
                    console.error("Errore durante il recupero dei dettagli dell'ordine: ", error);
                }
            });
        }
		
function getAllOrdiniAdmin() {	
		    $.ajax({
		        url: 'ManagerOrdiniServlet',
		        type: 'POST',
		        data: { action: 'visualizzaOrdini' , cambiaStato: 'true' },
		        success: function(response) {
		            $("#ordiniContainer").html(response);
					$("#dettagliContainer").html(''); // Resetta i dettagli
		        },
		        error: function(error) {
		            console.error("Errore durante il recupero degli ordini: ", error);
		        }
		    });
		}
		
function getOrdiniByDateAdmin() {
		    var dataInizio = $("#dataInizio").val();
		    var dataFine = $("#dataFine").val();
		    $.ajax({
		        url: 'ManagerOrdiniServlet',
		        type: 'POST',
		        data: { action: 'visualizzaOrdiniPerData', dataInizio: dataInizio, dataFine: dataFine, cambiaStato: 'true'},
		        success: function(response) {
		            $("#ordiniContainer").html(response);
					$("#dettagliContainer").html(''); // Resetta i dettagli
		        },
		        error: function(error) {
		            console.error("Errore durante il recupero degli ordini per intervallo di date: ", error);
		        }
		    });
		}

function getDettagliOrdineAdmin(ordineId) {
		            $.ajax({
		                url: 'ManagerOrdiniServlet',
		                type: 'POST',
		                data: { action: 'visualizzaComponentiOrdine', ordineId: ordineId , cambiaStato: 'true'},
		                success: function(response) {
		                    $("#dettagliContainer").html(response); // Mostra i dettagli
		                },
		                error: function(error) {
		                    console.error("Errore durante il recupero dei dettagli dell'ordine: ", error);
		                }
		            });
		        }

//<div id="ordiniContainer"></div> <----- gli ordini verranno mostrati in questo container
//<div id="dettagliContainer"></div> <--------- i componenti dell'ordine verranno visualizzati qui





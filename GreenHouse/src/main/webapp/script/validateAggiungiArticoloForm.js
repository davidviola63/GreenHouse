
function checkNome(nome) {
    var regex = /^[A-Za-z\s]+$/; 
    if (regex.test(nome.value)) {
        return true;
    } else {
        nome.focus();
        return false;
    }
}


function checkPrezzo(prezzo) {
    var regex = /^[0-9]+(\.[0-9]{1,2})?$/;  
    if (regex.test(prezzo.value) && parseFloat(prezzo.value) > 0) {
        return true;
    } else {
        prezzo.focus();
        return false;
    }
}


function checkQuantita(quantita) {
    var regex = /^[1-9][0-9]*$/; 
    if (regex.test(quantita.value) && parseInt(quantita.value) > 0) {
        return true;
    } else {
        quantita.focus();
        return false;
    }
}

function checkIva(iva) {
    var regex = /^[0-9]+(\.[0-9]{1,2})?$/;  
    if (regex.test(iva.value) && parseFloat(iva.value) > 0) {
        return true;
    } else {
        iva.focus();
        return false;
    }
}


function validateAggiungiArticoloForm() {
    var valid = true;
    
    var nome = document.getElementById("nome");
    var prezzo = document.getElementById("prezzo");
    var quantita = document.getElementById("quantita_disponibile");
    var iva = document.getElementById("iva");

    // Validazione del Nome
    if (checkNome(nome)) {
        document.getElementById("message1nome").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message1nome").style.display = "block";
    }

    // Validazione del Prezzo
    if (checkPrezzo(prezzo)) {
        document.getElementById("message1prezzo").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message1prezzo").style.display = "block";
    }

    // Validazione della Quantità
    if (checkQuantita(quantita)) {
        document.getElementById("message1quantita").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message1quantita").style.display = "block";
    }

    // Validazione dell'IVA
    if (checkIva(iva)) {
        document.getElementById("message1iva").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message1iva").style.display = "block";
    }

    return valid;  
}

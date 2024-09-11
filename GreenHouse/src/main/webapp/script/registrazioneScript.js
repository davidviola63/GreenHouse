function checkName(nome) {
   var regex = /^[A-Za-z]+$/;  // Solo lettere
  if (regex.test(nome.value)) {
      return true;
  } else {
     nome.focus();
     return false;
	}
}

function checkSurname(cognome) {
   return checkName(cognome);  // Riutilizza la funzione checkName
}

function checkPassword(password) {
    var regex = /^[A-Za-z0-9]+$/;  // Lettere e numeri
    if (regex.test(password.value)) {
        return true;
    } else {
        password.focus();
        return false;
    }
}

function checkEmail(email) {
    var regex = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;  // Email semplice
    if (regex.test(email.value)) {
        return true;
    } else {
        email.focus();
        return false;
    }
}

function checkCreditCard(cardNumber) {
    var regex = /^\d{16}$/;  // Esattamente 16 cifre
    if (regex.test(cardNumber.value)) {
        return true;
    } else {
        cardNumber.focus();
        return false;
    }
}

function checkExpiryDate(expiryDate) {
	
    var regex = /^\d{4}-\d{2}-\d{2}$/;  // Formato YYYY-MM-DD
    if (regex.test(expiryDate.value)) {
        return true;
    } else {
        expiryDate.focus();
        return false;
    }
}

function checkCVC(cvc) {
    var regex = /^\d{3}$/;  // Esattamente 3 cifre
    if (regex.test(cvc.value)) {
        return true;
    } else {
        cvc.focus();
        return false;
    }
}

function validateRegistrationInput() {
	
	console.log("registrazionInput");
    var valid = true;	    
    // Recupera gli elementi
    var name = document.querySelector("#nome");
    var surname = document.querySelector("#cognome");
    var password = document.querySelector("#password");
    var email = document.querySelector("#email");
    
    // Validazione Nome
    if (checkName(name)) {
        document.getElementById("message1").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message1").style.display = "block";
    }

    // Validazione Cognome
    if (checkSurname(surname)) {
        document.getElementById("message2").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message2").style.display = "block";
    }
    
    // Validazione Password
    if (checkPassword(password)) {
        document.getElementById("message4").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message4").style.display = "block";
    }
    
    // Validazione Email
    if (checkEmail(email)) {
        document.getElementById("message5").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message5").style.display = "block";
    }
    
    // Validazione Carta di Credito
    var cardNumber = document.querySelector("#cartaCredito");
    if (checkCreditCard(cardNumber)) {
        document.getElementById("message6").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message6").style.display = "block";
    }
    
    // Validazione Data di Scadenza
    var expiryDate = document.querySelector("#dataScadenza");
    if (checkExpiryDate(expiryDate)) {
        document.getElementById("message7").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message7").style.display = "block";
    }

    // Validazione CVC
    var cvc = document.querySelector("#cvc");
    if (checkCVC(cvc)) {
        document.getElementById("message8").style.display = "none";
    } else {
        valid = false;
        document.getElementById("message8").style.display = "block";
    }
    
    return valid;  // Ritorna true se tutti i controlli sono passati
}

function validateLoginInput(){
	
	 var valid=true;
	 console.log("validateinput");
	 var email = document.querySelector("#email1");
	 var password = document.querySelector("#password1");
	 		   
	    if (checkPassword(password)) {
	        document.getElementById("message2login").style.display = "none";
	    } else {
	        valid = false;
	        document.getElementById("message2login").style.display = "block";
	    }		    	  
	    if (checkEmail(email)) {		    	
	        document.getElementById("message1login").style.display = "none";
	    } else {
	        valid = false;
	        document.getElementById("message1login").style.display = "block";
	    }
	    
	  return valid;
}

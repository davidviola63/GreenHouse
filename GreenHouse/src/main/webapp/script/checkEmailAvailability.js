function checkEmailAvailability() {
	
    var email = document.getElementById("email").value;
    var emailCheckResult = document.getElementById("emailCheckResult");

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "CheckEmailServlet?email=" + encodeURIComponent(email), true);
    xhr.onreadystatechange = function() {
        
    	// Controllare se la richiesta è completa (readyState === 4) e se è andata a buon fine (status === 200)
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            if (xhr.responseText === "esiste") {
				console.log("esiste");
                emailCheckResult.innerHTML = "Email già registrata!";
                emailCheckResult.style.color = "red";
            } else {
				console.log("disponibile");
                emailCheckResult.innerHTML = "Email disponibile.";
                emailCheckResult.style.color = "green";
            }
        }
    };
    xhr.send();
}
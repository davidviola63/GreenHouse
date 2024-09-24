function getQueryParam(param) {
        var urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    var errorMessage = getQueryParam("errorMessage");
    var message = getQueryParam("message");

    var messageContainer = document.getElementById("messageContainer");

    if (errorMessage !== "") {
        var errorParagraph = document.createElement("p");
        errorParagraph.style.color = "red";
        errorParagraph.innerHTML = errorMessage;
        messageContainer.appendChild(errorParagraph); 
    }

    if (message !== "") {
        var messageParagraph = document.createElement("p");
        messageParagraph.style.color = "green";
        messageParagraph.innerHTML = message;
        messageContainer.appendChild(messageParagraph);
    }

//<div id="messageContainer"></div> <------- questo blocco è da inserire dove deve essere visualizzato il messaggio
$(document).ready(function() {
           
           function updateCatalog() {
               var tipologia = $('#tipologia').val();

               $.ajax({
                   url: 'ManagerArticoloServlet',
                   method: 'GET',
                   data: {
                       action: 'mostraPerTipologia',
                       tipologia: tipologia,
                      
                   },
                   success: function(response) {
                       $('#catalogoContainer').html(response);
                   },
                   error: function() {
                       alert('Errore durante il recupero dei dati.');
                   }
               });
           }

           $('#tipologia').change(function() {
               updateCatalog();
           });

           updateCatalog();
       });
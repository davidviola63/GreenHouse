$(document).ready(function() {
           
           function updateCatalogAdmin() {
               var tipologia = $('#tipologiaCatalogoAdmin').val();

               $.ajax({
                   url: 'ManagerArticoloServlet',
                   method: 'GET',
                   data: {
                       action: 'mostraPerTipologia',
                       tipologia: tipologia,
                       pagina: 'panelAdmin'
                   },
                   success: function(response) {
                       $('#catalogoContainerAdmin').html(response);
                   },
                   error: function() {
                       alert('Errore durante il recupero dei dati.');
                   }
               });
           }

           $('#tipologiaCatalogoAdmin').change(function() {
               updateCatalogAdmin();
           });

           updateCatalogAdmin();
       });
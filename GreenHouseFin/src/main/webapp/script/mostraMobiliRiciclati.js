       
           function getMobiliRiciclati() {             

               $.ajax({
                   url: 'RecyclingServlet',
                   method: 'GET',
                   data: {
                       action: 'getMobiliRiciclati',                    
                      
                   },
                   success: function(response) {
                       $('#mobiliRiciclatiContainer').html(response);
                   },
                   error: function() {
                       alert('Errore durante il recupero dei dati.');
                   }
               });
           }		   
		   

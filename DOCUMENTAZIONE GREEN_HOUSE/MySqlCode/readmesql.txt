per aggiungere il backup al vostro database dovete:
Requisiti: avere accesso ai comandi sql da linea di comando , si può avere accesso tramite variabili d'ambiente 
mettendo nella sezione PATH  il link alla cartella BIN della cartella MySQLServer nella cartella di MySQL in Programmi
Se l'aggiunta della variabile d'ambiente è andata a buon fine provae con i comandi:
  mysql --version
  mysqldump --version

Una volta settati i comandi per la cmd
-Clickare su "backup2.sql" e scaricare il file backup2.sql attraverso l'opzione "download raw file"
-Verificare che il file scaricato sia di dimensione 2.678 kb 
-Aprire la cmd con (win+r)  :
  -spostarsi nella directory dove si ha scaricato il file sql
  - in seguito digitare il comando |    mysql -u nome_utente -p greenhouse < backup2.sql   | (omettendo gli slash verticali obv)
Ovviamente modificare il valore nome_utente  a seconda di che profilo utilizzate( ad esempio root)
Se il comando va a buon fine con il nome ujtente corretto alla linea ssuccessiva vi verrà chiesta la password dell'account mysql per effettuare la modifica. (Importante controllare di avere un database creato di nome "greenhouse" il quale può essere anche vuoto).
-Per ultimo controllare nel catalogo del sito se le immagini vengono visualizzate


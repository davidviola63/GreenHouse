-- Inserimento dati nella tabella Articolo
INSERT INTO Articolo (Tipologia, Nome, Descrizione, Prezzo, Quantita_Disponibile, IVA, Immagine) VALUES
('Arredamento', 'Soggiorno', 'Soggiorno completo con mobili moderni', 1500.99, 5, 22.0, 'soggiorno.jpg'),
('Arredamento', 'Divano', 'Divano 3 posti in tessuto', 799.99, 10, 22.0, 'divano.jpg'),
('Arredamento', 'Tavolo', 'Tavolo da pranzo in legno', 450.50, 15, 22.0, 'tavolo.jpg');

-- Inserimento dati nella tabella Metodo_Pagamento
INSERT INTO Metodo_Pagamento (ID_Utente, Numero_Carta, Data, Circuito, CVC) VALUES
(1, '1234567890123456', '2024-12-31', 'Visa', 123),
(2, '2345678901234567', '2025-11-30', 'MasterCard', 456),
(3, '3456789012345678', '2026-10-31', 'American Express', 789);

-- Inserimento dati nella tabella Utente
INSERT INTO Utente (Nome, Cognome, Email, Password, Numero_Carta, Ruolo) VALUES
('Mario', 'Rossi', 'mario.rossi@example.com', 'password123', '1234567890123456', 'Cliente'),
('Luigi', 'Verdi', 'luigi.verdi@example.com', 'password456', '2345678901234567', 'Cliente'),
('Anna', 'Bianchi', 'anna.bianchi@example.com', 'password789', '3456789012345678', 'Cliente');

-- Inserimento dati nella tabella Ordine
INSERT INTO Ordine (ID_Utente, stato, data_acquisto, codice_fattura) VALUES
(1, 'Confermato', '2024-01-15', 'FAT12345'),
(2, 'Spedito', '2024-02-20', 'FAT12346'),
(3, 'Consegnato', '2024-03-25', 'FAT12347');

-- Inserimento dati nella tabella Compone
INSERT INTO Compone (ID_Articolo, ID_Ordine, Prezzo_Articolo, Quantita_Selezionata) VALUES
(1, 1, 1500.99, 1),
(2, 2, 799.99, 1),
(3, 3, 450.50, 1);

-- Inserimento dati nella tabella Indirizzo
INSERT INTO Indirizzo (ID, Citta, Via, Civico) VALUES
(1, 'Roma', 'Via Roma', 1),
(2, 'Milano', 'Via Milano', 2),
(3, 'Torino', 'Via Torino', 3);

-- Inserimento dati nella tabella Valutazione
INSERT INTO Valutazione (ID_Utente, ID_Articolo, Commento, Data) VALUES
(1, 1, 'Ottimo soggiorno, molto soddisfatto!', '2024-02-01'),
(2, 2, 'Divano molto comodo e ben fatto.', '2024-03-05'),
(3, 3, 'Tavolo robusto e ben rifinito.', '2024-04-10');
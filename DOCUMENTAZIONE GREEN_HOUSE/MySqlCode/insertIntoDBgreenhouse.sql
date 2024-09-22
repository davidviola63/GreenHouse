-- Inserimento dati nella tabella Articolo
INSERT INTO Articolo (Tipologia, Nome, Descrizione, Prezzo, Quantita_Disponibile, IVA, Immagine) VALUES
('Soggiorno', 'Parete Attrezzata Moderna', 'Soggiorno completo con mobili moderni', 1500.99, 5, 22.0, 'soggiorno.jpg'),
('Divano', 'Divano Camira', 'Divano 3 posti in tessuto', 799.99, 10, 22.0, 'divano.jpg'),
('Tavolo', 'Tavolo GreenHouse', 'Tavolo da pranzo in legno', 450.50, 2, 22.0, 'tavolo.jpg');

-- Inserimento dati nella tabella Bonus
INSERT INTO Bonus (Percentuale_sconto, Descrizione) VALUES 
(0, 'Nessun bonus attivo'),
(20, 'Bonus del 20%'),
(50, 'Bonus del 50%'),
(10, 'Bonus del 10%');

INSERT INTO Utente (Nome, Cognome, Email, Password, Citta, Via, Civico, Ruolo) VALUES
('Admin','Admin','greenhouse@example.com','greenhouse','Salerno', 'Via Aziendale', '10','Admin'),
('Mario', 'Rossi', 'mario.rossi@example.com', 'password123', 'Roma', 'Via Roma', '1', 'Cliente');

-- Inserimento dati nella tabella Metodo_Pagamento
INSERT INTO Metodo_Pagamento (Numero_Carta, Email_Utente, Data, Circuito, CVC) VALUES
('1234567890123456', 'mario.rossi@example.com', '2024-12-31', 'Visa', '123'),
('0000000000000000', 'greenhouse@example.com', '0001-01-01','admin', '000');


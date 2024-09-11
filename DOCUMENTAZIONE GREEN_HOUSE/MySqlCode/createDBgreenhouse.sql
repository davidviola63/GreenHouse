DROP DATABASE IF EXISTS greenhouse;
CREATE DATABASE greenhouse;
USE greenhouse;

create table Articolo(
ID int not null auto_increment,
Tipologia varchar(30) not null,
Nome varchar(60) not null,
Descrizione varchar(250) not null,
Prezzo double not null,
Quantita_Disponibile int not null,
IVA double not null,
Immagine mediumblob not null,
primary key (ID)
);

create table Bonus(
ID int not null auto_increment,
percentuale_sconto  int not null default 0,
descrizione varchar(100),
primary key(ID)
);


create table Utente(
Email varchar(30) not null ,
Password varchar(30) not null,
Nome char(15) not null,
Cognome char(15) not null,
Citta char(25)  not null,
Via char(40) not null,
Civico varchar(4) not null,
Ruolo varchar(20) not null,
ID_Bonus int default 1,
primary key(Email),
foreign key (ID_Bonus) references Bonus(ID)
);

CREATE TABLE Metodo_Pagamento (
    Numero_Carta varchar(16) not null,
    Email_Utente varchar(30) not null,
    Data date not null,
    Circuito varchar(50) not null,
    Cvc varchar(4) not null,
    primary key(Numero_Carta),
	foreign key (Email_Utente) references Utente(Email)
);

CREATE TABLE Mobile_Riciclato (
    ID int NOT NULL AUTO_INCREMENT,
    Email_Utente varchar(30) NOT NULL,
    Immagine mediumblob NOT NULL,
    Tipo_Mobile VARCHAR(15) NOT NULL,
    Commento VARCHAR(200),
    PRIMARY KEY (ID),
    foreign key (Email_Utente) references Utente(Email)
    );

create table Ordine(
	ID int not null auto_increment,
	Email_Utente varchar(30) not null,
    Stato varchar(10) not null,
    Data_Acquisto date not null,
    Codice_Fattura varchar(20),
    foreign key (Email_Utente) references Utente(Email),
    primary key(ID)
);

create table Compone(
ID_Articolo int not null,
ID_Ordine int not null,
Nome_Articolo varchar(60) not null,
Prezzo_Articolo double not null,
Quantita_Selezionata int not null,
Valutazione int not null default -1,
primary key(ID_Articolo,ID_Ordine),
foreign key(ID_Ordine) references Ordine(ID)
);




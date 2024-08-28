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
Immagine varchar(250),
primary key (ID)
);

create table Metodo_Pagamento(
ID_Utente int not null,
Numero_Carta varchar(25) not null,
Data date not null,
Circuito varchar(50) not null,
CVC int not null,
primary key(ID_Utente,Numero_Carta)
);

create table Utente(
ID int not null auto_increment,
Nome char(15) not null,
Cognome char(15) not null,
Email varchar(30) not null,
Password varchar(30) not null,
Numero_Carta varchar(16) references Metodo_Pagamento(Numero_Carta),
Ruolo varchar(20) not null,
primary key(ID)
);

create table Ordine(
	ID int not null auto_increment,
	ID_Utente int not null,
    stato varchar(10) not null,
    data_acquisto date not null,
    codice_fattura varchar(20),
    foreign key (ID_Utente) references Utente(ID),
    primary key(ID,ID_Utente)
);

create table Compone(
ID_Articolo int not null,
ID_Ordine int not null,
Prezzo_Articolo double not null,
Quantita_Selezionata int not null,
primary key(ID_Articolo,ID_Ordine),
foreign key(ID_Ordine) references Ordine(ID),
foreign key(ID_Articolo) references Articolo(ID)
on update cascade
on delete cascade
);




create table Indirizzo(
ID int not null,
Citta char(25)  not null,
Via char(40) not null,
Civico int not null,
primary key(ID),
foreign key(ID) references Utente(ID)
on update cascade
on delete cascade
);



create table Valutazione(
ID_Utente int not null,
ID_Articolo int not null,
Commento varchar(200) not null,
Data date not null,
primary key(ID_Utente,ID_Articolo),
foreign key(ID_Utente) references Utente(ID)
on update cascade
on delete cascade,
foreign key(ID_Articolo) references Articolo(ID)
on update cascade
on delete cascade
);
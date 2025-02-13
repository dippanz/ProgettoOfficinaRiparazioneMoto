DROP TABLE IF EXISTS public."RIPARAZIONE_LAVORAZIONE";
DROP TABLE IF EXISTS public."RIPARAZIONE";
DROP TABLE IF EXISTS public."STATO_RIPARAZIONE";
DROP TABLE IF EXISTS public."MOTO";
DROP TABLE IF EXISTS public."CLIENTE";
DROP TABLE IF EXISTS public."UTENTE_RUOLO";
DROP TABLE IF EXISTS public."UTENTE";
DROP TABLE IF EXISTS public."RUOLO";

-- RUOLO
CREATE TABLE IF NOT EXISTS public."RUOLO"
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "nome" character varying(50) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."RUOLO"
    OWNER to postgres;

-- UTENTE

CREATE TABLE IF NOT EXISTS public."UTENTE"
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "email" character varying(256) NOT NULL UNIQUE,
    "username" character varying(256) UNIQUE,
    "nome" character varying(256),
    "cognome" character varying(256),
    "telefono" character varying(20),
    "hashPassword" text NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."UTENTE"
    OWNER to postgres;

-- UTENTE_RUOLO relazione

CREATE TABLE IF NOT EXISTS public."UTENTE_RUOLO"
(
    "id_utente" uuid NOT NULL,
    "id_ruolo" uuid NOT NULL,
    PRIMARY KEY ("id_utente", "id_ruolo"),
    CONSTRAINT fk_ruolo FOREIGN KEY ("id_ruolo")
        REFERENCES public."RUOLO" ("id"),
    CONSTRAINT fk_utente FOREIGN KEY ("id_utente")
        REFERENCES public."UTENTE" ("id")
);

ALTER TABLE IF EXISTS public."UTENTE_RUOLO"
    OWNER to postgres;

-- CLIENTE

CREATE TABLE IF NOT EXISTS public."CLIENTE"
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "nome" character varying(256),
    "cognome" character varying(256),
	"telefono" character varying(20),
	"email" character varying(256) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."CLIENTE"
    OWNER to postgres;

-- MOTO

CREATE TABLE IF NOT EXISTS public."MOTO"
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "modello" character varying(256),
    "targa" character varying(20) NOT NULL UNIQUE,
    "id_cliente" uuid NOT NULL,
    PRIMARY KEY ("id"),
	CONSTRAINT fk_cliente FOREIGN KEY("id_cliente") REFERENCES public."CLIENTE"("id")
);

ALTER TABLE IF EXISTS public."MOTO"
    OWNER to postgres;


-- Tabella tipologica Stato della riparazione

CREATE TABLE IF NOT EXISTS public."STATO_RIPARAZIONE"
(
    "id" SERIAL NOT NULL ,
    "stato" character varying(256) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

ALTER TABLE IF EXISTS public."STATO_RIPARAZIONE"
    OWNER to postgres;


-- RIPARAZIONE

CREATE TABLE IF NOT EXISTS public."RIPARAZIONE"
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid(),
	"codice_servizio" character varying(30) NOT NULL UNIQUE, -- codice fornito al cliente per ricercare la sua prenotazione
    "id_stato" int,
    "descrizione_problema" text,
	"dataInizio" TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	"dataFine" TIMESTAMP, 
    "id_utente_mec" uuid DEFAULT NULL, -- utente che effettua la riparazione (meccanico)
	"id_moto" uuid NOT NULL,
    "id_utente_reg" uuid NOT NULL, -- utente che registra la riparazione
    PRIMARY KEY ("id"),
	CONSTRAINT fk_utente_mec FOREIGN KEY("id_utente_mec") REFERENCES public."UTENTE"("id"),
	CONSTRAINT fk_utente_reg FOREIGN KEY("id_utente_reg") REFERENCES public."UTENTE"("id"),
	CONSTRAINT fk_stato_riparazione FOREIGN KEY("id_stato") REFERENCES public."STATO_RIPARAZIONE"("id"),
	CONSTRAINT fk_moto FOREIGN KEY("id_moto") REFERENCES public."MOTO"("id")
);

ALTER TABLE IF EXISTS public."RIPARAZIONE"
    OWNER to postgres;

-- RIPARAZIONE_LAVORAZIONE indica lo storico delle lavorazioni effettuate su una riparazione 

CREATE TABLE IF NOT EXISTS public."RIPARAZIONE_LAVORAZIONE"
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "id_riparazione" uuid NOT NULL, -- Riparazione a cui è collegata
    "descrizione" text NOT NULL, -- Descrizione dell'intervento del meccanico
    "ore" decimal(5,2) NOT NULL, -- Ore lavorate con due cifre decimali (es. 1.25 per un'ora e un quarto)
    "data_inserimento" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("id"),
    CONSTRAINT fk_riparazione FOREIGN KEY ("id_riparazione") REFERENCES public."RIPARAZIONE"("id")
);

ALTER TABLE IF EXISTS public."RIPARAZIONE_LAVORAZIONE"
    OWNER to postgres;


INSERT INTO public."STATO_RIPARAZIONE" ("stato")
VALUES
    ('Registrato'),
    ('Rifiutato'),
    ('Accettato'),
    ('In lavorazione'),
    ('Completata');

-- Inserimento dei ruoli
INSERT INTO public."RUOLO" ("nome")
VALUES
	('ADMIN'), 
    ('MECCANICO'),          
    ('ADDETTO_ACCETTAZIONE');  

INSERT INTO public."UTENTE" ("email", "username", "hashPassword")
VALUES ('admin@admin.it', 'ADMIN', '$2a$12$AiXIMBFu4qQa65z9oWRVBO8fL4wX4wtlnzg/bXWZr5yKiWhl.n3Ee'); -- pass: Prova@123

INSERT INTO public."UTENTE" ("email", "username", "hashPassword")
VALUES ('accettazione@ciao.it', 'ACCETTAZIONE', '$2a$12$AiXIMBFu4qQa65z9oWRVBO8fL4wX4wtlnzg/bXWZr5yKiWhl.n3Ee'); -- pass: Prova@123

INSERT INTO public."UTENTE" ("email", "username", "hashPassword")
VALUES ('meccanico@ciao.it', 'MECCANICO', '$2a$12$AiXIMBFu4qQa65z9oWRVBO8fL4wX4wtlnzg/bXWZr5yKiWhl.n3Ee'); -- pass: Prova@123

INSERT INTO public."UTENTE_RUOLO" ("id_utente", "id_ruolo")
VALUES (
  (SELECT ID FROM public."UTENTE" WHERE username='ADMIN'),
  (SELECT ID FROM public."RUOLO" WHERE nome='ADMIN')
);

INSERT INTO public."UTENTE_RUOLO" ("id_utente", "id_ruolo")
VALUES (
  (SELECT ID FROM public."UTENTE" WHERE username='ADMIN'),
  (SELECT ID FROM public."RUOLO" WHERE nome='ADDETTO_ACCETTAZIONE')
);

INSERT INTO public."UTENTE_RUOLO" ("id_utente", "id_ruolo")
VALUES (
  (SELECT ID FROM public."UTENTE" WHERE username='ADMIN'),
  (SELECT ID FROM public."RUOLO" WHERE nome='MECCANICO')
);

INSERT INTO public."UTENTE_RUOLO" ("id_utente", "id_ruolo")
VALUES (
  (SELECT ID FROM public."UTENTE" WHERE username='ACCETTAZIONE'),
  (SELECT ID FROM public."RUOLO" WHERE nome='ADDETTO_ACCETTAZIONE')
);

INSERT INTO public."UTENTE_RUOLO" ("id_utente", "id_ruolo")
VALUES (
  (SELECT ID FROM public."UTENTE" WHERE username='MECCANICO'),
  (SELECT ID FROM public."RUOLO" WHERE nome='MECCANICO')
);
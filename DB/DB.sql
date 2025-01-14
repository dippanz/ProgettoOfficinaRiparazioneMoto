-- RUOLO
CREATE TABLE public."RUOLO"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Nome" character varying(256) NOT NULL UNIQUE,
    PRIMARY KEY ("Id")
);

ALTER TABLE IF EXISTS public."RUOLO"
    OWNER to postgres;

-- UTENTE

CREATE TABLE public."UTENTE"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Email" character varying(256) NOT NULL UNIQUE,
    "UserName" character varying(256) UNIQUE,
    "Nome" character varying(256),
    "Cognome" character varying(256),
    "Telefono" character varying(20),
    "HashPassword" text NOT NULL,
    "Id_ruolo" uuid NOT NULL,
    PRIMARY KEY ("Id"),
	CONSTRAINT fk_ruolo FOREIGN KEY("Id_ruolo") REFERENCES public."RUOLO"("Id")
);

ALTER TABLE IF EXISTS public."UTENTE"
    OWNER to postgres;

-- CLIENTE

CREATE TABLE public."CLIENTE"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Nome" character varying(256),
    "Cognome" character varying(256),
	"Telefono" character varying(20),
	"Email" character varying(256),
	"Id_utente_reg" uuid NOT NULL, -- utente che registra il cliente
    PRIMARY KEY ("Id"),
	CONSTRAINT fk_utentReg_cliente FOREIGN KEY("Id_utente_reg") REFERENCES public."UTENTE"("Id")
);

ALTER TABLE IF EXISTS public."CLIENTE"
    OWNER to postgres;

-- MOTO

CREATE TABLE public."MOTO"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Modello" character varying(256),
    "Targa" character varying(20),
    "Id_cliente" uuid NOT NULL,
	"Id_utente_reg" uuid NOT NULL, -- utente che registra la moto
    PRIMARY KEY ("Id"),
	CONSTRAINT fk_cliente FOREIGN KEY("Id_cliente") REFERENCES public."CLIENTE"("Id"),
	CONSTRAINT fk_utenteReg_moto FOREIGN KEY("Id_utente_reg") REFERENCES public."UTENTE"("Id")
);

ALTER TABLE IF EXISTS public."MOTO"
    OWNER to postgres;


-- Tabella tipologica Stato della riparazione

CREATE TABLE public."STATO_RIPARAZIONE"
(
    "Id" SERIAL NOT NULL ,
    "Stato" character varying(256) NOT NULL UNIQUE,
    PRIMARY KEY ("Id")
);

ALTER TABLE IF EXISTS public."STATO_RIPARAZIONE"
    OWNER to postgres;


-- RIPARAZIONE

CREATE TABLE public."RIPARAZIONE"
(
    "Id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "Id_stato" int,
    "Descrizione" text,
	"DataInizio" TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	"DataFine" TIMESTAMP, 
    "Id_utente_mec" uuid DEFAULT NULL, -- utente che effettua la riparazione (meccanico)
	"Id_moto" uuid NOT NULL,
    PRIMARY KEY ("Id"),
	CONSTRAINT fk_utente_mec FOREIGN KEY("Id_utente_mec") REFERENCES public."UTENTE"("Id"),
	CONSTRAINT fk_stato_riparazione FOREIGN KEY("Id_stato") REFERENCES public."STATO_RIPARAZIONE"("Id"),
	CONSTRAINT fk_moto FOREIGN KEY("Id_moto") REFERENCES public."MOTO"("Id")
);

ALTER TABLE IF EXISTS public."RIPARAZIONE"
    OWNER to postgres;

INSERT INTO public."STATO_RIPARAZIONE" ("Stato")
VALUES
    ('Accettato'),
    ('In lavorazione'),
    ('Completata');




	
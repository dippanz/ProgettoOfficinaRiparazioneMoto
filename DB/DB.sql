-- DROP TABLE IF EXISTS public."RIPARAZIONE";
-- DROP TABLE IF EXISTS public."STATO_RIPARAZIONE";
-- DROP TABLE IF EXISTS public."MOTO";
-- DROP TABLE IF EXISTS public."CLIENTE";
-- DROP TABLE IF EXISTS public."UTENTE_RUOLO";
-- DROP TABLE IF EXISTS public."UTENTE";
-- DROP TABLE IF EXISTS public."RUOLO";
-- DROP TABLE IF EXISTS public."LIVELLO_ACCESSO";

-- LIVELLO DI ACCESSO
-- CREATE TABLE public."LIVELLO_ACCESSO"
-- (
--     "id" SERIAL NOT NULL ,
--     "Livello" character varying(50) NOT NULL UNIQUE,
--     PRIMARY KEY ("id")
-- );

-- ALTER TABLE IF EXISTS public."LIVELLO_ACCESSO"
--     OWNER to postgres;

-- RUOLO
CREATE TABLE IF NOT EXISTS public."RUOLO"
(
    "id" uuid NOT NULL DEFAULT gen_random_uuid(),
    "nome" character varying(50) NOT NULL UNIQUE,
	-- "id_livello_accesso" int,
    PRIMARY KEY ("id")
	-- CONSTRAINT fk_livello_accesso FOREIGN KEY("id_livello_accesso") REFERENCES public."LIVELLO_ACCESSO"("id")
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
    -- "id_ruolo" uuid NOT NULL,
    PRIMARY KEY ("id")
	-- CONSTRAINT fk_ruolo FOREIGN KEY("id_ruolo") REFERENCES public."RUOLO"("id")
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
	"id_utente_reg" uuid NOT NULL, -- utente che registra il cliente
    PRIMARY KEY ("id"),
	CONSTRAINT fk_utentReg_cliente FOREIGN KEY("id_utente_reg") REFERENCES public."UTENTE"("id")
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
	"id_utente_reg" uuid NOT NULL, -- utente che registra la moto
    PRIMARY KEY ("id"),
	CONSTRAINT fk_cliente FOREIGN KEY("id_cliente") REFERENCES public."CLIENTE"("id"),
	CONSTRAINT fk_utenteReg_moto FOREIGN KEY("id_utente_reg") REFERENCES public."UTENTE"("id")
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
    "descrizione" text,
	"dataInizio" TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	"dataFine" TIMESTAMP, 
    "id_utente_mec" uuid DEFAULT NULL, -- utente che effettua la riparazione (meccanico)
	"id_moto" uuid NOT NULL,
    PRIMARY KEY ("id"),
	CONSTRAINT fk_utente_mec FOREIGN KEY("id_utente_mec") REFERENCES public."UTENTE"("id"),
	CONSTRAINT fk_stato_riparazione FOREIGN KEY("id_stato") REFERENCES public."STATO_RIPARAZIONE"("id"),
	CONSTRAINT fk_moto FOREIGN KEY("id_moto") REFERENCES public."MOTO"("id")
);

ALTER TABLE IF EXISTS public."RIPARAZIONE"
    OWNER to postgres;

INSERT INTO public."STATO_RIPARAZIONE" ("stato")
VALUES
    ('In fase di accettazione'),
    ('Accettato'),
    ('In lavorazione'),
    ('Completata');

-- Inserimento dei livelli di accesso
-- INSERT INTO public."LIVELLO_ACCESSO" ("Livello")
-- VALUES
--     ('admin'),
--     ('user'),
-- 	('generic');

-- Inserimento dei ruoli
INSERT INTO public."RUOLO" ("nome")
VALUES
	    ('ADMIN'),              -- Ruolo admin con livello di accesso "admin"
    ('MECCANICO'),          -- Ruolo meccanico con livello di accesso "user"
    ('ADDETTO_ACCETTAZIONE');  -- Ruolo addetto_accettazione con livello di accesso "user"
	-- ('utente_generico');

INSERT INTO public."UTENTE" ("email", "username", "hashPassword")
VALUES ('admin@admin.it', 'admin', '$2a$12$AiXIMBFu4qQa65z9oWRVBO8fL4wX4wtlnzg/bXWZr5yKiWhl.n3Ee'); -- pass: Prova@123

INSERT INTO public."UTENTE_RUOLO" ("id_utente", "id_ruolo")
VALUES (
  (SELECT ID FROM public."UTENTE" WHERE username='admin'),
  (SELECT ID FROM public."RUOLO" WHERE nome='ADMIN')
);

s


	
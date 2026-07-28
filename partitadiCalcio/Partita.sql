-- 1. Tabella Utenti (Richiesta con username, password, ruolo)
-- 1. Tabella UTENTE
     CREATE TABLE utente (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    ruolo VARCHAR(20) NOT NULL

-- 2. Tabella ARBITRO----
    CREATE TABLE arbitro (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    codice_arbitrale VARCHAR(30) UNIQUE NOT NULL
);

-- 3. Tabella TORNEO-----
     CREATE TABLE torneo (
    id_torneo SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    anno INT NOT NULL,
    descrizione TEXT
);

-- 4. Tabella SQUADRA----
     CREATE TABLE squadra (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    anno_fondazione INT NOT NULL,
    citta VARCHAR(50) NOT NULL
);

-- Tabella di Giunzione tra TORNEO e SQUADRA (Relazione Molti-a-Molti)
-- Una squadra partecipa a più tornei, un torneo ha più squadre
      CREATE TABLE torneo_squadra (
       torneo_id BIGINT REFERENCES torneo(id) ON DELETE CASCADE,
        squadra_id BIGINT REFERENCES squadra(id) ON DELETE CASCADE,
           PRIMARY KEY (torneo_id, squadra_id),
);


   -- 5. Tabella GIOCATORE (Relazione Uno-a-Molti con Squadra)
    CREATE TABLE giocatore (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    data_nascita DATE NOT NULL,
    ruolo VARCHAR(30) NOT NULL,
    altezza INT, -- in centimetri
    squadra_id BIGINT NOT NULL REFERENCES squadra(id) ON DELETE RESTRICT,
);


-- 6. Tabella PARTITA
    CREATE TABLE partita (
    id BIGSERIAL PRIMARY KEY,
    data_ora TIMESTAMP NOT NULL,
    luogo VARCHAR(100) NOT NULL,
    goals_home INT DEFAULT 0,
    goals_away INT DEFAULT 0,
    stato VARCHAR(20) NOT NULL, -- es. 'SCHEDULED', 'PLAYED'
    torneo_id BIGINT NOT NULL REFERENCES torneo(id) ON DELETE CASCADE,
    squadra_home_id BIGINT NOT NULL REFERENCES squadra(id) ON DELETE RESTRICT,
    squadra_away_id BIGINT NOT NULL REFERENCES squadra(id) ON DELETE RESTRICT,
    arbitro_id BIGINT REFERENCES arbitro(id) ON DELETE SET NULL,
    CONSTRAINT chk_squadre_diverse CHECK (squadra_home_id <> squadron_away_id)
);


-- 7. Tabella COMMENTO (Necessaria per le funzionalità degli utenti registrati)
CREATE TABLE commento (
    id BIGSERIAL PRIMARY KEY,
    testo TEXT NOT NULL,
    data_creazione TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    utente_id BIGINT NOT NULL REFERENCES utente(id) ON DELETE CASCADE,
    partita_id BIGINT NOT NULL REFERENCES partita(id) ON DELETE CASCADE
);


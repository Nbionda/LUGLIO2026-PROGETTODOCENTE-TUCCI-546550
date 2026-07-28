package com.partitadicalcio.java;



import com.partitadicalcio.java.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    /**
     * Recupera un utente partendo dal suo username.
     * Questo metodo è fondamentale per Spring Security durante la fase di login
     * per verificare se le credenziali inserite esistono nel database PostgreSQL.
     */
    Optional<Utente> findByUsername(String username);
}

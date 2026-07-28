package com.partitadicalcio.java;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartitaRepository extends JpaRepository<Partita, Long> {

    /**
     * Recupera le partite di un torneo.
     * Usiamo una query JPQL personalizzata con JOIN FETCH per caricare immediatamente 
     * le squadre collegate (squadraHome e squadraAway), risolvendo il problema delle N+1 query.
     */
    @Query("SELECT p FROM Partita p JOIN FETCH p.squadraHome JOIN FETCH p.squadraAway WHERE p.torneo.id = :torneoId")
    List<Partita> findByTorneoId(@Param("torneoId") Long torneoId);

    /**
     * Recupera il dettaglio completo di una singola partita caricando in un'unica 
     * transazione sia le squadre che l'arbitro associato.
     */
    @Query("SELECT p FROM Partita p JOIN FETCH p.squadraHome JOIN FETCH p.squadraAway LEFT JOIN FETCH p.arbitro WHERE p.id = :id")
    Optional<Partita> findByIdWithDetails(@Param("id") Long id);
}

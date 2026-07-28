package com.partitadicalcio.java;



import com.partitadicalcio.java.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Long> {

    /**
     * Risolve il problema delle N+1 query (Richiesta Punto 8.2 dell'esame).
     * Carica il torneo e la sua collezione di partite collegate in un'unica 
     * operazione di lettura dal database PostgreSQL.
     */
    @Query("SELECT t FROM Torneo t LEFT JOIN FETCH t.partite WHERE t.id = :id")
    Optional<Torneo> findElementByIdFetchPartite(@Param("id") Long id);
}

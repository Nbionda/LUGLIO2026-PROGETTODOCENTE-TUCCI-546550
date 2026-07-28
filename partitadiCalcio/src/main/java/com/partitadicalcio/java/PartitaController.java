package com.partitadicalcio.java;


import com.partitadicalcio.java.Partita;
import com.partitadicalcio.java.PartitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partite")
public class PartitaController {

    @Autowired
    private PartitaRepository partitaRepository;

    /**
     * ENDPOINT PUBBLICO (Richiesto dal Punto 4.1 e consumato da React nel Punto 9)
     * Recupera tutte le partite associate a un determinato torneo.
     * Accessibile liberamente senza autenticazione (permitAll).
     */
    @GetMapping("/torneo/{torneoId}")
    public List<Partita> getPartiteByTorneo(@PathVariable Long torneoId) {
        return partitaRepository.findByTorneoId(torneoId);
    }

    /**
     * ENDPOINT PUBBLICO
     * Recupera il dettaglio ottimizzato di una singola partita usando la query JOIN FETCH (Punto 8.2).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Partita> getPartitaDettaglio(@PathVariable Long id) {
        return partitaRepository.findByIdWithDetails(id)
                .map(partita -> new ResponseEntity<>(partita, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * ENDPOINT PROTETTO - ACCESSIBILE SOLO DA ADMIN (Punto 4.3 e Punto 5)
     * Permette di inserire o aggiornare il risultato di una partita.
     * Spring Security intercetta questa chiamata POST e risponde con 403 Forbidden se l'utente non è ADMIN.
     */
    @PostMapping("/{id}/risultato")
    public ResponseEntity<Partita> aggiornaRisultato(
            @PathVariable Long id, 
            @RequestParam Integer goalsHome, 
            @RequestParam Integer goalsAway) {
        
        return partitaRepository.findById(id).map(partita -> {
            partita.setGoalsHome(goalsHome);
            partita.setGoalsAway(goalsAway);
            partita.setStato("PLAYED"); // Cambia lo stato per farlo vedere terminato su React
            Partita partitaAggiornata = partitaRepository.save(partita);
            return new ResponseEntity<>(partitaAggiornata, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}





package com.partitadicalcio.java;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartitaService {

    @Autowired
    private PartitaRepository partitaRepository;

    /**
     * Recupera le partite di un torneo sfruttando l'ottimizzazione JOIN FETCH.
     * @Transactional(readOnly = true) garantisce una transazione ottimizzata in sola lettura.
     */
    @Transactional(readOnly = true)
    public List<Partita> findByTorneoId(Long torneoId) {
        return partitaRepository.findByTorneoId(torneoId);
    }

    /**
     * Recupera il dettaglio completo di una partita.
     */
    @Transactional(readOnly = true)
    public Optional<Partita> findByIdWithDetails(Long id) {
        return partitaRepository.findByIdWithDetails(id);
    }

    /**
     * Aggiorna il risultato di una partita esistente.
     * L'annotazione @Transactional assicura che l'operazione di update sul database 
     * avvenga in modo atomico (tutto o niente).
     */
    @Transactional
    public Optional<Partita> aggiornaRisultato(Long id, Integer goalsHome, Integer goalsAway) {
        return partitaRepository.findById(id).map(partita -> {
            partita.setGoalsHome(goalsHome);
            partita.setGoalsAway(goalsAway);
            partita.setStato("PLAYED"); // Imposta lo stato su terminata per il widget React
            return partitaRepository.save(partita);
        });
    }
}
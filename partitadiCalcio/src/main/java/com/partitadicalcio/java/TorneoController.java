package com.partitadicalcio.java;

import com.partitadicalcio.java.Torneo;
import com.partitadicalcio.java.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TorneoController {

    @Autowired
    private TorneoRepository torneoRepository;

    /**
     * Mostra la lista di tutti i tornei disponibili (utilizzata nella Home Page).
     */
    @GetMapping("/tornei")
    public String getAllTornei(Model model) {
        model.addAttribute("tornei", torneoRepository.findAll());
        return "index"; // Cerca src/main/resources/templates/index.html
    }

    /**
     * Mostra i dettagli di un singolo torneo specifico.
     * Utilizza un metodo personalizzato del repository per caricare il torneo 
     * e le sue partite associate in un'unica operazione ottimizzata.
     */
    @GetMapping("/tornei/{id}")
    public String getTorneoDettagli(@PathVariable Long id, Model model) {
        // Recuperiamo il torneo usando il metodo ottimizzato con JOIN FETCH 
        Torneo torneo = torneoRepository.findElementByIdFetchPartite(id)
                .orElseThrow(() -> new RuntimeException("Torneo non trovato per l'ID: " + id));

        model.addAttribute("torneo", torneo);
        return "dettagli-torneo"; // Cerca src/main/resources/templates/dettagli-torneo.html
    }
}

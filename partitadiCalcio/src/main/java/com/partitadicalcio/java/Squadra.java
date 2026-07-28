package com.partitadicalcio.java;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "squadra")
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "anno_fondazione", nullable = false)
    private Integer annoFondazione;

    @Column(nullable = false, length = 50)
    private String citta;

    // Relazione uno-a-molti bidirezionale: una squadra ha più giocatori.
    // Il "mappedBy" indica che il vincolo e il proprietario della relazione è il campo "squadra" nella classe Giocatore.
    // cascade = CascadeType.ALL assicura che se cancelliamo una squadra, le azioni si ripercuotono sui giocatori (in base alle logiche di business).
    // fetch = FetchType.LAZY evita di caricare l'intera rosa ogni volta che cerchiamo solo i dati generali di una squadra.
    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Giocatore> giocatori;

    // Relazione molti-a-molti: una squadra partecipa a più tornei.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "torneo_squadra",
        joinColumns = @JoinColumn(name = "squadra_id"),
        inverseJoinColumns = @JoinColumn(name = "torneo_id")
    )
    private List<Torneo> tornei;

    // --- COSTRUTTORI ---
    
    public Squadra() {
        this.giocatori = new ArrayList<>();
        this.tornei = new ArrayList<>();
    }

    public Squadra(String nome, Integer annoFondazione, String citta) {
        this();
        this.nome = nome;
        this.annoFondazione = annoFondazione;
        this.citta = citta;
    }

    // --- METODI UTILITY PER LA RELAZIONE BIDIREZIONALE ---
    // Questi metodi sono fondamentali in JPA per mantenere sincronizzati i due lati della relazione in memoria
    public void addGiocatore(Giocatore giocatore) {
        this.giocatori.add(giocatore);
        giocatore.setSquadra(this);
    }

    public void removeGiocatore(Giocatore giocatore) {
        this.giocatori.remove(giocatore);
        giocatore.setSquadra(null);
    }

    // --- GETTER E SETTER ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnnoFondazione() {
        return annoFondazione;
    }

    public void setAnnoFondazione(Integer annoFondazione) {
        this.annoFondazione = annoFondazione;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    public List<Torneo> getTornei() {
        return tornei;
    }

    public void setTornei(List<Torneo> tornei) {
        this.tornei = tornei;
    }

    // --- EQUALS E HASHCODE ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squadra squadra = (Squadra) o;
        return Objects.equals(id, squadra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- TO STRING ---

    @Override
    public String toString() {
        return "Squadra{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
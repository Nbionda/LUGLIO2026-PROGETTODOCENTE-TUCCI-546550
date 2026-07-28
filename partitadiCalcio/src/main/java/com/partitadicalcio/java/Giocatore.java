package com.partitadicalcio.java;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "giocatore")
public class Giocatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @Column(nullable = false, length = 30)
    private String ruolo;

    private Integer altezza; // Espressa in centimetri

    // Relazione molti-a-uno: molti giocatori appartengono a una sola squadra
    // Il caricamento è impostato su LAZY per favorire le prestazioni, come richiesto dal punto 8
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "squadra_id", nullable = false)
    private Squadra squadra;

    // --- COSTRUTTORI ---
    
    public Giocatore() {
    }

    public Giocatore(String nome, String cognome, LocalDate dataNascita, String ruolo, Integer altezza) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.ruolo = ruolo;
        this.altezza = altezza;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Integer getAltezza() {
        return altezza;
    }

    public void setAltezza(Integer altezza) {
        this.altezza = altezza;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    // --- EQUALS E HASHCODE ---
    // Essenziale per Hibernate basarsi sull'ID surrogato o su attributi business univoci

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giocatore giocatore = (Giocatore) o;
        return Objects.equals(id, giocatore.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- TO STRING ---

    @Override
    public String toString() {
        return "Giocatore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }
}

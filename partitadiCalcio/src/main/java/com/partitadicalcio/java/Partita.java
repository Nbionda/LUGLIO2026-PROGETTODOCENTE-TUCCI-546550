package com.partitadicalcio.java;


import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partita")
public class Partita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataOra;

    // Stato della partita: es. "SCHEDULED", "PLAYED" (utilizzato nel widget React)
    @Column(nullable = false)
    private String stato;

    private Integer goalsHome;
    private Integer goalsAway;

    // RELAZIONI MOLTI-A-UNO (Punto 8: Configurate esplicitamente su LAZY)
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "squadra_home_id", nullable = false)
    private Squadra squadraHome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "squadra_away_id", nullable = false)
    private Squadra squadraAway;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbitro_id") // Può essere nullo se non ancora assegnato
    private Arbitro arbitro;

    // ==========================================================================
    // COSTRUTTORI
    // ==========================================================================
    public Partita() {
        this.stato = "SCHEDULED"; // Stato di default
    }

    // ==========================================================================
    // GETTER E SETTER
    // ==========================================================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Integer getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(Integer goalsHome) {
        this.goalsHome = goalsHome;
    }

    public Integer getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(Integer goalsAway) {
        this.goalsAway = goalsAway;
    }

    public Squadra getSquadraHome() {
        return squadraHome;
    }

    public void setSquadraHome(Squadra squadraHome) {
        this.squadraHome = squadraHome;
    }

    public Squadra getSquadraAway() {
        return squadraAway;
    }

    public void setSquadraAway(Squadra squadraAway) {
        this.squadraAway = squadraAway;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    // ==========================================================================
    // EQUALS E HASHCODE (Best Practice per JPA)
    // ==========================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partita partita = (Partita) o;
        return Objects.equals(id, partita.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
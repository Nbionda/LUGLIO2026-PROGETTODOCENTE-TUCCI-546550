package com.partitadicalcio.java;


import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "torneo")
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer anno;

    @Column(length = 500)
    private String descrizione;

    // RELAZIONE UNO-A-MOLTI (Punto 8: Configurata esplicitamente su LAZY)
    // mappedBy indica che il vincolo di chiave esterna è gestito dalla colonna "torneo_id" nella tabella Partita
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Partita> partite;

    // ==========================================================================
    // COSTRUTTORI
    // ==========================================================================
    public Torneo() {
        this.partite = new ArrayList<>();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Partita> getPartite() {
        return partite;
    }

    public void setPartite(List<Partita> partite) {
        this.partite = partite;
    }

    // ==========================================================================
    // EQUALS E HASHCODE
    // ==========================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Torneo torneo = (Torneo) o;
        return Objects.equals(id, torneo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
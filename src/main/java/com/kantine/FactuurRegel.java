package com.kantine;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuur_regel")
public class FactuurRegel implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur_id", nullable = false)
    private Factuur factuur;

    @Transient
    private Artikel artikel;

    public FactuurRegel() {}

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
    }

    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        return "naam: " + artikel.getNaam() + " prijs: " + artikel.getPrijs();
    }
}
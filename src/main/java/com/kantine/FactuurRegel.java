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

    @Column(name="artikel_naam")
    private String artikelNaam;

    @Column(name="artikel_prijs")
    private double artikelPrijs;

    public FactuurRegel() {}

    public FactuurRegel(Factuur factuur, Artikel artikel) {
        this.factuur = factuur;
        this.artikel = artikel;
        this.artikelNaam = artikel.getNaam();
        this.artikelPrijs = artikel.getPrijs();
    }

    /**
     * @return een printbare factuurregel
     */
    public String toString() {
        return "Artikel: " + artikel.getNaam() + " prijs: " + artikel.getPrijs();
    }
}
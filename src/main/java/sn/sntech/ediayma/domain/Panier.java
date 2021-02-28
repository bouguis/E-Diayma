package sn.sntech.ediayma.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Panier.
 */
@Entity
@Table(name = "panier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_panier")
    private String codePanier;

    @ManyToOne
    @JsonIgnoreProperties(value = "paniers", allowSetters = true)
    private LigneCommande ligneCommande;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePanier() {
        return codePanier;
    }

    public Panier codePanier(String codePanier) {
        this.codePanier = codePanier;
        return this;
    }

    public void setCodePanier(String codePanier) {
        this.codePanier = codePanier;
    }

    public LigneCommande getLigneCommande() {
        return ligneCommande;
    }

    public Panier ligneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
        return this;
    }

    public void setLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Panier)) {
            return false;
        }
        return id != null && id.equals(((Panier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Panier{" +
            "id=" + getId() +
            ", codePanier='" + getCodePanier() + "'" +
            "}";
    }
}

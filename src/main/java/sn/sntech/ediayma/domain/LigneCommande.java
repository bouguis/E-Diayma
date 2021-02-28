package sn.sntech.ediayma.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A LigneCommande.
 */
@Entity
@Table(name = "ligne_commande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LigneCommande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "quantite_commande")
    private Integer quantiteCommande;

    @Column(name = "prix_total", precision = 21, scale = 2)
    private BigDecimal prixTotal;

    @OneToMany(mappedBy = "ligneCommande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Panier> paniers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ligneCommandes", allowSetters = true)
    private Commande commande;

    @ManyToOne
    @JsonIgnoreProperties(value = "ligneCommandes", allowSetters = true)
    private Article article;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantiteCommande() {
        return quantiteCommande;
    }

    public LigneCommande quantiteCommande(Integer quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
        return this;
    }

    public void setQuantiteCommande(Integer quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public LigneCommande prixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
        return this;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Set<Panier> getPaniers() {
        return paniers;
    }

    public LigneCommande paniers(Set<Panier> paniers) {
        this.paniers = paniers;
        return this;
    }

    public LigneCommande addPanier(Panier panier) {
        this.paniers.add(panier);
        panier.setLigneCommande(this);
        return this;
    }

    public LigneCommande removePanier(Panier panier) {
        this.paniers.remove(panier);
        panier.setLigneCommande(null);
        return this;
    }

    public void setPaniers(Set<Panier> paniers) {
        this.paniers = paniers;
    }

    public Commande getCommande() {
        return commande;
    }

    public LigneCommande commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Article getArticle() {
        return article;
    }

    public LigneCommande article(Article article) {
        this.article = article;
        return this;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneCommande)) {
            return false;
        }
        return id != null && id.equals(((LigneCommande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneCommande{" +
            "id=" + getId() +
            ", quantiteCommande=" + getQuantiteCommande() +
            ", prixTotal=" + getPrixTotal() +
            "}";
    }
}

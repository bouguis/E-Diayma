package sn.sntech.ediayma.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ref_commande")
    private String refCommande;

    @Column(name = "etat_commande")
    private String etatCommande;

    @Column(name = "destination")
    private String destination;

    @OneToMany(mappedBy = "commande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LigneCommande> ligneCommandes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "commande_article",
               joinColumns = @JoinColumn(name = "commande_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"))
    private Set<Article> articles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "commandes", allowSetters = true)
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefCommande() {
        return refCommande;
    }

    public Commande refCommande(String refCommande) {
        this.refCommande = refCommande;
        return this;
    }

    public void setRefCommande(String refCommande) {
        this.refCommande = refCommande;
    }

    public String getEtatCommande() {
        return etatCommande;
    }

    public Commande etatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
        return this;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    public String getDestination() {
        return destination;
    }

    public Commande destination(String destination) {
        this.destination = destination;
        return this;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Set<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public Commande ligneCommandes(Set<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
        return this;
    }

    public Commande addLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommandes.add(ligneCommande);
        ligneCommande.setCommande(this);
        return this;
    }

    public Commande removeLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommandes.remove(ligneCommande);
        ligneCommande.setCommande(null);
        return this;
    }

    public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public Commande articles(Set<Article> articles) {
        this.articles = articles;
        return this;
    }

    public Commande addArticle(Article article) {
        this.articles.add(article);
        article.getCommandes().add(this);
        return this;
    }

    public Commande removeArticle(Article article) {
        this.articles.remove(article);
        article.getCommandes().remove(this);
        return this;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public Commande userExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
        return this;
    }

    public void setUserExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commande{" +
            "id=" + getId() +
            ", refCommande='" + getRefCommande() + "'" +
            ", etatCommande='" + getEtatCommande() + "'" +
            ", destination='" + getDestination() + "'" +
            "}";
    }
}

package sn.sntech.ediayma.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ref_article")
    private String refArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "description")
    private String description;

    @Column(name = "prix", precision = 21, scale = 2)
    private BigDecimal prix;

    @Column(name = "quantite_stocke")
    private Long quantiteStocke;

    @Column(name = "image")
    private String image;

    @Column(name = "origine")
    private String origine;

    @Column(name = "disponiblite")
    private Boolean disponiblite;

    @Column(name = "selectionne")
    private Boolean selectionne;

    @OneToMany(mappedBy = "article")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<LigneCommande> ligneCommandes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "articles", allowSetters = true)
    private Categorie categorie;

    @ManyToMany(mappedBy = "articles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Commande> commandes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public Article refArticle(String refArticle) {
        this.refArticle = refArticle;
        return this;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public Article designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public Article description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public Article prix(BigDecimal prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Long getQuantiteStocke() {
        return quantiteStocke;
    }

    public Article quantiteStocke(Long quantiteStocke) {
        this.quantiteStocke = quantiteStocke;
        return this;
    }

    public void setQuantiteStocke(Long quantiteStocke) {
        this.quantiteStocke = quantiteStocke;
    }

    public String getImage() {
        return image;
    }

    public Article image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrigine() {
        return origine;
    }

    public Article origine(String origine) {
        this.origine = origine;
        return this;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public Boolean isDisponiblite() {
        return disponiblite;
    }

    public Article disponiblite(Boolean disponiblite) {
        this.disponiblite = disponiblite;
        return this;
    }

    public void setDisponiblite(Boolean disponiblite) {
        this.disponiblite = disponiblite;
    }

    public Boolean isSelectionne() {
        return selectionne;
    }

    public Article selectionne(Boolean selectionne) {
        this.selectionne = selectionne;
        return this;
    }

    public void setSelectionne(Boolean selectionne) {
        this.selectionne = selectionne;
    }

    public Set<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public Article ligneCommandes(Set<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
        return this;
    }

    public Article addLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommandes.add(ligneCommande);
        ligneCommande.setArticle(this);
        return this;
    }

    public Article removeLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommandes.remove(ligneCommande);
        ligneCommande.setArticle(null);
        return this;
    }

    public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Article categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public Article commandes(Set<Commande> commandes) {
        this.commandes = commandes;
        return this;
    }

    public Article addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.getArticles().add(this);
        return this;
    }

    public Article removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.getArticles().remove(this);
        return this;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        return id != null && id.equals(((Article) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Article{" +
            "id=" + getId() +
            ", refArticle='" + getRefArticle() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", description='" + getDescription() + "'" +
            ", prix=" + getPrix() +
            ", quantiteStocke=" + getQuantiteStocke() +
            ", image='" + getImage() + "'" +
            ", origine='" + getOrigine() + "'" +
            ", disponiblite='" + isDisponiblite() + "'" +
            ", selectionne='" + isSelectionne() + "'" +
            "}";
    }
}

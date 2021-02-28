package sn.sntech.ediayma.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link sn.sntech.ediayma.domain.Article} entity.
 */
public class ArticleDTO implements Serializable {
    
    private Long id;

    private String refArticle;

    private String designation;

    private String description;

    private BigDecimal prix;

    private Long quantiteStocke;

    private String image;

    private String origine;

    private Boolean disponiblite;

    private Boolean selectionne;


    private Long categorieId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Long getQuantiteStocke() {
        return quantiteStocke;
    }

    public void setQuantiteStocke(Long quantiteStocke) {
        this.quantiteStocke = quantiteStocke;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public Boolean isDisponiblite() {
        return disponiblite;
    }

    public void setDisponiblite(Boolean disponiblite) {
        this.disponiblite = disponiblite;
    }

    public Boolean isSelectionne() {
        return selectionne;
    }

    public void setSelectionne(Boolean selectionne) {
        this.selectionne = selectionne;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArticleDTO)) {
            return false;
        }

        return id != null && id.equals(((ArticleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArticleDTO{" +
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
            ", categorieId=" + getCategorieId() +
            "}";
    }
}

package sn.sntech.ediayma.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link sn.sntech.ediayma.domain.LigneCommande} entity.
 */
public class LigneCommandeDTO implements Serializable {
    
    private Long id;

    private Integer quantiteCommande;

    private BigDecimal prixTotal;


    private Long commandeId;

    private Long articleId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(Integer quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneCommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((LigneCommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneCommandeDTO{" +
            "id=" + getId() +
            ", quantiteCommande=" + getQuantiteCommande() +
            ", prixTotal=" + getPrixTotal() +
            ", commandeId=" + getCommandeId() +
            ", articleId=" + getArticleId() +
            "}";
    }
}

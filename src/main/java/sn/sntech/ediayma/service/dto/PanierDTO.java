package sn.sntech.ediayma.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link sn.sntech.ediayma.domain.Panier} entity.
 */
public class PanierDTO implements Serializable {
    
    private Long id;

    private String codePanier;


    private Long ligneCommandeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePanier() {
        return codePanier;
    }

    public void setCodePanier(String codePanier) {
        this.codePanier = codePanier;
    }

    public Long getLigneCommandeId() {
        return ligneCommandeId;
    }

    public void setLigneCommandeId(Long ligneCommandeId) {
        this.ligneCommandeId = ligneCommandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PanierDTO)) {
            return false;
        }

        return id != null && id.equals(((PanierDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PanierDTO{" +
            "id=" + getId() +
            ", codePanier='" + getCodePanier() + "'" +
            ", ligneCommandeId=" + getLigneCommandeId() +
            "}";
    }
}

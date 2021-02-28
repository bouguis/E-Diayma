package sn.sntech.ediayma.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link sn.sntech.ediayma.domain.Categorie} entity.
 */
public class CategorieDTO implements Serializable {
    
    private Long id;

    private String code;

    private String libelle;

    private String famille;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategorieDTO)) {
            return false;
        }

        return id != null && id.equals(((CategorieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategorieDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", famille='" + getFamille() + "'" +
            "}";
    }
}

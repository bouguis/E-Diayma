package sn.sntech.ediayma.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link sn.sntech.ediayma.domain.Commande} entity.
 */
public class CommandeDTO implements Serializable {
    
    private Long id;

    private String refCommande;

    private String etatCommande;

    private String destination;

    private Set<ArticleDTO> articles = new HashSet<>();

    private Long userExtraId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefCommande() {
        return refCommande;
    }

    public void setRefCommande(String refCommande) {
        this.refCommande = refCommande;
    }

    public String getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Set<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleDTO> articles) {
        this.articles = articles;
    }

    public Long getUserExtraId() {
        return userExtraId;
    }

    public void setUserExtraId(Long userExtraId) {
        this.userExtraId = userExtraId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((CommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeDTO{" +
            "id=" + getId() +
            ", refCommande='" + getRefCommande() + "'" +
            ", etatCommande='" + getEtatCommande() + "'" +
            ", destination='" + getDestination() + "'" +
            ", articles='" + getArticles() + "'" +
            ", userExtraId=" + getUserExtraId() +
            "}";
    }
}

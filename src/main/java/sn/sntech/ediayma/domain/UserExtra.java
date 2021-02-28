package sn.sntech.ediayma.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A UserExtra.
 */
@Entity
@Table(name = "user_extra")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "localisation")
    private String localisation;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "userExtra")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Commande> commandes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public UserExtra localisation(String localisation) {
        this.localisation = localisation;
        return this;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public User getUser() {
        return user;
    }

    public UserExtra user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public UserExtra commandes(Set<Commande> commandes) {
        this.commandes = commandes;
        return this;
    }

    public UserExtra addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setUserExtra(this);
        return this;
    }

    public UserExtra removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setUserExtra(null);
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
        if (!(o instanceof UserExtra)) {
            return false;
        }
        return id != null && id.equals(((UserExtra) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserExtra{" +
            "id=" + getId() +
            ", localisation='" + getLocalisation() + "'" +
            "}";
    }
}

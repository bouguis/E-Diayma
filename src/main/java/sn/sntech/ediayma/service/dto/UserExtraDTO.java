package sn.sntech.ediayma.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link sn.sntech.ediayma.domain.UserExtra} entity.
 */
public class UserExtraDTO implements Serializable {
    
    private Long id;

    private String localisation;


    private Long userId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserExtraDTO)) {
            return false;
        }

        return id != null && id.equals(((UserExtraDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserExtraDTO{" +
            "id=" + getId() +
            ", localisation='" + getLocalisation() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}

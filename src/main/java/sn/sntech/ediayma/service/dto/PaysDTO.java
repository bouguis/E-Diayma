package sn.sntech.ediayma.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link sn.sntech.ediayma.domain.Pays} entity.
 */
public class PaysDTO implements Serializable {
    
    private Long id;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaysDTO)) {
            return false;
        }

        return id != null && id.equals(((PaysDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaysDTO{" +
            "id=" + getId() +
            "}";
    }
}

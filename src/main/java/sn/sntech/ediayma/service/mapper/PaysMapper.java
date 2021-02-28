package sn.sntech.ediayma.service.mapper;


import sn.sntech.ediayma.domain.*;
import sn.sntech.ediayma.service.dto.PaysDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pays} and its DTO {@link PaysDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaysMapper extends EntityMapper<PaysDTO, Pays> {



    default Pays fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pays pays = new Pays();
        pays.setId(id);
        return pays;
    }
}

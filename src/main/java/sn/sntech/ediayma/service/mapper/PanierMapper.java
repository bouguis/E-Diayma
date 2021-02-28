package sn.sntech.ediayma.service.mapper;


import sn.sntech.ediayma.domain.*;
import sn.sntech.ediayma.service.dto.PanierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Panier} and its DTO {@link PanierDTO}.
 */
@Mapper(componentModel = "spring", uses = {LigneCommandeMapper.class})
public interface PanierMapper extends EntityMapper<PanierDTO, Panier> {

    @Mapping(source = "ligneCommande.id", target = "ligneCommandeId")
    PanierDTO toDto(Panier panier);

    @Mapping(source = "ligneCommandeId", target = "ligneCommande")
    Panier toEntity(PanierDTO panierDTO);

    default Panier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Panier panier = new Panier();
        panier.setId(id);
        return panier;
    }
}

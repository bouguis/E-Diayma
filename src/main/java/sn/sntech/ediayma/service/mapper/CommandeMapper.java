package sn.sntech.ediayma.service.mapper;


import sn.sntech.ediayma.domain.*;
import sn.sntech.ediayma.service.dto.CommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commande} and its DTO {@link CommandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ArticleMapper.class, UserExtraMapper.class})
public interface CommandeMapper extends EntityMapper<CommandeDTO, Commande> {

    @Mapping(source = "userExtra.id", target = "userExtraId")
    CommandeDTO toDto(Commande commande);

    @Mapping(target = "ligneCommandes", ignore = true)
    @Mapping(target = "removeLigneCommande", ignore = true)
    @Mapping(target = "removeArticle", ignore = true)
    @Mapping(source = "userExtraId", target = "userExtra")
    Commande toEntity(CommandeDTO commandeDTO);

    default Commande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setId(id);
        return commande;
    }
}

package sn.sntech.ediayma.service.mapper;


import sn.sntech.ediayma.domain.*;
import sn.sntech.ediayma.service.dto.LigneCommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LigneCommande} and its DTO {@link LigneCommandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommandeMapper.class, ArticleMapper.class})
public interface LigneCommandeMapper extends EntityMapper<LigneCommandeDTO, LigneCommande> {

    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "article.id", target = "articleId")
    LigneCommandeDTO toDto(LigneCommande ligneCommande);

    @Mapping(target = "paniers", ignore = true)
    @Mapping(target = "removePanier", ignore = true)
    @Mapping(source = "commandeId", target = "commande")
    @Mapping(source = "articleId", target = "article")
    LigneCommande toEntity(LigneCommandeDTO ligneCommandeDTO);

    default LigneCommande fromId(Long id) {
        if (id == null) {
            return null;
        }
        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setId(id);
        return ligneCommande;
    }
}

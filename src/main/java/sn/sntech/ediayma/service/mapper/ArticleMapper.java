package sn.sntech.ediayma.service.mapper;


import sn.sntech.ediayma.domain.*;
import sn.sntech.ediayma.service.dto.ArticleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Article} and its DTO {@link ArticleDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategorieMapper.class})
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

    @Mapping(source = "categorie.id", target = "categorieId")
    ArticleDTO toDto(Article article);

    @Mapping(target = "ligneCommandes", ignore = true)
    @Mapping(target = "removeLigneCommande", ignore = true)
    @Mapping(source = "categorieId", target = "categorie")
    @Mapping(target = "commandes", ignore = true)
    @Mapping(target = "removeCommande", ignore = true)
    Article toEntity(ArticleDTO articleDTO);

    default Article fromId(Long id) {
        if (id == null) {
            return null;
        }
        Article article = new Article();
        article.setId(id);
        return article;
    }
}

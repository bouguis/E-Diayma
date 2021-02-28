package sn.sntech.ediayma.service.mapper;


import sn.sntech.ediayma.domain.*;
import sn.sntech.ediayma.service.dto.UserExtraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserExtra} and its DTO {@link UserExtraDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserExtraMapper extends EntityMapper<UserExtraDTO, UserExtra> {

    @Mapping(source = "user.id", target = "userId")
    UserExtraDTO toDto(UserExtra userExtra);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "commandes", ignore = true)
    @Mapping(target = "removeCommande", ignore = true)
    UserExtra toEntity(UserExtraDTO userExtraDTO);

    default UserExtra fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserExtra userExtra = new UserExtra();
        userExtra.setId(id);
        return userExtra;
    }
}

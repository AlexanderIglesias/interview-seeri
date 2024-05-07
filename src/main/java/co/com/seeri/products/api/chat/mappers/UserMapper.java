package co.com.seeri.products.api.chat.mappers;

import co.com.seeri.products.api.chat.dto.UserDTO;
import co.com.seeri.products.api.chat.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(implementationName = "<CLASS_NAME>MapStructImpl", componentModel = "spring")
public interface UserMapper {

    @Named("mapUser")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDTO mapUserToUserDTO(User product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User mapUserDTOToUser(UserDTO userDTO);
}

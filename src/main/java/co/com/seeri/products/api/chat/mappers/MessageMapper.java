package co.com.seeri.products.api.chat.mappers;

import co.com.seeri.products.api.chat.dto.MessageDTO;
import co.com.seeri.products.api.chat.entities.Message;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(implementationName = "<CLASS_NAME>MapStructImpl", componentModel = "spring")
public interface MessageMapper {

    @Named("mapMessage")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MessageDTO mapMessageToMessageDTO(Message message);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message mapMessageDTOToMessage(MessageDTO messagesDTO);
}

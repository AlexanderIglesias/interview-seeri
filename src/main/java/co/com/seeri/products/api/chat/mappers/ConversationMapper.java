package co.com.seeri.products.api.chat.mappers;

import co.com.seeri.products.api.chat.dto.ConversationDTO;
import co.com.seeri.products.api.chat.entities.Conversation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(implementationName = "<CLASS_NAME>MapStructImpl", componentModel = "spring")
public interface ConversationMapper {

    @Named("mapConversation")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ConversationDTO mapConversationToConversationDTO(Conversation conversation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Conversation mapConversationDTOToConversation(ConversationDTO conversationDTO);

}

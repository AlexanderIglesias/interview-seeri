package co.com.seeri.products.api.chat.services;

import co.com.seeri.products.api.chat.dto.ConversationDTO;

import java.util.List;

public interface ConversationServices {

    ConversationDTO addConversation(ConversationDTO conversationDTO);

    void addUserToConversation(Long conversationId, Long userId);

    List<ConversationDTO> getAllConversations();

    List<ConversationDTO> getConversationsByUserId(Long userId);
}

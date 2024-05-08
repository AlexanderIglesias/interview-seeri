package co.com.seeri.products.api.chat.services;

import co.com.seeri.products.api.chat.dto.MessageDTO;
import co.com.seeri.products.api.chat.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageServices {

    MessageDTO addMessageToConversation(Long conversationId, MessageDTO messagesDTO) throws Exception;

    Page<Message> getAllMessages(Pageable pageable, Long conversationId);

}

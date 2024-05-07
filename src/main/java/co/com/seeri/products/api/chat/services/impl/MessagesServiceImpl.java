package co.com.seeri.products.api.chat.services.impl;

import co.com.seeri.products.api.chat.dto.MessageDTO;
import co.com.seeri.products.api.chat.entities.Conversation;
import co.com.seeri.products.api.chat.entities.Message;
import co.com.seeri.products.api.chat.mappers.MessageMapper;
import co.com.seeri.products.api.chat.repositories.ConversationRepository;
import co.com.seeri.products.api.chat.repositories.MessageRepository;
import co.com.seeri.products.api.chat.services.MessageServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessagesServiceImpl implements MessageServices {


    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final MessageMapper messageMapper;

    public MessagesServiceImpl(MessageRepository messageRepository, ConversationRepository conversationRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageDTO addMessageToConversation(Long conversationId, MessageDTO messageDTO) {

        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new EntityNotFoundException("Conversation not found"));

        if (conversation != null) {
            messageDTO.setConversation(conversation);
            Message message = messageRepository.saveAndFlush(messageMapper.mapMessageDTOToMessage(messageDTO));
            return messageMapper.mapMessageToMessageDTO(message);
        }
        return null;
    }

    @Override
    public Page<Message> getAllMessages(Pageable pageable, Long conversationId) {
        return messageRepository.findAllByConversationId(pageable, conversationId);
    }

}

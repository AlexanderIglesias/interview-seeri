package co.com.seeri.products.api.chat.services.impl;

import co.com.seeri.products.api.chat.dto.ConversationDTO;
import co.com.seeri.products.api.chat.dto.MessageDTO;
import co.com.seeri.products.api.chat.entities.Conversation;
import co.com.seeri.products.api.chat.entities.Message;
import co.com.seeri.products.api.chat.mappers.ConversationMapper;
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
    private final ConversationMapper conversationMapper;

    public MessagesServiceImpl(MessageRepository messageRepository, ConversationRepository conversationRepository,
                               MessageMapper messageMapper, ConversationMapper conversationMapper) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.messageMapper = messageMapper;
        this.conversationMapper = conversationMapper;
    }

    @Override
    public MessageDTO addMessageToConversation(Long conversationId, MessageDTO messageDTO) throws EntityNotFoundException {

        try {
            Conversation conversation = conversationRepository.findById(conversationId)
                    .orElseThrow(() -> new EntityNotFoundException("Conversation not found"));

            if (conversation != null) {
                ConversationDTO conversationDTO = conversationMapper.mapConversationToConversationDTO(conversation);
                messageDTO.setConversation(conversationDTO);
                Message message = messageRepository.saveAndFlush(messageMapper.mapMessageDTOToMessage(messageDTO));
                return messageMapper.mapMessageToMessageDTO(message);
            }

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Conversation with id " + conversationId + " not found");
        }

        return null;
    }

    @Override
    public Page<Message> getAllMessages(Pageable pageable, Long conversationId) {
        return messageRepository.findAllByConversationId(pageable, conversationId);
    }

}

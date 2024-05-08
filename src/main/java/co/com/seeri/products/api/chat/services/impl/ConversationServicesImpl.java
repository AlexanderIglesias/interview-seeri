package co.com.seeri.products.api.chat.services.impl;

import co.com.seeri.products.api.chat.dto.ConversationDTO;
import co.com.seeri.products.api.chat.entities.Conversation;
import co.com.seeri.products.api.chat.entities.User;
import co.com.seeri.products.api.chat.mappers.ConversationMapper;
import co.com.seeri.products.api.chat.repositories.ConversationRepository;
import co.com.seeri.products.api.chat.repositories.UserRepository;
import co.com.seeri.products.api.chat.services.ConversationServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationServicesImpl implements ConversationServices {

    private final ConversationRepository conversationRepository;
    private final ConversationMapper conversationMapper;
    private final UserRepository userRepository;

    public ConversationServicesImpl(ConversationRepository conversationRepository, ConversationMapper conversationMapper,
                                    UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.conversationMapper = conversationMapper;
        this.userRepository = userRepository;
    }


    @Override
    public ConversationDTO addConversation(ConversationDTO conversationDTO) {
        Conversation conversation = conversationMapper.mapConversationDTOToConversation(conversationDTO);
        List<Long> ids = new ArrayList<>();
        conversationDTO.getUsers().stream().forEach(user -> ids.add(user.getId()));
        List<User> users = userRepository.findByIdIn(ids);
        conversation.setUsers(users);
        Conversation savedConversation = conversationRepository.save(conversation);
        return conversationMapper.mapConversationToConversationDTO(savedConversation);
    }

    @Override
    public void addUserToConversation(Long conversationId, Long userId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(
                () -> new EntityNotFoundException("Conversation not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        conversation.getUsers().add(user);
        conversationRepository.save(conversation);
    }

    @Override
    public List<ConversationDTO> getAllConversations() {
        List<Conversation> conversations = conversationRepository.findAll();

        if (!conversations.isEmpty()) {
            List<ConversationDTO> dtos = new ArrayList<>();
            for (Conversation conversation : conversations) {
                dtos.add(conversationMapper.mapConversationToConversationDTO(conversation));
            }
            return dtos;
        }

        return new ArrayList<>();
    }

    @Override
    public List<ConversationDTO> getConversationsByUserId(Long userId) {
        List<Conversation> conversations = conversationRepository.findByUsersId(userId);
        if (!conversations.isEmpty()) {
            List<ConversationDTO> dtos = new ArrayList<>();
            for (Conversation conversation : conversations) {
                dtos.add(conversationMapper.mapConversationToConversationDTO(conversation));
                return dtos;
            }
        }
        return null;
    }
}

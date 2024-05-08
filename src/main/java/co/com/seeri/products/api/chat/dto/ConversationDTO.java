package co.com.seeri.products.api.chat.dto;

import lombok.Data;

import java.util.List;

@Data
public class ConversationDTO {
    private Long id;
    private String name;
    private List<UserDTO> users;
    private List<MessageDTO> messages;
}

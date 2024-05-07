package co.com.seeri.products.api.chat.dto;

import co.com.seeri.products.api.chat.entities.Message;
import lombok.Data;

import java.util.List;

@Data
public class ConversationDTO {
    private Long id;
    private String name;
    private List<UserDTO> users;
    private List<Message> messages;
}

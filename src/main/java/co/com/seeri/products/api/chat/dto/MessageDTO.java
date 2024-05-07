package co.com.seeri.products.api.chat.dto;

import co.com.seeri.products.api.chat.entities.Conversation;
import lombok.Data;


@Data
public class MessageDTO {
    private Long id;
    private Long userId;
    ;
    private String text;
    private String multimediaUrl;
    private Conversation conversation;
}

package co.com.seeri.products.api.chat.dto;

import lombok.Data;


@Data
public class MessageDTO {
    private Long id;
    private String text;
    private String multimediaUrl;
    private ConversationDTO conversation;
}

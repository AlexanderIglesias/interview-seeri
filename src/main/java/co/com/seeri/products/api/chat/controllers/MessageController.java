package co.com.seeri.products.api.chat.controllers;

import co.com.seeri.products.api.chat.dto.MessageDTO;
import co.com.seeri.products.api.chat.entities.Message;
import co.com.seeri.products.api.chat.services.MessageServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {


    private final MessageServices messageService;

    @Autowired
    public MessageController(MessageServices messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/{conversationId}")
    @Operation(summary = "add message to conversations")
    public ResponseEntity<MessageDTO> addMessageToConversation(@PathVariable Long conversationId, @RequestBody MessageDTO messagesDTO) {

        MessageDTO dto = messageService.addMessageToConversation(conversationId, messagesDTO);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{conversationId}")
    @Operation(summary = "get messages")
    public Page<Message> getMessages(@PathVariable Long conversationId, Pageable pageable) {
        return this.messageService.getAllMessages(pageable, conversationId);
    }


}

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

import java.util.logging.Logger;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final static Logger logger = Logger.getLogger(MessageController.class.getName());


    private final MessageServices messageService;

    @Autowired
    public MessageController(MessageServices messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/addMessageToConversation/{conversationId}")
    @Operation(summary = "add message to conversations")
    public ResponseEntity<MessageDTO> addMessageToConversation(@PathVariable Long conversationId, @RequestBody MessageDTO messagesDTO) throws Exception {

        try {
            MessageDTO dto = messageService.addMessageToConversation(conversationId, messagesDTO);
            if (dto != null) {
                logger.info("Message added to conversation: " + dto);
                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/getMessagesFromConversation/{conversationId}")
    @Operation(summary = "get messages")
    public ResponseEntity<Page<Message>> getMessages(@PathVariable Long conversationId, Pageable pageable) {
        Page<Message> messagePage = messageService.getAllMessages(pageable, conversationId);
        if (messagePage.getContent().isEmpty()) {
            logger.info("No messages found for conversation: " + conversationId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(messagePage);
        }
    }


}

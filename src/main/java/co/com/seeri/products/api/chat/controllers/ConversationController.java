package co.com.seeri.products.api.chat.controllers;


import co.com.seeri.products.api.chat.dto.ConversationDTO;
import co.com.seeri.products.api.chat.services.ConversationServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/connversations")
@Tag(name = "Conversation Controller")
public class ConversationController {
    private final static Logger logger = Logger.getLogger(ConversationController.class.getName());


    private final ConversationServices conversationService;

    @Autowired
    public ConversationController(ConversationServices conversationService) {
        this.conversationService = conversationService;
    }


    @GetMapping
    @Operation(summary = "get all conversations")
    public ResponseEntity<List<ConversationDTO>> getAllConversations() {
        List<ConversationDTO> conversations = conversationService.getAllConversations();
        if (conversations.isEmpty()) {
            logger.info("No conversations found");
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(conversations);
        }
    }

    @PostMapping
    @Operation(summary = "add conversations")
    public ResponseEntity<ConversationDTO> addConversation(@RequestBody ConversationDTO conversation) {
        return ResponseEntity.ok(conversationService.addConversation(conversation));
    }
}

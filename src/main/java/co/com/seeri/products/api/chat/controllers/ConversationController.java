package co.com.seeri.products.api.chat.controllers;


import co.com.seeri.products.api.chat.dto.ConversationDTO;
import co.com.seeri.products.api.chat.services.ConversationServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connversations")
@Tag(name="Conversation Controller")
public class ConversationController {

    private final ConversationServices conversationService;

    @Autowired
    public ConversationController(ConversationServices conversationService) {
        this.conversationService = conversationService;
    }


    @GetMapping
    @Operation(summary = "get all conversations")
    public List<ConversationDTO> getAllConversations() {
        return conversationService.getAllConversations();
    }

    @PostMapping
    @Operation(summary = "add conversations")
    public ConversationDTO addConversation(@RequestBody ConversationDTO conversation) {
        return conversationService.addConversation(conversation);
    }
}

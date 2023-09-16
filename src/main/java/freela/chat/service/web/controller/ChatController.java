package freela.chat.service.web.controller;


import freela.chat.service.domain.model.entities.Chat;
import freela.chat.service.domain.model.entities.Message;
import freela.chat.service.domain.model.request.CreateChatRequest;
import freela.chat.service.domain.service.ChatService;
import freela.chat.service.domain.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    public ChatController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Chat> create(@RequestBody CreateChatRequest request){
        return ResponseEntity.ok(this.chatService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Chat>> getAllByUser(@RequestParam Integer userId,@RequestParam Boolean isFreelancer){
        return ResponseEntity.ok(this.chatService.getAllByUser(userId, isFreelancer));
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<List<Message>> getAllByChat(@PathVariable @NotNull Integer chatId){
        return ResponseEntity.ok(this.messageService.getAllMessages(chatId));
    }
}

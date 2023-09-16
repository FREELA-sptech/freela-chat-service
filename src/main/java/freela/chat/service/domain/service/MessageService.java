package freela.chat.service.domain.service;

import freela.chat.service.domain.model.entities.Chat;
import freela.chat.service.domain.model.entities.Message;
import freela.chat.service.domain.model.request.CreateMessageRequest;
import freela.chat.service.domain.service.interfaces.IMessageService;
import freela.chat.service.infra.repository.ChatRepository;
import freela.chat.service.infra.repository.MessageRepository;
import freela.chat.service.web.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService implements IMessageService {
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    public MessageService(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages(Integer chatId) {
        Chat chat = this.chatRepository.findById(chatId).orElseThrow(
                () -> new NotFoundException("Chat não encontrado!")
        );

        return this.messageRepository.findAllByChat(chat);
    }

    @Override
    public Message create(Integer userId, CreateMessageRequest messageRequest) {
        Chat chat = this.chatRepository.findById(messageRequest.getChatId()).orElseThrow(
                () -> new NotFoundException("Chat não encontrado!")
        );

        Message message = new Message(userId, messageRequest.getMessage(), messageRequest.getTime(),chat);

        this.messageRepository.save(message);

        return message;
    }
}

package freela.chat.service.domain.service;

import freela.chat.service.domain.model.entities.Chat;
import freela.chat.service.domain.model.request.CreateChatRequest;
import freela.chat.service.domain.service.interfaces.IChatService;
import freela.chat.service.infra.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService implements IChatService {
    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat create(CreateChatRequest chatRequest) {
        Chat chatResponse = this.chatRepository.findByUserIdAndFreelancerId(chatRequest.getUserId(), chatRequest.getFreelancerId()).orElseThrow(
                () -> new Exception("Chat nao encontrado!")
        );

        Chat chat = new Chat(
                chatRequest.getFreelancerId(),
                chatRequest.getUserId(),
                chatRequest.getOrderId(),
                chatRequest.getLastUpdate()
        );

        this.chatRepository.save(chat);

        return chat;
    }

    @Override
    public List<Chat> getAllByUser(Integer userId, Boolean isFreelancer) {
        List<Chat> response = new ArrayList<>();

        if (isFreelancer) {
            response = this.chatRepository.findAllByFreelancerId(userId);
        } else {
            response = this.chatRepository.findAllByUserId(userId);
        }

        return response;
    }
}

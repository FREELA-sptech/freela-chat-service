package freela.chat.service.domain.service.interfaces;

import freela.chat.service.domain.model.entities.Chat;
import freela.chat.service.domain.model.request.CreateChatRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IChatService {
    Chat create(CreateChatRequest chatRequest);
    List<Chat> getAllByUser(Integer userId, Boolean isFreelancer);
}

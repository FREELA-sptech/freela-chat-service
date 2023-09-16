package freela.chat.service.domain.service.interfaces;

import freela.chat.service.domain.model.entities.Message;
import freela.chat.service.domain.model.request.CreateMessageRequest;

import java.util.List;

public interface IMessageService {
    List<Message> getAllMessages(Integer chatId);
    Message create(Integer userId, CreateMessageRequest messageRequest);
}

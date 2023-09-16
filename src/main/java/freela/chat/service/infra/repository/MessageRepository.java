package freela.chat.service.infra.repository;

import freela.chat.service.domain.model.entities.Chat;
import freela.chat.service.domain.model.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByChat(Chat chat);
}

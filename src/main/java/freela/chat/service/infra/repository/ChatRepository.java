package freela.chat.service.infra.repository;


import freela.chat.service.domain.model.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<Chat> findAllByFreelancerId (Integer freelancerId);
    List<Chat> findAllByUserId (Integer userId);
}

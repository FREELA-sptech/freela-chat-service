package freela.chat.service.infra.repository;


import freela.chat.service.domain.model.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<Chat> findAllByFreelancerId (Integer freelancerId);
    List<Chat> findAllByUserId (Integer userId);
    Optional<Chat> findByUserIdAndFreelancerId(Integer userId, Integer freelancerId);
}

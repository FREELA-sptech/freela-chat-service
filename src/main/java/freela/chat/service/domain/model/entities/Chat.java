package freela.chat.service.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "chat_bff")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer freelancerId;
    private Integer userId;
    private Integer orderId;
    private String lastUpdate;

    public Chat(Integer freelancerId, Integer userId, Integer orderId, String lastUpdate) {
        this.freelancerId = freelancerId;
        this.userId = userId;
        this.orderId = orderId;
        this.lastUpdate = lastUpdate;
    }
}

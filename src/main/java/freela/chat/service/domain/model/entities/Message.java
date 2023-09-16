package freela.chat.service.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userIdFrom;
    private String message;
    private String time;
    @ManyToOne
    private Chat chat;

    public Message(Integer userIdFrom, String message, String time, Chat chat) {
        this.userIdFrom = userIdFrom;
        this.message = message;
        this.time = time;
        this.chat = chat;
    }
}

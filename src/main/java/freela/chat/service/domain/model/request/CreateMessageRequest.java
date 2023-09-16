package freela.chat.service.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequest {
    private String message;
    private String time;
    private Integer chatId;
    private Integer to;
}

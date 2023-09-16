package freela.chat.service.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {
    private Integer freelancerId;
    private Integer userId;
    private Integer orderId;
    private String lastUpdate;
}

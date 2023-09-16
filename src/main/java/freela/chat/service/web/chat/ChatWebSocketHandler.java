package freela.chat.service.web.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import freela.chat.service.domain.model.entities.Message;
import freela.chat.service.domain.model.request.CreateMessageRequest;
import freela.chat.service.domain.service.ChatService;
import freela.chat.service.domain.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // Injete os serviços necessários
    private final ChatService chatService;
    private final MessageService messageService;

    @Autowired
    public ChatWebSocketHandler(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);

        // Obtém o valor do parâmetro roomId da query string
        String userId = getRoomIdUserQueryString(session.getUri().getQuery());

        // Adiciona o userId à sessão como um atributo
        session.getAttributes().put("userId", userId);
    }

    private String getRoomIdUserQueryString(String queryString) {
        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromUriString("?" + queryString).build().getQueryParams();
        return queryParams.getFirst("userId");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        // Converter o JSON em um objeto MessageRequest
        ObjectMapper objectMapper = new ObjectMapper();
        CreateMessageRequest messageRequest = objectMapper.readValue(payload, CreateMessageRequest.class);

        // Obter o userId da sessão como String
        String userIdString = (String) session.getAttributes().get("userId");

        // Converter o userId de String para Integer
        Integer userId = Integer.parseInt(userIdString);

        // Fazer o processamento necessário com a mensagem recebida
        Message messages = this.messageService.create(userId, messageRequest);

        // Converter o objeto MessageResponse para uma string JSON
        String jsonMessage = objectMapper.writeValueAsString(messages);

        // Criar um novo TextMessage com a string JSON
        TextMessage textMessage = new TextMessage(jsonMessage);

        // Enviar a mensagem processada para o usuário de destino
        for (WebSocketSession webSocketSession : webSocketSessions) {
            String userIdStringLocal = (String) webSocketSession.getAttributes().get("userId");
            Integer userIdLocal = Integer.parseInt(userIdStringLocal);

            if (userIdLocal == messageRequest.getTo() || userIdLocal == userId) {
                webSocketSession.sendMessage(textMessage);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}

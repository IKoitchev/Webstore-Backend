package com.store.VideoGameStore.controllers;
import com.store.VideoGameStore.Dto.MessageDto;
import com.store.VideoGameStore.websocket.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


//    @MessageMapping("/**")
//    @SendTo("/topic/message")
//    public MessageDto postMessage1(MessageDto messageDto) {
//        return messageService.saveMessage(messageDto);
//    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public MessageDto postMessage(MessageDto messageDto) {
        return messageService.saveMessage(messageDto);
    }

    @GetMapping("/message")
    public ResponseEntity<List<MessageDto>> getAllMessages() {
        List<MessageDto> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

}


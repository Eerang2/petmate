package green.petmate.chatting.controller;

import green.petmate.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, Object> request) {
        try {
            Long senderId = Long.valueOf(request.get("senderId").toString());
            Long receiverId = Long.valueOf(request.get("receiverId").toString());
            String content = request.get("content").toString();
            return ResponseEntity.ok(chatService.sendMessage(senderId, receiverId, content));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/conversation")
    public ResponseEntity<?> getConversation(
            @RequestParam Long userId1,
            @RequestParam Long userId2) {
        return ResponseEntity.ok(chatService.getConversation(userId1, userId2));
    }
}

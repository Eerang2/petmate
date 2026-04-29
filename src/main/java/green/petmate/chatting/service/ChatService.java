package green.petmate.chatting.service;

import green.petmate.chatting.entity.ChatMessage;
import green.petmate.user.entity.User;
import green.petmate.chatting.entity.repository.ChatMessageRepository;
import green.petmate.user.entity.repositoey.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Transactional
    public Map<String, Object> sendMessage(Long senderId, Long receiverId, String content) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        ChatMessage message = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .build();

        chatMessageRepository.save(message);

        Map<String, Object> result = new HashMap<>();
        result.put("id", message.getId());
        result.put("senderId", senderId);
        result.put("receiverId", receiverId);
        result.put("content", content);
        result.put("createdAt", message.getCreatedAt().toString());
        return result;
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getConversation(Long userId1, Long userId2) {
        List<ChatMessage> messages = chatMessageRepository.findConversation(userId1, userId2);
        return messages.stream().map(m -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", m.getId());
            map.put("senderId", m.getSender().getId());
            map.put("senderName", m.getSender().getName());
            map.put("senderProfileImage", m.getSender().getProfileImage());
            map.put("content", m.getContent());
            map.put("createdAt", m.getCreatedAt().toString());
            return map;
        }).collect(Collectors.toList());
    }
}

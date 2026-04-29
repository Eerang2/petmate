package green.petmate.chatting.entity.repository;

import green.petmate.chatting.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("SELECT m FROM ChatMessage m WHERE " +
           "(m.sender.id = :userId1 AND m.receiver.id = :userId2) OR " +
           "(m.sender.id = :userId2 AND m.receiver.id = :userId1) " +
           "ORDER BY m.createdAt ASC")
    List<ChatMessage> findConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    @Query("SELECT m FROM ChatMessage m WHERE m.id IN (" +
           "SELECT MAX(m2.id) FROM ChatMessage m2 WHERE m2.sender.id = :userId OR m2.receiver.id = :userId " +
           "GROUP BY CASE WHEN m2.sender.id = :userId THEN m2.receiver.id ELSE m2.sender.id END) " +
           "ORDER BY m.createdAt DESC")
    List<ChatMessage> findLatestMessagesByUser(@Param("userId") Long userId);
}

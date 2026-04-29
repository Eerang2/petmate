package green.petmate.matching.entity.repository;

import green.petmate.matching.entity.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {
    List<MatchRequest> findByReceiverIdAndStatus(Long receiverId, String status);
    List<MatchRequest> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
    Optional<MatchRequest> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
    List<MatchRequest> findByReceiverIdAndStatusOrSenderIdAndStatus(
            Long receiverId, String status1, Long senderId, String status2);
}

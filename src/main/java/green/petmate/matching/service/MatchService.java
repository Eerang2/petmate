package green.petmate.matching.service;

import green.petmate.user.dto.UserResponse;
import green.petmate.matching.entity.MatchRequest;
import green.petmate.user.entity.User;
import green.petmate.matching.entity.repository.MatchRequestRepository;
import green.petmate.user.entity.repositoey.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final MatchRequestRepository matchRequestRepository;

    @Transactional(readOnly = true)
    public List<UserResponse> findNearbyUsers(Long userKey, String region) {
        User me = userRepository.findById(userKey)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .filter(u -> !u.getId().equals(userKey))
                .filter(u -> u.getLatitude() != null && u.getLongitude() != null)
                .filter(u -> region == null || region.isEmpty() ||
                       (u.getAddress() != null && u.getAddress().contains(region)))
                .sorted((a, b) -> {
                    double distA = calculateDistance(me.getLatitude(), me.getLongitude(),
                            a.getLatitude(), a.getLongitude());
                    double distB = calculateDistance(me.getLatitude(), me.getLongitude(),
                            b.getLatitude(), b.getLongitude());
                    return Double.compare(distA, distB);
                })
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void sendMatchRequest(Long senderId, Long receiverId) {
        Optional<MatchRequest> existing = matchRequestRepository
                .findBySenderIdAndReceiverId(senderId, receiverId);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("이미 매칭 요청을 보냈습니다.");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        MatchRequest request = MatchRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .status("PENDING")
                .build();

        matchRequestRepository.save(request);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getPendingRequests(Long userId) {
        List<MatchRequest> requests = matchRequestRepository
                .findByReceiverIdAndStatus(userId, "PENDING");

        return requests.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("requestId", r.getId());
            map.put("sender", UserResponse.from(r.getSender()));
            map.put("createdAt", r.getCreatedAt().toString());
            return map;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void acceptRequest(Long requestId) {
        MatchRequest request = matchRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 요청입니다."));
        request.setStatus("ACCEPTED");
        matchRequestRepository.save(request);
    }

    @Transactional
    public void rejectRequest(Long requestId) {
        MatchRequest request = matchRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 요청입니다."));
        request.setStatus("REJECTED");
        matchRequestRepository.save(request);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAcceptedUsers(Long userId) {
        List<MatchRequest> accepted = matchRequestRepository
                .findByReceiverIdAndStatusOrSenderIdAndStatus(userId, "ACCEPTED", userId, "ACCEPTED");

        return accepted.stream().map(r -> {
            User other = r.getSender().getId().equals(userId) ? r.getReceiver() : r.getSender();
            return UserResponse.from(other);
        }).collect(Collectors.toList());
    }

    private double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        if (lat1 == null || lon1 == null || lat2 == null || lon2 == null) return Double.MAX_VALUE;
        double R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}

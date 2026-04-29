package green.petmate.matching.controller;

import green.petmate.user.dto.UserResponse;
import green.petmate.matching.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/nearby")
    public ResponseEntity<?> findNearbyUsers(
            @RequestParam Long userKey,
            @RequestParam(required = false) String region) {
        try {
            List<UserResponse> users = matchService.findNearbyUsers(userKey, region);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/request")
    public ResponseEntity<?> sendMatchRequest(@RequestBody Map<String, Long> request) {
        try {
            matchService.sendMatchRequest(request.get("senderId"), request.get("receiverId"));
            return ResponseEntity.ok(Map.of("success", true));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingRequests(@RequestParam Long userKey) {
        return ResponseEntity.ok(matchService.getPendingRequests(userKey));
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long requestId) {
        try {
            matchService.acceptRequest(requestId);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<?> rejectRequest(@PathVariable Long requestId) {
        try {
            matchService.rejectRequest(requestId);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/accepted")
    public ResponseEntity<?> getAcceptedUsers(@RequestParam Long userKey) {
        return ResponseEntity.ok(matchService.getAcceptedUsers(userKey));
    }
}

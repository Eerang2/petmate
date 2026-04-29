package green.petmate.user.controller;

import green.petmate.user.dto.SignupRequest;
import green.petmate.user.dto.UserResponse;
import green.petmate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestPart("data") SignupRequest request,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(value = "petImage", required = false) MultipartFile petImage) {
        try {
            Long userId = userService.signup(request, profileImage, petImage);
            return ResponseEntity.ok(Map.of("success", true, "userKey", userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "파일 업로드 실패"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        try {
            Long userKey = userService.login(request.get("loginId"), request.get("password"));
            return ResponseEntity.ok(Map.of("success", true, "userKey", userKey));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/user/{userKey}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userKey) {
        try {
            UserResponse response = userService.getUserInfo(userKey);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PutMapping("/user/{userKey}/info")
    public ResponseEntity<?> updateUserInfo(
            @PathVariable Long userKey,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {
        try {
            userService.updateUserInfo(userKey, name, address, latitude, longitude, profileImage);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PutMapping("/user/{userKey}/pet")
    public ResponseEntity<?> updatePetInfo(
            @PathVariable Long userKey,
            @RequestParam(required = false) String petType,
            @RequestParam(required = false) String petBreed,
            @RequestParam(required = false) String personality,
            @RequestPart(value = "petImage", required = false) MultipartFile petImage) {
        try {
            userService.updatePetInfo(userKey, petType, petBreed, personality, petImage);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}

package green.petmate.community.controller;

import green.petmate.community.dto.CommentResponse;
import green.petmate.community.dto.CommunityPostResponse;
import green.petmate.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) Long userKey) {
        try {
            List<CommunityPostResponse> posts = communityService.getPosts(tag, userKey);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostDetail(
            @PathVariable Long postId,
            @RequestParam(required = false) Long userKey) {
        try {
            CommunityPostResponse post = communityService.getPostDetail(postId, userKey);
            return ResponseEntity.ok(post);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(
            @RequestParam Long userKey,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String tag,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        try {
            Long postId = communityService.createPost(userKey, title, content, tag, images);
            return ResponseEntity.ok(Map.of("success", true, "postId", postId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("success", false, "message", "파일 업로드 실패"));
        }
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable Long postId,
            @RequestParam Long userKey) {
        try {
            communityService.deletePost(postId, userKey);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> toggleLike(
            @PathVariable Long postId,
            @RequestParam Long userKey) {
        try {
            Map<String, Object> result = communityService.toggleLike(postId, userKey);
            result.put("success", true);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long postId) {
        try {
            List<CommentResponse> comments = communityService.getComments(postId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> addComment(
            @PathVariable Long postId,
            @RequestBody Map<String, Object> request) {
        try {
            Long userId = Long.valueOf(request.get("userKey").toString());
            String content = request.get("content").toString();
            Long parentId = request.containsKey("parentId") && request.get("parentId") != null
                    ? Long.valueOf(request.get("parentId").toString())
                    : null;
            CommentResponse comment = communityService.addComment(postId, userId, content, parentId);
            return ResponseEntity.ok(Map.of("success", true, "comment", comment));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long userKey) {
        try {
            communityService.deleteComment(commentId, userKey);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}

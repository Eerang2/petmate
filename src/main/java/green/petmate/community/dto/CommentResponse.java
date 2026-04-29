package green.petmate.community.dto;

import green.petmate.community.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private String createdAt;
    private Long userId;
    private String userName;
    private String userProfileImage;
    private Long parentId;
    private List<CommentResponse> replies;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .userId(comment.getUser().getId())
                .userName(comment.getUser().getName())
                .userProfileImage(comment.getUser().getProfileImage())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .replies(comment.getReplies() != null
                        ? comment.getReplies().stream()
                            .map(CommentResponse::from)
                            .collect(Collectors.toList())
                        : List.of())
                .build();
    }
}

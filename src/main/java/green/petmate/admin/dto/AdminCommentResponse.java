package green.petmate.admin.dto;

import green.petmate.community.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class AdminCommentResponse {
    private Long id;
    private String content;
    private String authorName;
    private Long authorId;
    private Long postId;
    private String postTitle;
    private String createdAt;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static AdminCommentResponse from(Comment c) {
        return AdminCommentResponse.builder()
                .id(c.getId())
                .content(c.getContent())
                .authorName(c.getUser().getName())
                .authorId(c.getUser().getId())
                .postId(c.getPost().getId())
                .postTitle(c.getPost().getTitle())
                .createdAt(c.getCreatedAt().format(FMT))
                .build();
    }
}

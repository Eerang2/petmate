package green.petmate.admin.dto;

import green.petmate.community.entity.CommunityPost;
import green.petmate.community.entity.PostImage;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class AdminPostResponse {
    private Long id;
    private String title;
    private String content;
    private String tag;
    private String authorName;
    private Long authorId;
    private String authorProfileImage;
    private String createdAt;
    private long commentCount;
    private long likeCount;
    private String thumbnailImage;
    private List<String> imageUrls;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static AdminPostResponse from(CommunityPost post, long commentCount, long likeCount) {
        List<String> images = null;
        String thumbnail = null;
        if (post.getImages() != null && !post.getImages().isEmpty()) {
            images = post.getImages().stream()
                    .sorted((a, b) -> {
                        int sa = a.getSortOrder() != null ? a.getSortOrder() : 0;
                        int sb = b.getSortOrder() != null ? b.getSortOrder() : 0;
                        return Integer.compare(sa, sb);
                    })
                    .map(PostImage::getImageUrl)
                    .collect(Collectors.toList());
            thumbnail = images.get(0);
        }

        return AdminPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .tag(post.getTag())
                .authorName(post.getUser().getName())
                .authorId(post.getUser().getId())
                .authorProfileImage(post.getUser().getProfileImage())
                .createdAt(post.getCreatedAt().format(FMT))
                .commentCount(commentCount)
                .likeCount(likeCount)
                .thumbnailImage(thumbnail)
                .imageUrls(images)
                .build();
    }
}

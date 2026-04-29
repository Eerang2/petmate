package green.petmate.community.dto;

import green.petmate.community.entity.CommunityPost;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CommunityPostResponse {
    private Long id;
    private String title;
    private String content;
    private String tag;
    private String createdAt;

    private Long userId;
    private String userName;
    private String userProfileImage;

    private String thumbnailImage;
    private List<String> imageUrls;

    private long likeCount;
    private long commentCount;
    private boolean liked;

    public static CommunityPostResponse from(CommunityPost post, long likeCount, long commentCount, boolean liked) {
        List<String> imageUrls = post.getImages().stream()
                .sorted((a, b) -> Integer.compare(
                        a.getSortOrder() != null ? a.getSortOrder() : 0,
                        b.getSortOrder() != null ? b.getSortOrder() : 0))
                .map(img -> img.getImageUrl())
                .collect(Collectors.toList());

        return CommunityPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .tag(post.getTag())
                .createdAt(post.getCreatedAt().toString())
                .userId(post.getUser().getId())
                .userName(post.getUser().getName())
                .userProfileImage(post.getUser().getProfileImage())
                .thumbnailImage(imageUrls.isEmpty() ? null : imageUrls.get(0))
                .imageUrls(imageUrls)
                .likeCount(likeCount)
                .commentCount(commentCount)
                .liked(liked)
                .build();
    }
}

package green.petmate.community.service;

import green.petmate.community.entity.Comment;
import green.petmate.community.entity.CommunityPost;
import green.petmate.community.entity.PostImage;
import green.petmate.community.entity.PostLike;
import green.petmate.community.entity.repository.CommentRepository;
import green.petmate.community.entity.repository.CommunityPostRepository;
import green.petmate.community.entity.repository.PostImageRepository;
import green.petmate.community.entity.repository.PostLikeRepository;
import green.petmate.community.dto.CommentResponse;
import green.petmate.community.dto.CommunityPostResponse;
import green.petmate.user.entity.User;
import green.petmate.user.entity.repositoey.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityPostRepository postRepository;
    private final PostImageRepository imageRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository likeRepository;
    private final UserRepository userRepository;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Transactional(readOnly = true)
    public List<CommunityPostResponse> getPosts(String tag, Long currentUserId) {
        List<CommunityPost> posts;
        if (tag == null || tag.isEmpty() || tag.equals("전체")) {
            posts = postRepository.findAllByOrderByCreatedAtDesc();
        } else {
            posts = postRepository.findByTagOrderByCreatedAtDesc(tag);
        }

        return posts.stream().map(post -> {
            long likeCount = likeRepository.countByPostId(post.getId());
            long commentCount = commentRepository.countByPostId(post.getId());
            boolean liked = currentUserId != null
                    && likeRepository.existsByPostIdAndUserId(post.getId(), currentUserId);
            return CommunityPostResponse.from(post, likeCount, commentCount, liked);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommunityPostResponse getPostDetail(Long postId, Long currentUserId) {
        CommunityPost post = postRepository.findByIdWithImages(postId);
        if (post == null) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
        long likeCount = likeRepository.countByPostId(postId);
        long commentCount = commentRepository.countByPostId(postId);
        boolean liked = currentUserId != null
                && likeRepository.existsByPostIdAndUserId(postId, currentUserId);
        return CommunityPostResponse.from(post, likeCount, commentCount, liked);
    }

    @Transactional
    public Long createPost(Long userId, String title, String content, String tag,
                           List<MultipartFile> images) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        CommunityPost post = CommunityPost.builder()
                .user(user)
                .title(title)
                .content(content)
                .tag(tag)
                .build();
        postRepository.save(post);

        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                MultipartFile file = images.get(i);
                if (file != null && !file.isEmpty()) {
                    String imageUrl = saveFile(file);
                    PostImage postImage = PostImage.builder()
                            .post(post)
                            .imageUrl(imageUrl)
                            .sortOrder(i)
                            .build();
                    imageRepository.save(postImage);
                }
            }
        }

        return post.getId();
    }

    @Transactional
    public void deletePost(Long postId, Long userId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인의 게시글만 삭제할 수 있습니다.");
        }
        postRepository.delete(post);
    }

    @Transactional
    public Map<String, Object> toggleLike(Long postId, Long userId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Optional<PostLike> existing = likeRepository.findByPostIdAndUserId(postId, userId);
        boolean liked;
        if (existing.isPresent()) {
            likeRepository.delete(existing.get());
            liked = false;
        } else {
            PostLike like = PostLike.builder().post(post).user(user).build();
            likeRepository.save(like);
            liked = true;
        }

        long likeCount = likeRepository.countByPostId(postId);
        Map<String, Object> result = new HashMap<>();
        result.put("liked", liked);
        result.put("likeCount", likeCount);
        return result;
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long postId) {
        List<Comment> topLevel = commentRepository.findTopLevelByPostId(postId);
        return topLevel.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse addComment(Long postId, Long userId, String content, Long parentId) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(content)
                .build();

        if (parentId != null) {
            Comment parent = commentRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
            comment.setParent(parent);
        }

        commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인의 댓글만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(UPLOAD_DIR + fileName);
        file.transferTo(dest);
        return "/uploads/" + fileName;
    }
}

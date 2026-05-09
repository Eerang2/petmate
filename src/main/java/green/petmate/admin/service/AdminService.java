package green.petmate.admin.service;

import green.petmate.admin.dto.*;
import green.petmate.admin.entity.Admin;
import green.petmate.admin.repository.AdminRepository;
import green.petmate.community.entity.Comment;
import green.petmate.community.entity.CommunityPost;
import green.petmate.community.entity.repository.CommentRepository;
import green.petmate.community.entity.repository.CommunityPostRepository;
import green.petmate.community.entity.repository.PostLikeRepository;
import green.petmate.matching.entity.repository.MatchRequestRepository;
import green.petmate.payment.repository.PaymentRepository;
import green.petmate.user.entity.User;
import green.petmate.user.entity.repositoey.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final CommunityPostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository likeRepository;
    private final MatchRequestRepository matchRequestRepository;
    private final PaymentRepository paymentRepository;

    public Admin login(String loginId, String password) {
        Admin admin = adminRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관리자 계정입니다."));
        if (!admin.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return admin;
    }

    public DashboardStatsResponse getStats() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        return DashboardStatsResponse.builder()
                .totalUsers(userRepository.count())
                .totalPosts(postRepository.count())
                .totalMatches(matchRequestRepository.count())
                .totalRevenue(paymentRepository.sumTotalCompletedAmount())
                .todaySignups(userRepository.countByCreatedAtBetween(startOfDay, endOfDay))
                .todayMatches(matchRequestRepository.countByCreatedAtBetween(startOfDay, endOfDay))
                .todayRevenue(paymentRepository.sumAmountByPaidAtBetween(startOfDay, endOfDay))
                .build();
    }

    public ChartDataResponse getChartData(String period) {
        List<String> labels = new ArrayList<>();
        List<Long> signups = new ArrayList<>();
        List<Long> matches = new ArrayList<>();
        List<Long> revenue = new ArrayList<>();
        List<Long> posts = new ArrayList<>();

        LocalDate today = LocalDate.now();

        if ("daily".equals(period)) {
            for (int i = 29; i >= 0; i--) {
                LocalDate date = today.minusDays(i);
                LocalDateTime start = date.atStartOfDay();
                LocalDateTime end = date.plusDays(1).atStartOfDay();

                labels.add(date.getMonthValue() + "/" + date.getDayOfMonth());
                signups.add(userRepository.countByCreatedAtBetween(start, end));
                matches.add(matchRequestRepository.countByCreatedAtBetween(start, end));
                revenue.add(paymentRepository.sumAmountByPaidAtBetween(start, end));
                posts.add(postRepository.countByCreatedAtBetween(start, end));
            }
        } else if ("monthly".equals(period)) {
            for (int i = 11; i >= 0; i--) {
                YearMonth ym = YearMonth.from(today).minusMonths(i);
                LocalDateTime start = ym.atDay(1).atStartOfDay();
                LocalDateTime end = ym.plusMonths(1).atDay(1).atStartOfDay();

                labels.add(ym.getYear() + "/" + ym.getMonthValue());
                signups.add(userRepository.countByCreatedAtBetween(start, end));
                matches.add(matchRequestRepository.countByCreatedAtBetween(start, end));
                revenue.add(paymentRepository.sumAmountByPaidAtBetween(start, end));
                posts.add(postRepository.countByCreatedAtBetween(start, end));
            }
        } else {
            for (int i = 4; i >= 0; i--) {
                int year = today.getYear() - i;
                LocalDateTime start = LocalDate.of(year, 1, 1).atStartOfDay();
                LocalDateTime end = LocalDate.of(year + 1, 1, 1).atStartOfDay();

                labels.add(String.valueOf(year));
                signups.add(userRepository.countByCreatedAtBetween(start, end));
                matches.add(matchRequestRepository.countByCreatedAtBetween(start, end));
                revenue.add(paymentRepository.sumAmountByPaidAtBetween(start, end));
                posts.add(postRepository.countByCreatedAtBetween(start, end));
            }
        }

        return ChartDataResponse.builder()
                .labels(labels).signups(signups).matches(matches)
                .revenue(revenue).posts(posts).build();
    }

    public List<AdminUserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<AdminUserResponse> result = new ArrayList<>();
        for (User user : users) {
            result.add(AdminUserResponse.from(user));
        }
        return result;
    }

    public List<AdminUserResponse> searchUsers(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<AdminUserResponse> result = new ArrayList<>();
        for (User user : users) {
            result.add(AdminUserResponse.from(user));
        }
        return result;
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        userRepository.delete(user);
    }

    public List<AdminPostResponse> getAllPosts() {
        List<CommunityPost> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<AdminPostResponse> result = new ArrayList<>();
        for (CommunityPost post : postList) {
            long cc = commentRepository.countByPostId(post.getId());
            long lc = likeRepository.countByPostId(post.getId());
            result.add(AdminPostResponse.from(post, cc, lc));
        }
        return result;
    }

    @Transactional
    public void updatePost(Long postId, String title, String content) {
        CommunityPost post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<AdminCommentResponse> getCommentsByPost(Long postId) {
        List<AdminCommentResponse> result = new ArrayList<>();
        List<Comment> topLevel = commentRepository.findTopLevelByPostId(postId);
        for (Comment c : topLevel) {
            result.add(AdminCommentResponse.from(c));
            if (c.getReplies() != null) {
                for (Comment reply : c.getReplies()) {
                    result.add(AdminCommentResponse.from(reply));
                }
            }
        }
        return result;
    }

    @Transactional
    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        comment.setContent(content);
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

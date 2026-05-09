package green.petmate.community.entity.repository;

import green.petmate.community.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    List<CommunityPost> findAllByOrderByCreatedAtDesc();

    List<CommunityPost> findByTagOrderByCreatedAtDesc(String tag);

    @Query("SELECT p FROM CommunityPost p LEFT JOIN FETCH p.images WHERE p.id = :postId")
    CommunityPost findByIdWithImages(@Param("postId") Long postId);

    @Query("SELECT COUNT(p) FROM CommunityPost p WHERE p.createdAt >= :start AND p.createdAt < :end")
    long countByCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}

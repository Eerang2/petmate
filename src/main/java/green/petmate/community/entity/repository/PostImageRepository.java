package green.petmate.community.entity.repository;

import green.petmate.community.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    List<PostImage> findByPostIdOrderBySortOrder(Long postId);
}

package green.petmate.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String petImage;

    @Column(nullable = false)
    private String petType; // 개, 고양이, 기타

    private String petBreed; // 시바견, 진돗개 등 (강아지일 때만)

    private String personality;
}

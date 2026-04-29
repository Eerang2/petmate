package green.petmate.payment.entity;

import green.petmate.user.entity.Grade;
import green.petmate.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 포트원 결제 고유번호
    @Column(nullable = false, unique = true)
    private String impUid;

    // 가맹점 주문번호
    @Column(nullable = false, unique = true)
    private String merchantUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 결제자 정보
    @Column(nullable = false)
    private String buyerName;

    private String buyerEmail;

    private String buyerTel;

    // 결제 정보
    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    @Column(nullable = false)
    private String payMethod; // card, trans, phone

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private PaymentStatus status = PaymentStatus.COMPLETED;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime paidAt = LocalDateTime.now();

    private LocalDateTime refundedAt;
}

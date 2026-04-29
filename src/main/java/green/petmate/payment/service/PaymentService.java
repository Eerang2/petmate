package green.petmate.payment.service;

import green.petmate.payment.dto.PaymentRequest;
import green.petmate.payment.dto.PaymentResponse;
import green.petmate.payment.entity.Payment;
import green.petmate.payment.entity.PaymentStatus;
import green.petmate.payment.repository.PaymentRepository;
import green.petmate.user.entity.Grade;
import green.petmate.user.entity.User;
import green.petmate.user.entity.repositoey.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    // 예상 금액 검증용
    private int getExpectedAmount(Grade grade) {
        return switch (grade) {
            case SILVER -> 900;     // TODO: 실제 가격으로 변경
            case GOLD -> 1000;      // TODO: 실제 가격으로 변경
            default -> 0;
        };
    }

    /**
     * 결제 검증 및 저장
     * - 포트원 서버 검증은 TODO로 남김 (REST API로 imp_uid 조회 후 금액 대조)
     * - 현재는 프론트에서 넘어온 금액과 서버 기대 금액만 비교
     */
    @Transactional
    public PaymentResponse verifyAndSave(PaymentRequest request) {
        User user = userRepository.findById(request.getUserKey())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Grade grade = Grade.valueOf(request.getGrade());
        int expectedAmount = getExpectedAmount(grade);

        // 금액 검증
        if (request.getAmount() != expectedAmount) {
            throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
        }

        // TODO: 포트원 REST API로 실제 결제 금액 검증
        // String token = getPortOneToken();
        // int actualAmount = getPaymentAmount(token, request.getImpUid());
        // if (actualAmount != expectedAmount) throw ...

        // 결제 내역 저장
        Payment payment = Payment.builder()
                .impUid(request.getImpUid())
                .merchantUid(request.getMerchantUid())
                .user(user)
                .buyerName(request.getBuyerName())
                .buyerEmail(request.getBuyerEmail())
                .buyerTel(request.getBuyerTel())
                .amount(request.getAmount())
                .grade(grade)
                .payMethod(request.getPayMethod() != null ? request.getPayMethod() : "card")
                .status(PaymentStatus.COMPLETED)
                .paidAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        // 유저 등급 업그레이드
        user.setGrade(grade);
        userRepository.save(user);

        return PaymentResponse.from(payment);
    }

    /**
     * 전체 결제 내역 조회
     */
    @Transactional(readOnly = true)
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAllByOrderByPaidAtDesc()
                .stream()
                .map(PaymentResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 상태별 결제 내역 조회
     */
    @Transactional(readOnly = true)
    public List<PaymentResponse> getPaymentsByStatus(String status) {
        PaymentStatus paymentStatus = PaymentStatus.valueOf(status);
        return paymentRepository.findByStatusOrderByPaidAtDesc(paymentStatus)
                .stream()
                .map(PaymentResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 회원명으로 검색
     */
    @Transactional(readOnly = true)
    public List<PaymentResponse> searchByUserName(String name) {
        return paymentRepository.findByUser_NameContainingOrderByPaidAtDesc(name)
                .stream()
                .map(PaymentResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 환불 처리
     */
    @Transactional
    public PaymentResponse refund(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 결제 내역입니다."));

        if (payment.getStatus() == PaymentStatus.REFUNDED) {
            throw new IllegalArgumentException("이미 환불된 결제입니다.");
        }

        // TODO: 포트원 REST API로 실제 환불 요청
        // cancelPayment(token, payment.getImpUid(), payment.getAmount());

        payment.setStatus(PaymentStatus.REFUNDED);
        payment.setRefundedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        // 등급 다운그레이드 (기본 등급으로)
        User user = payment.getUser();
        user.setGrade(Grade.SILVER);
        userRepository.save(user);

        return PaymentResponse.from(payment);
    }
}

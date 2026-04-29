package green.petmate.payment.dto;

import green.petmate.payment.entity.Payment;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class PaymentResponse {
    private Long id;
    private String impUid;
    private String merchantUid;
    private Long userId;
    private String userName;
    private String buyerName;
    private String buyerEmail;
    private String buyerTel;
    private String grade;
    private String payMethod;
    private int amount;
    private String status;
    private String paidAt;
    private String refundedAt;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static PaymentResponse from(Payment p) {
        return PaymentResponse.builder()
                .id(p.getId())
                .impUid(p.getImpUid())
                .merchantUid(p.getMerchantUid())
                .userId(p.getUser().getId())
                .userName(p.getUser().getName())
                .buyerName(p.getBuyerName())
                .buyerEmail(p.getBuyerEmail())
                .buyerTel(p.getBuyerTel())
                .grade(p.getGrade().name())
                .payMethod(p.getPayMethod())
                .amount(p.getAmount())
                .status(p.getStatus().name())
                .paidAt(p.getPaidAt().format(FMT))
                .refundedAt(p.getRefundedAt() != null ? p.getRefundedAt().format(FMT) : null)
                .build();
    }
}

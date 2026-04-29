package green.petmate.payment.controller;

import green.petmate.payment.dto.PaymentRequest;
import green.petmate.payment.dto.PaymentResponse;
import green.petmate.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * 결제 검증 및 저장
     * POST /api/payment/verify
     */
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody PaymentRequest request) {
        try {
            PaymentResponse response = paymentService.verifyAndSave(request);
            return ResponseEntity.ok(Map.of("success", true, "payment", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    /**
     * 전체 결제 내역 조회 (관리자)
     * GET /api/payment/list
     * GET /api/payment/list?status=COMPLETED
     * GET /api/payment/list?keyword=홍길동
     */
    @GetMapping("/list")
    public ResponseEntity<?> getPayments(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword) {
        try {
            List<PaymentResponse> payments;

            if (keyword != null && !keyword.isBlank()) {
                payments = paymentService.searchByUserName(keyword);
            } else if (status != null && !status.isBlank()) {
                payments = paymentService.getPaymentsByStatus(status);
            } else {
                payments = paymentService.getAllPayments();
            }

            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    /**
     * 환불 처리 (관리자)
     * POST /api/payment/refund/{paymentId}
     */
    @PostMapping("/refund/{paymentId}")
    public ResponseEntity<?> refundPayment(@PathVariable Long paymentId) {
        try {
            PaymentResponse response = paymentService.refund(paymentId);
            return ResponseEntity.ok(Map.of("success", true, "payment", response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}

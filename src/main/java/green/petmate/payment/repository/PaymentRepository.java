package green.petmate.payment.repository;

import green.petmate.payment.entity.Payment;
import green.petmate.payment.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByImpUid(String impUid);

    Optional<Payment> findByMerchantUid(String merchantUid);

    List<Payment> findAllByOrderByPaidAtDesc();

    List<Payment> findByStatusOrderByPaidAtDesc(PaymentStatus status);

    List<Payment> findByUser_NameContainingOrderByPaidAtDesc(String name);

    List<Payment> findByUser_Id(Long userId);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.status = 'COMPLETED' AND p.paidAt >= :start AND p.paidAt < :end")
    long sumAmountByPaidAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status = 'COMPLETED' AND p.paidAt >= :start AND p.paidAt < :end")
    long countCompletedByPaidAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.status = 'COMPLETED'")
    long sumTotalCompletedAmount();
}

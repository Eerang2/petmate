package green.petmate.payment.repository;

import green.petmate.payment.entity.Payment;
import green.petmate.payment.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByImpUid(String impUid);

    Optional<Payment> findByMerchantUid(String merchantUid);

    List<Payment> findAllByOrderByPaidAtDesc();

    List<Payment> findByStatusOrderByPaidAtDesc(PaymentStatus status);

    List<Payment> findByUser_NameContainingOrderByPaidAtDesc(String name);

    List<Payment> findByUser_Id(Long userId);
}

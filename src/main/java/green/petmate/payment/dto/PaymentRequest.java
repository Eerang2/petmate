package green.petmate.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String impUid;
    private String merchantUid;
    private Long userKey;
    private String grade;
    private int amount;
    private String buyerName;
    private String buyerEmail;
    private String buyerTel;
    private String payMethod;
}

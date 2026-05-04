package tech.techsete.valorapayments_sdk.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.techsete.valorapayments_sdk.enums.TransactionStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixInResponse {
    private String id;
    private TransactionStatus status;
    private Integer amount;
    private String paymentMethod;
    private String createdAt;
    private String paidAt;
    private Pix pix;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pix {
        private String qrcode;
        private String expirationDate;
    }
}

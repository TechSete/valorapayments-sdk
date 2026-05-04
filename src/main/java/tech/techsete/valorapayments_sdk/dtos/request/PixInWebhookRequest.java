package tech.techsete.valorapayments_sdk.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.techsete.valorapayments_sdk.enums.TransactionStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixInWebhookRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private TransactionStatus status;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @JsonProperty("paidAt")
    private String paidAt;

    @JsonProperty("customer")
    private Customer customer;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Customer {

        @JsonProperty("name")
        private String name;

        @JsonProperty("document")
        private String document;
    }
}

package tech.techsete.valorapayments_sdk.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.techsete.valorapayments_sdk.enums.TransactionStatus;
import tech.techsete.valorapayments_sdk.utils.BigDecimalToCentsDeserializer;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixInResponse {
    private String id;
    private TransactionStatus status;
    @JsonDeserialize(using = BigDecimalToCentsDeserializer.class)
    private Integer amount;
    private String paymentMethod;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String paidAt;
    private Boolean traceable;
    private String ip;
    private Integer installments;
    private Pix pix;
    private Customer customer;
    private Splits splits;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pix {
        private String qrcode;
        private String expirationDate;
        private String secureUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Customer {
        private String name;
        private String email;
        private String phone;
        private String birthdate;
        private Document document;
        private String revenue;
        private Address address;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Document {
            private String type;
            private String number;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Address {
            private String street;
            private String streetNumber;
            private String complement;
            private String zipCode;
            private String neighborhood;
            private String city;
            private String state;
            private String country;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Splits {
        @JsonProperty("has_splits")
        private Boolean hasSplits;
        private Integer count;
        private String status;
        private Summary summary;
        private List<SplitDetail> details;
        private Badge badge;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Summary {
            @JsonProperty("has_splits")
            private Boolean hasSplits;
            private Integer count;
            @JsonProperty("total_amount")
            @JsonDeserialize(using = BigDecimalToCentsDeserializer.class)
            private Integer totalAmount;
            @JsonProperty("total_net_amount")
            @JsonDeserialize(using = BigDecimalToCentsDeserializer.class)
            private Integer totalNetAmount;
            private String status;
            private List<SplitDetail> recipients;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SplitDetail {
            @JsonProperty("receiver_id")
            private String receiverId;
            @JsonProperty("company_name")
            private String companyName;
            @JsonDeserialize(using = BigDecimalToCentsDeserializer.class)
            private Integer amount;
            @JsonProperty("net_amount")
            @JsonDeserialize(using = BigDecimalToCentsDeserializer.class)
            private Integer netAmount;
            private String status;
            @JsonProperty("split_type")
            private String splitType;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Badge {
            private String text;
            private String color;
            private String icon;
            private String variant;
            private String tooltip;
            private Integer count;
            @JsonDeserialize(using = BigDecimalToCentsDeserializer.class)
            private Integer amount;
        }
    }
}

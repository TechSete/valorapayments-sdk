package tech.techsete.valorapayments_sdk.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixInRequest {

    @NotNull(message = "O valor da cobrança é obrigatório.")
    @Min(value = 100, message = "O valor mínimo da cobrança é 100 centavos (R$ 1,00).")
    @JsonProperty("amount")
    private Integer amount;

    @Builder.Default
    @NotBlank(message = "O método de pagamento é obrigatório.")
    @JsonProperty("paymentMethod")
    private String paymentMethod = "pix";

    @Valid
    @NotNull(message = "As configurações PIX são obrigatórias.")
    @JsonProperty("pix")
    private Pix pix;

    @Size(max = 255, message = "A descrição da cobrança deve ter no máximo 255 caracteres.")
    @JsonProperty("description")
    private String description;

    @JsonProperty("webhook")
    private String webhook;

    @Valid
    @NotNull(message = "Os dados do pagador são obrigatórios.")
    @JsonProperty("customer")
    private Customer customer;

    @Valid
    @NotEmpty(message = "A cobrança deve possuir ao menos um item.")
    @JsonProperty("items")
    private List<Item> items;

    @Valid
    @JsonProperty("splits")
    private List<Split> splits;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pix {

        @NotNull(message = "O prazo de expiração do QR Code é obrigatório.")
        @Min(value = 1, message = "O prazo mínimo de expiração do QR Code é 1 dia.")
        @Max(value = 10, message = "O prazo máximo de expiração do QR Code é 10 dias.")
        @JsonProperty("expiresInDays")
        private Integer expiresInDays;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Customer {

        @NotBlank(message = "O nome do pagador é obrigatório.")
        @JsonProperty("name")
        private String name;

        @Email(message = "O e-mail do pagador deve ser válido.")
        @NotBlank(message = "O e-mail do pagador é obrigatório.")
        @JsonProperty("email")
        private String email;

        @NotBlank(message = "O telefone do pagador é obrigatório.")
        @Pattern(regexp = "\\d{10,11}", message = "O telefone do pagador deve conter 10 ou 11 dígitos.")
        @JsonProperty("phone")
        private String phone;

        @Valid
        @NotNull(message = "O documento do pagador é obrigatório.")
        @JsonProperty("document")
        private Document document;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Document {

        @NotBlank(message = "O número do documento do pagador é obrigatório.")
        @Pattern(regexp = "\\d{11}|\\d{14}", message = "O documento do pagador deve conter 11 dígitos para CPF ou 14 dígitos para CNPJ.")
        @JsonProperty("number")
        private String number;

        @NotBlank(message = "O tipo do documento do pagador é obrigatório.")
        @Pattern(regexp = "cpf|cnpj", message = "O tipo do documento do pagador deve ser cpf ou cnpj.")
        @JsonProperty("type")
        private String type;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {

        @NotBlank(message = "O título do item é obrigatório.")
        @JsonProperty("title")
        private String title;

        @NotNull(message = "O preço unitário do item é obrigatório.")
        @Min(value = 1, message = "O preço unitário do item deve ser maior que zero.")
        @JsonProperty("unitPrice")
        private Integer unitPrice;

        @NotNull(message = "A quantidade do item é obrigatória.")
        @Min(value = 1, message = "A quantidade do item deve ser maior que zero.")
        @JsonProperty("quantity")
        private Integer quantity;

        @JsonProperty("tangible")
        private Boolean tangible;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Split {

        @NotNull(message = "O ID da empresa recebedora do split é obrigatório.")
        @JsonProperty("company_id")
        private String companyId;

        @NotNull(message = "O valor do split é obrigatório.")
        @Min(value = 1, message = "O valor do split deve ser maior que zero.")
        @JsonProperty("amount")
        private Integer amount;
    }
}

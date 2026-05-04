package tech.techsete.valorapayments_sdk.configurations;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValoraPaymentsCredentials {

    @NotBlank(message = "A apiKey da ValoraPayments é obrigatória.")
    private String apiKey;

    @NotBlank(message = "A apiSecret da ValoraPayments é obrigatória.")
    private String apiSecret;
}

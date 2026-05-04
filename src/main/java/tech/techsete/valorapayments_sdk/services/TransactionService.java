package tech.techsete.valorapayments_sdk.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.techsete.valorapayments_sdk.configurations.ValoraPaymentsCredentials;
import tech.techsete.valorapayments_sdk.dtos.request.PixInRequest;
import tech.techsete.valorapayments_sdk.dtos.response.PixInResponse;

@Slf4j
@Service
@Validated
public class TransactionService {

    private static final String TRANSACTIONS_ENDPOINT = "/transactions";
    private final WebClient webClient;

    public TransactionService(@Qualifier("valoraPaymentsWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Cria uma cobrança PIX In de forma síncrona.
     *
     * @param request     dados da cobrança PIX In que será criada
     * @param credentials credenciais da conta ValoraPayments responsável pela criação da cobrança
     * @return resposta da API com os dados da transação e do QR Code PIX
     */
    public PixInResponse createPixIn(@Valid PixInRequest request,
                                     @NotNull(message = "As credenciais da ValoraPayments são obrigatórias.")
                                     @Valid ValoraPaymentsCredentials credentials) {
        return createPixInAsync(request, credentials).block();
    }

    /**
     * Cria uma cobrança PIX In de forma assíncrona.
     *
     * @param request     dados da cobrança PIX In que será criada
     * @param credentials credenciais da conta ValoraPayments responsável pela criação da cobrança
     * @return um {@link Mono} que emite a resposta da API com os dados da transação e do QR Code PIX
     */
    public Mono<PixInResponse> createPixInAsync(@Valid PixInRequest request,
                                                @NotNull(message = "As credenciais da ValoraPayments são obrigatórias.")
                                                @Valid ValoraPaymentsCredentials credentials) {

        log.debug("Criando cobrança PIX In na ValoraPayments");

        return webClient.post()
                .uri(TRANSACTIONS_ENDPOINT)
                .headers(headers -> headers.setBasicAuth(credentials.getApiKey(), credentials.getApiSecret()))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PixInResponse.class);
    }

    /**
     * Busca uma transação pelo ID de forma síncrona.
     *
     * @param transactionId ID da transação que será consultada
     * @param credentials   credenciais da conta ValoraPayments responsável pela consulta
     * @return resposta da API com os dados atuais da transação
     */
    public PixInResponse getTransactionById(@NotBlank(message = "O ID da transação é obrigatório.") String transactionId,
                                            @NotNull(message = "As credenciais da ValoraPayments são obrigatórias.")
                                            @Valid ValoraPaymentsCredentials credentials) {
        return getTransactionByIdAsync(transactionId, credentials).block();
    }

    /**
     * Busca uma transação pelo ID de forma assíncrona.
     *
     * @param transactionId ID da transação que será consultada
     * @param credentials   credenciais da conta ValoraPayments responsável pela consulta
     * @return um {@link Mono} que emite a resposta da API com os dados atuais da transação
     */
    public Mono<PixInResponse> getTransactionByIdAsync(@NotBlank(message = "O ID da transação é obrigatório.") String transactionId,
                                                       @NotNull(message = "As credenciais da ValoraPayments são obrigatórias.")
                                                       @Valid ValoraPaymentsCredentials credentials) {
        log.debug("Buscando transação {} na ValoraPayments", transactionId);

        return webClient.get()
                .uri(TRANSACTIONS_ENDPOINT + "/{transactionId}", transactionId)
                .headers(headers -> headers.setBasicAuth(credentials.getApiKey(), credentials.getApiSecret()))
                .retrieve()
                .bodyToMono(PixInResponse.class);
    }
}

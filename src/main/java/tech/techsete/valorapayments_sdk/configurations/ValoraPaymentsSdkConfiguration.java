package tech.techsete.valorapayments_sdk.configurations;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tech.techsete.valorapayments_sdk.services.TransactionService;

/**
 * Configuração principal do SDK ValoraPayments.
 * <p>
 * Esta classe é responsável pela configuração automática do SDK ValoraPayments,
 * habilitando a funcionalidade de auto-configuração do Spring Boot e definindo
 * o escaneamento de componentes para o pacote base do SDK.
 * </p>
 * <p>
 * A anotação {@link AutoConfiguration} permite que o Spring Boot configure automaticamente
 * este SDK quando ele é incluído como dependência em um projeto, simplificando a integração.
 * </p>
 * <p>
 * A anotação {@link ComponentScan} garante que todos os componentes, serviços e configurações
 * dentro do pacote base do SDK (tech.techsete.valorapayments_sdk) sejam detectados e registrados
 * como beans no contexto da aplicação Spring.
 * </p>
 * <p>
 * Este SDK facilita a integração com os serviços da ValoraPayments, incluindo processamento
 * de cobranças via PIX e gerenciamento de transações.
 * </p>
 *
 * @author Edson Isaac
 * @see TransactionService
 */
@AutoConfiguration
@ComponentScan("tech.techsete.valorapayments_sdk")
public class ValoraPaymentsSdkConfiguration {
}

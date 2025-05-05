# INTOPAYS SDK (Java)

Para mais informações, visite nosso site: [Intopays](https://intopays.com)

## Contato 
<p>
 <a href="https://wa.me/5511997649421" target="_blank">
  <img src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white" title="+55 11 99764-9421"/>
 </a>
<a href="https://www.linkedin.com/in/lucasscode" target="_blank">
 <img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank">
</a>  
</p>

## Como usar

Certifique-se de que o repositório JitPack está incluído no seu pom.xml:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Adicione a dependência no seu arquivo pom.xml:
```xml
<dependency>
    <groupId>com.github.intopays</groupId>
    <artifactId>intopays-java-sdk</artifactId>
    <version>v1.3.1</version>
</dependency>
```
## Inicialização

Você pode inicializar a SDK da seguinte maneira:

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.infra.config.Environment;

public class Main {
    public static void main(String[] args) {
        // Inicialize o construtor da SDK com token
        IntopaysConstructor config = new IntopaysConstructor("meu-intopays-token");

        // Crie a instância principal da SDK
        Intopays intopays = new Intopays(config);

        // A partir daqui, você pode acessar os serviços:
        // intopays.pix
        // intopays.boleto
        // intopays.webhook
    }
}

```
#### Parâmetros:

- `token`: (string): Token de autenticação fornecido pela Intopays.
  
#### Retorno:

- `Intopays`: Objeto Intopays com acesso às funcionalidades do SDK, como pix, webhooks, etc.

## Documentaçāo
- [Pix](#pix)
  - [Criar Pix](#criar-pix)
  - [Encontrar Pix](#encontrar-pix)
  - [Pesquisar Pix](#pesquisar-pix)
- [Boleto](#boleto)
  - [Criar Boleto](#criar-boleto)
  - [Encontrar Boleto](#encontrar-boleto)
  - [Pesquisar Boleto](#pesquisar-boleto)
  - [Cancelar Boleto](#cancelar-boleto)
- [Webhook](#webhook)
  - [Criar Webhook](#criar-webhook)
  - [Listar Webhooks](#listar-webhooks)
  - [Pesquisar Webhooks](#pesquisar-webhooks)
  - [Excluir Webhooks](#excluir-webhooks)

 ## Pix

## Criar pix

Você pode criar cobranças Pix utilizando o SDK de forma simples. para bancos `Sicredi`, `Sicoob`, `Santander`, `Banco do Brasil` enter outros.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.enums.IntegrationEnum;
import com.intopays.sdk.core.models.Pix;
import com.intopays.sdk.core.models.PixInfo;

import java.util.Arrays;

public class PixExample {
    public static void main(String[] args) {
        // Inicialização do SDK (ver seção de inicialização)
        Intopays intopays = ...;

        // Criação do objeto Pix
        Pix pix = new Pix();
        pix.setCalendarExpiration(86400); // Tempo de expiração em segundos
        pix.setDebtorName("Lucas Lopes");
        pix.setDebtorDocument("12345678901"); // CPF ou CNPJ
        pix.setAmountOriginal("10.99"); // Valor do Pix
        pix.setAmountModificationType(0); // Tipo de modificação no valor
        pix.setPayerRequest("Cobrança de serviço");

        // Informações adicionais
        PixInfo info = new PixInfo();
        info.setName("Campo 1");
        info.setValue("Informação Adicional do PSP-Recebedor");
        pix.setAdditionalInfos(Arrays.asList(info));

        // Tipo de integração (banco)
        pix.setIntegrationType(IntegrationEnum.SICOOB);

        try {
            Pix response = intopays.pix.create(pix);
            System.out.println("Pix gerado com sucesso:");
            System.out.println("QR Code: " + response.getQrcode());
            System.out.println("URL: " + response.getUrl());
        } catch (Exception e) {
            System.err.println("Erro ao gerar Pix: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `amountOriginal`: O valor do pix (em reais).
- `debtorName`: Nome do pagador.
- `debtorDocument`: CPF/CNPJ do pagador.
- `payerRequest`: Descrição da cobrança.
- `calendarExpiration`: Tempo em segundos para expirar
- `amountModificationType`: Tipo de modificação no valor da cobrança.
- `additionalInfos`: Informações adicionais da cobrança, contendo nome e valor.
- `integrationType`: Tipo de integração (use o enum IntegrationEnum para escolher).

#### Retorno:

- `Pix`: Objeto com os dados da cobrança `Pix`, incluindo `qrcode`, `location`, `status` e `url`.

## Encontrar Pix

Você pode encontrar um Pix existente usando o ID de pagamento.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Pix;

public class PixFinder {
    public static void main(String[] args) {
        // Inicialize o SDK (ver seção de inicialização)
        Intopays intopays = ...;

        // ID do Pix previamente criado
        String pixId = "123"; // Exemplo: String.valueOf(createdPix.getId());

        try {
            Pix foundPix = intopays.pix.find(pixId);
            System.out.println("Pix encontrado:");
            System.out.println("Status: " + foundPix.getStatus());
            System.out.println("QR Code: " + foundPix.getQrcode());
            System.out.println("URL: " + foundPix.getUrl());
        } catch (Exception e) {
            System.err.println("Erro ao encontrar o Pix: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `pixId`: ID do Pix gerado anteriormente.

#### Retorno:

- `Pix`: Objeto com os dados da cobrança `Pix`, incluindo `qrcode`, `location`, `status` e `url`.

## Pesquisar Pix

Você pode pesquisar um Pix por CPF/CNPJ, status ou data.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Pix;
import com.intopays.sdk.core.enums.PixTransactionStatusEnum;

import java.util.List;

public class PixSearchExample {
    public static void main(String[] args) {
        // Inicialize o SDK (ver seção de inicialização)
        Intopays intopays = ...;

        // Criar objeto Pix com os critérios de pesquisa
        Pix searchCriteria = new Pix();
        searchCriteria.setDebtorDocument("12345678901"); // CPF ou CNPJ
        searchCriteria.setStatus(PixTransactionStatusEnum.ACTIVE); // Ex: ACTIVE, COMPLETED

        try {
            List<Pix> resultList = intopays.pix.search(searchCriteria);

            System.out.println("Resultados da pesquisa:");
            for (Pix result : resultList) {
                System.out.println("ID: " + result.getId());
                System.out.println("Status: " + result.getStatus());
                System.out.println("QR Code: " + result.getQrcode());
                System.out.println("URL: " + result.getUrl());
                System.out.println("--------------------------");
            }
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar Pix: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `debtorDocument`: (opcional): CPF ou CNPJ do pagador.
- `status`: (opcional): Status da cobrança (ex: ACTIVE, COMPLETED).

#### Retorno:

- `Pix`: Lista de cobranças Pix que atendem aos critérios. Objeto com os dados da cobrança `Pix`, incluindo `qrcode`, `location`, `status` e `url`.

## Boleto

## Criar boleto

Você pode criar boletos utilizando o SDK, com opções de `integração` com bancos como `Banco do Brasil`, `Bradesco`, `Itaú`, entre outros.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Boleto;
import com.intopays.sdk.core.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BoletoExample {
    public static void main(String[] args) {
        // Inicialize o SDK (ver seção de inicialização)
        Intopays intopays = ...;

        // Configuração do boleto
        Boleto boleto = new Boleto();
        boleto.setAmount(new BigDecimal("10.01")); // Valor do boleto: R$10,01
        boleto.setDueDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        boleto.setDaysAfterDueDateForCancellation(30);
        
        boleto.setPayerName("Lucas Lopes");
        boleto.setPayerDocument("12345678901");
        boleto.setPayerEmail("lucas@example.com");
        boleto.setPayerPhone("51999999999");
        boleto.setPayerZipCode("12345678");
        boleto.setPayerAddress("Rua Exemplo");
        boleto.setPayerNumber("123");
        boleto.setPayerComplement("Apto 101");
        boleto.setPayerNeighborhood("Centro");
        boleto.setPayerCity("Cidade");
        boleto.setPayerState(StateEnum.SP);

        boleto.setMessageLine1("Obrigado pela preferência!");
        boleto.setMessageLine2("Vencimento em 3 dias úteis.");

        boleto.setDiscount1Code(DiscountEnum.NO_DISCOUNT);
        boleto.setFineCode(FineTypeEnum.NO_FINE);
        boleto.setInterestCode(InterestEnum.EXEMPT);

        boleto.setFinalBeneficiaryName("Empresa Beneficiária");
        boleto.setFinalBeneficiaryDocument("11111111111");
        boleto.setFinalBeneficiaryZipCode("87654321");
        boleto.setFinalBeneficiaryAddress("Rua Final");
        boleto.setFinalBeneficiaryNeighborhood("Bairro Final");
        boleto.setFinalBeneficiaryCity("Cidade Final");
        boleto.setFinalBeneficiaryState(StateEnum.RJ);

        boleto.setIntegrationType(IntegrationEnum.SICOOB); // Banco integrador

        try {
            Boleto resultado = intopays.boleto.create(boleto);
            System.out.println("Boleto criado com sucesso!");
            System.out.println("Código de barras: " + resultado.getBarcode());
            System.out.println("URL: " + resultado.getBoletoUrl());
            System.out.println("Status: " + resultado.getStatus());
        } catch (Exception e) {
            System.err.println("Erro ao gerar boleto: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `amount`: O valor do boleto (em reais).
- `dueDate`: A data de vencimento do boleto.
- `daysAfterDueDateForCancellation`: Número de dias após o vencimento para cancelamento do boleto.
- `payerDocument`: O CPF ou CNPJ do pagador.
- `payerName`: Nome do pagador.
- `payerEmail`: E-mail do pagador.
- `payerPhone`: Telefone do pagador.
- `payerZipCode`: CEP do pagador.
- `payerNumber`: Número do endereço do pagador.
- `payerComplement`: Complemento do endereço do pagador.
- `payerNeighborhood`: Bairro do pagador.
- `payerCity`: Cidade do pagador.
- `payerState`: Estado do pagador (use o enum StateEnum para escolher).
- `payerAddress`: Endereço do pagador.
- `messageLine1`: Mensagem personalizada (linha 1).
- `messageLine2`: Mensagem personalizada (linha 2).
- `discount1Code`: Código do desconto 1 (use o enum DiscountEnum para escolher).
- `discount1Rate`: Taxa de desconto 1.
- `discount1Value`: Valor do desconto 1.
- `discount1Date`: Data do desconto 1.
- `discount2Code`: Código do desconto 2 (use o enum DiscountEnum para escolher).
- `discount2Rate`: Taxa de desconto 2.
- `discount2Value`: Valor do desconto 2.
- `discount2Date`: Data do desconto 2.
- `fineCode`: Código de multa (use o enum FineTypeEnum para escolher).
- `fineDate`: Data da multa.
- `fineValue`: Valor da multa.
- `fineRate`: Taxa da multa.
- `interestCode`: Código de juros (use o enum InterestEnum para escolher).
- `interestDate`: Data dos juros.
- `interestRate`: Taxa de juros.
- `interestValue`: Valor dos juros.
- `finalBeneficiaryName`: Nome do beneficiário final.
- `finalBeneficiaryDocument`: CPF ou CNPJ do beneficiário final.
- `finalBeneficiaryZipCode`: CEP do beneficiário final.
- `finalBeneficiaryAddress`: Endereço do beneficiário final.
- `finalBeneficiaryNeighborhood`: Bairro do beneficiário final.
- `finalBeneficiaryCity`: Cidade do beneficiário final.
- `finalBeneficiaryState`: Estado do beneficiário final (use o enum StateEnum para escolher).
- `integrationType`: Tipo de integração (use o enum IntegrationEnum para escolher).

#### Retorno:

`Boleto`: Objeto contendo os dados da cobrança do boleto, incluindo `barcode`, `boletoUrl`, `dueDate`, `amount`, `status`, entre outros.

## Encontrar Boleto

Você pode encontrar um boleto específico utilizando seu ID com o SDK de forma simples.

##### Exemplo de Uso
```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Boleto;

public class BuscarBoletoExample {
    public static void main(String[] args) {
        // Inicialize o SDK (ver seção de inicialização)
        Intopays intopays = ...;

        try {
            Long boletoId = 123L; // ID do boleto que você deseja buscar
            Boleto boleto = intopays.boleto.find(boletoId);

            System.out.println("Boleto encontrado:");
            System.out.println("ID: " + boleto.getId());
            System.out.println("Valor: " + boleto.getAmount());
            System.out.println("Vencimento: " + boleto.getDueDate());
            System.out.println("Status: " + boleto.getStatus());
            System.out.println("Código de barras: " + boleto.getBarcode());
            System.out.println("URL: " + boleto.getBoletoUrl());
        } catch (Exception e) {
            System.err.println("Erro ao buscar boleto: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:
- `id`: ID do boleto que será encontrado. Este ID é retornado ao criar o boleto.

#### Retorno:
- `Boleto`: Objeto contendo os dados do boleto, como id, amount, dueDate, status e outros detalhes relacionados.

## Cancelar Boleto

Você pode cancelar uma cobrança de boleto utilizando o SDK de forma simples.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Boleto;

public class CancelarBoletoExample {
    public static void main(String[] args) {
        // Inicialize o SDK (ver seção de autenticação e configuração)
        Intopays intopays = ...;

        try {
            Long boletoId = 123L; // ID do boleto que será cancelado
            Boleto boletoCancelado = intopays.boleto.cancel(boletoId);

            System.out.println("Boleto cancelado com sucesso!");
            System.out.println("ID: " + boletoCancelado.getId());
            System.out.println("Status: " + boletoCancelado.getStatus());
        } catch (Exception e) {
            System.err.println("Erro ao cancelar boleto: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `id`: ID do boleto que será cancelado. Este ID é retornado ao criar o boleto.

#### Retorno:

- `Boleto`: Objeto com a confirmação do cancelamento do boleto, incluindo o status da operação e a mensagem de sucesso ou erro.


## Pesquisar Boleto

Você pode pesquisar boletos com base em diferentes critérios usando o SDK de forma simples.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Boleto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PesquisarBoletoExample {
    public static void main(String[] args) {
        // Inicialize o SDK (ver seção de autenticação e configuração)
        Intopays intopays = ...;

        try {
            Boleto filtro = new Boleto();
            filtro.setPayerName("Luffrs");
            filtro.setDueDate(Date.from(LocalDate.of(2025, 5, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

            List<Boleto> boletos = intopays.boleto.search(filtro);

            for (Boleto boleto : boletos) {
                System.out.println("ID: " + boleto.getId());
                System.out.println("Nome do Pagador: " + boleto.getPayerName());
                System.out.println("Valor: R$" + boleto.getAmount());
                System.out.println("Status: " + boleto.getStatus());
                System.out.println("------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar boletos: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:
- `payerName`: Nome do pagador (opcional).
- `dueDate`: Data de vencimento do boleto (opcional).
- `status`: Status do boleto, como "PENDENTE", "PAGO", etc. (opcional).

#### Retorno:
- `List<Boleto>`: Lista de objetos que representam os boletos encontrados com os critérios de pesquisa. Cada objeto de boleto pode incluir informações como `id`, `amount`, `dueDate`, `payerName`, `status`, entre outros detalhes.

## Webhook

## Criar Webhook

Você pode registrar um novo webhook utilizando o método create do SDK Java. O webhook será utilizado para receber notificações de eventos, como a confirmação de pagamento de boletos e pix.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Webhook;

import java.util.UUID;

public class CriarWebhookExample {
    public static void main(String[] args) {
        // Inicialize o SDK (ver seção de autenticação e configuração)
        Intopays intopays = ...;

        try {
            String endpoint = "https://intopays.com/" + UUID.randomUUID();

            Webhook novoWebhook = new Webhook();
            novoWebhook.setEndpoint(endpoint);

            Webhook recebido = intopays.webhook.create(novoWebhook);

            System.out.println("Webhook criado com sucesso!");
            System.out.println("ID: " + recebido.getId());
            System.out.println("Endpoint: " + recebido.getEndpoint());
        } catch (Exception e) {
            System.err.println("Erro ao criar webhook: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `payload.endpoint`: Endpoint responsável por receber eventos via webhook.

#### Retorno:

- `Webhook`: Objeto que representa um webhook.


## Listar Webhooks

Você pode listar todos os webhooks registrados usando a função `find` do objeto `intopays.webhooks`.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Webhook;

import java.util.List;

public class PesquisarWebhookExample {
    public static void main(String[] args) {
        Intopays intopays = ...;

        try {
            Webhook filtro = new Webhook();
            filtro.setEndpoint("https://exemple.intopays.com/webhooks");

            List<Webhook> encontrados = intopays.webhook.find(filtro);
            for (Webhook w : encontrados) {
                System.out.println("Encontrado: " + w.getId() + " -> " + w.getEndpoint());
            }
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar webhooks: " + e.getMessage());
        }
    }
}

```

#### Retorno:

- `Array<Webhook>`: Lista de objetos que representam webhooks.

## Pesquisar Webhooks

Você também pode pesquisar webhooks por endpoint usando a função `find` do objeto `intopays.webhooks`.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;
import com.intopays.sdk.core.models.Webhook;

import java.util.List;

public class PesquisarWebhookExample {
    public static void main(String[] args) {
        Intopays intopays = ...;

        try {
            Webhook filtro = new Webhook();
            filtro.setEndpoint("https://exemple.intopays.com/webhooks");

            List<Webhook> encontrados = intopays.webhook.find(filtro);
            for (Webhook w : encontrados) {
                System.out.println("Encontrado: " + w.getId() + " -> " + w.getEndpoint());
            }
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar webhooks: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `endpoint`: Endpoint a ser especificado durante a consulta..

#### Retorno:

- `List<Webhook>`: Lista de objetos que representam webhooks.

## Excluir Webhooks

Você pode excluir um webhook usando a função `delete` do objeto `intopays.webhooks`.

##### Exemplo de Uso

```java
import com.intopays.sdk.Intopays;

public class DeletarWebhookExample {
    public static void main(String[] args) {
        Intopays intopays = ...;

        try {
            int webhookId = 123;
            intopays.webhook.delete(webhookId);
            System.out.println("Webhook excluído com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao excluir webhook: " + e.getMessage());
        }
    }
}

```

#### Parâmetros:

- `webhookId`: ID do webhook a ser deletado.

#### Retorno:

- `void`: Sem retorno após a exclusão do webhook

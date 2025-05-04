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
    <version>v1.0.0</version>
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
  - [Webhook Signature](#webhook-signature)
  - [Recebimento de Evento de Boleto via Webhook](#recebimento-de-evento-de-boleto-via-webhook)
  - [Recebimento de Evento PIX via Webhook](#recebimento-de-evento-pix-via-webhook)

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


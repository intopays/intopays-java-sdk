package com.intopays.sdk.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.intopays.sdk.Intopays;
import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.enums.IntegrationEnum;
import com.intopays.sdk.core.enums.PaymentStatusEnum;
import com.intopays.sdk.core.enums.StateEnum;
import com.intopays.sdk.core.models.Boleto;
import com.intopays.sdk.infra.config.Environment;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoletoServiceTest {
    private Boleto createdBoleto;
    private Intopays intopays;
   
    @BeforeEach
    public void setUp() {
        this.intopays = new Intopays(new IntopaysConstructor(
            Environment.get(EnvironmentTypeEnum.TEST).getToken(), 
            EnvironmentTypeEnum.TEST
        ));
    }

    @Test
    @Order(1)
    public void shouldCreateBoletoSuccessfully() throws IOException {
        Boleto boleto = new Boleto();
        boleto.setAmount(new BigDecimal("10.01")); // R$10,00
        boleto.setDueDate(Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        boleto.setPayerName("Lucas Lopes");
        boleto.setPayerDocument("12345678901");
        boleto.setPayerEmail("lucas@example.com");
        boleto.setPayerZipCode("12345678");
        boleto.setPayerAddress("Rua Exemplo");
        boleto.setPayerNumber("123");
        boleto.setPayerNeighborhood("Bairro");
        boleto.setPayerCity("Cidade");
        boleto.setPayerState(StateEnum.SP);
        boleto.setIntegrationType(IntegrationEnum.SICOOB);
        boleto.setDaysAfterDueDateForCancellation(30);
        Boleto result = this.intopays.boleto.create(boleto);
        this.createdBoleto = result;

        assertNotNull(result);
        assertEquals(boleto.getAmount(), result.getAmount());
        assertEquals(boleto.getPayerName(), result.getPayerName());
    }

    @Test
    @Order(2)
    public void shouldSearchBoletoSuccessfully() throws IOException {
        Boleto filter = new Boleto();
        filter.setId(this.createdBoleto.getId());

        List<Boleto> results = this.intopays.boleto.search(filter);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(createdBoleto.getId(), results.get(0).getId());
    }

    @Test
    @Order(3)
    public void shouldFindBoletoSuccessfully() throws IOException {
        String id = String.valueOf(createdBoleto.getId());

        Boleto found = this.intopays.boleto.find(id);

        assertNotNull(found);
        assertEquals(createdBoleto.getId(), found.getId());
    }

    @Test
    @Order(4)
    public void shouldFailToFindBoletoWithInvalidId() {
        String invalidId = UUID.randomUUID().toString();

        assertThrows(IOException.class, () -> {
            this.intopays.boleto.find(invalidId);
        });
    }

    @Test
    @Order(5)
    public void shouldCancelBoletoSuccessfully() throws IOException {
        String id = String.valueOf(createdBoleto.getId());

        Boleto canceled = this.intopays.boleto.cancel(id);

        assertNotNull(canceled);
        assertEquals(PaymentStatusEnum.PROCESSING, canceled.getStatus()); // Adjust based on your enum
    }

    @Test
    @Order(6)
    public void shouldFailToCancelBoletoWithInvalidId() {
        String invalidId = UUID.randomUUID().toString();

        assertThrows(IOException.class, () -> {
            this.intopays.boleto.cancel(invalidId);
        });
    }
}

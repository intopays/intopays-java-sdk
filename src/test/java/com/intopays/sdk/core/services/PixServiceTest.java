package com.intopays.sdk.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.app.factories.PixFactory;
import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.enums.IntegrationEnum;
import com.intopays.sdk.core.models.Pix;
import com.intopays.sdk.core.models.PixInfo;
import com.intopays.sdk.infra.config.Environment;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PixServiceTest {

    private PixService pixService;
    private Pix createdPix;

    @BeforeEach
    public void setUp() {
    	IntopaysConstructor config = new IntopaysConstructor(Environment.get(EnvironmentTypeEnum.TEST).getToken(), EnvironmentTypeEnum.TEST);
        pixService = PixFactory.createPixService(config);
    }

    @Test
    @Order(1)
    public void shouldCreatePixSuccessfully() throws IOException {
        // Arrange
        Pix expectedPix = new Pix();

        expectedPix.setCalendarExpiration(86400);
        expectedPix.setDebtorName("Lucas Lopes");
        expectedPix.setDebtorDocument("12345678901");
        expectedPix.setAmountOriginal("10.99");
        expectedPix.setAmountModificationType(0);
        expectedPix.setPayerRequest("Cobrança de serviço");
        
        PixInfo pixInfo = new PixInfo();
        pixInfo.setName("Campo 1");
        pixInfo.setValue("Informação Adicional do PSP-Recebedor");
        
        expectedPix.setAdditionalInfos(Arrays.asList(pixInfo));
        expectedPix.setIntegrationType(IntegrationEnum.SICOOB);
        
        // Act
        Pix receivedPix = this.pixService.create(expectedPix);
        this.createdPix = receivedPix;  // Storing for further tests
        // Assert
        assertNotNull(receivedPix);
        assertEquals(expectedPix.getAmountOriginal(), receivedPix.getAmountOriginal());
    }

    @Test
    @Order(2)
    public void shouldSearchPixSuccessfully() throws IOException {
        // Act
        List<Pix> foundPixList = this.pixService.search(this.createdPix);

        // Assert
        assertTrue(foundPixList.size() > 0);
        assertEquals(this.createdPix.getTransactionId(), foundPixList.get(0).getTransactionId());
    }

    @Test
    @Order(3)
    public void shouldFindPixSuccessfully() throws IOException {
        // Arrange
        String id = String.valueOf(createdPix.getId());

        // Act
        Pix foundPix = this.pixService.find(id);

        // Assert
        assertNotNull(foundPix);
        assertEquals(id, String.valueOf(foundPix.getId()));
    }

    @Test
    @Order(4)
    public void shouldFailToFindPixWithInvalidId() throws IOException {
        // Arrange
        String invalidId = UUID.randomUUID().toString();

        // Act & Assert
        assertThrows(IOException.class, () -> {
            this.pixService.find(invalidId);
        });
    }
}

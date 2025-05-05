package com.intopays.sdk.core.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intopays.sdk.core.models.Boleto;
import com.intopays.sdk.infra.http.BoletoRemote;
import com.intopays.sdk.infra.http.response.PageResponse;

/**
 * Serviço responsável pela lógica de negócios relacionada aos boletos.
 */
public class BoletoService {
    private final BoletoRemote remote;

    /**
     * Construtor da classe BoletoService.
     *
     * @param remote Instância da classe BoletoRemote responsável pela comunicação com o servidor.
     */
    public BoletoService(BoletoRemote remote) {
        this.remote = remote;
    }

    /**
     * Cria um novo boleto no sistema.
     *
     * @param data Dados necessários para a criação do boleto.
     * @return O objeto Boleto criado.
     * @throws IOException Caso ocorra erro na requisição.
     */
    public Boleto create(Boleto data) throws IOException {
        return this.remote.create(data);
    }

    /**
     * Busca uma lista de boletos com base em dados de filtro fornecidos.
     *
     * @param data Dados para filtrar os boletos (opcional).
     * @return Lista de boletos que atendem aos critérios fornecidos.
     * @throws IOException Caso ocorra erro na requisição.
     */
    public List<Boleto> search(Boleto data) throws IOException {
        Map<String, String> queryParams = new HashMap<>();

        if (data.getId() > 0) queryParams.put("id", String.valueOf(data.getId()));
        if (data.getAmount().compareTo(BigDecimal.ZERO) > 0) queryParams.put("amount", String.valueOf(data.getAmount()));
        if (data.getDueDate() != null) queryParams.put("dueDate", data.getDueDate().toString());
        if (data.getDaysAfterDueDateForCancellation() > 0) queryParams.put("daysAfterDueDateForCancellation", String.valueOf(data.getDaysAfterDueDateForCancellation()));
        if (data.getPayerDocument() != null) queryParams.put("payerDocument", data.getPayerDocument());
        if (data.getPayerName() != null) queryParams.put("payerName", data.getPayerName());
        if (data.getPayerEmail() != null) queryParams.put("payerEmail", data.getPayerEmail());
        if (data.getPayerPhone() != null) queryParams.put("payerPhone", data.getPayerPhone());
        if (data.getPayerZipCode() != null) queryParams.put("payerZipCode", data.getPayerZipCode());
        if (data.getPayerNumber() != null) queryParams.put("payerNumber", data.getPayerNumber());
        if (data.getPayerComplement() != null) queryParams.put("payerComplement", data.getPayerComplement());
        if (data.getPayerNeighborhood() != null) queryParams.put("payerNeighborhood", data.getPayerNeighborhood());
        if (data.getPayerCity() != null) queryParams.put("payerCity", data.getPayerCity());
        if (data.getPayerState() != null) queryParams.put("payerState", data.getPayerState().name());
        if (data.getPayerAddress() != null) queryParams.put("payerAddress", data.getPayerAddress());

        if (data.getMessageLine1() != null) queryParams.put("messageLine1", data.getMessageLine1());
        if (data.getMessageLine2() != null) queryParams.put("messageLine2", data.getMessageLine2());

        if (data.getDiscount1Code() != null) queryParams.put("discount1Code", data.getDiscount1Code().name());
        if (data.getDiscount1Rate() > 0) queryParams.put("discount1Rate", String.valueOf(data.getDiscount1Rate()));
        if (data.getDiscount1Value() > 0) queryParams.put("discount1Value", String.valueOf(data.getDiscount1Value()));
        if (data.getDiscount1Date() != null) queryParams.put("discount1Date", data.getDiscount1Date().toString());

        if (data.getDiscount2Code() != null) queryParams.put("discount2Code", data.getDiscount2Code().name());
        if (data.getDiscount2Rate() > 0) queryParams.put("discount2Rate", String.valueOf(data.getDiscount2Rate()));
        if (data.getDiscount2Value() > 0) queryParams.put("discount2Value", String.valueOf(data.getDiscount2Value()));
        if (data.getDiscount2Date() != null) queryParams.put("discount2Date", data.getDiscount2Date().toString());

        if (data.getFineCode() != null) queryParams.put("fineCode", data.getFineCode().name());
        if (data.getFineDate() != null) queryParams.put("fineDate", data.getFineDate().toString());
        if (data.getFineRate() > 0) queryParams.put("fineRate", String.valueOf(data.getFineRate()));
        if (data.getFineValue() > 0) queryParams.put("fineValue", String.valueOf(data.getFineValue()));

        if (data.getInterestCode() != null) queryParams.put("interestCode", data.getInterestCode().name());
        if (data.getInterestDate() != null) queryParams.put("interestDate", data.getInterestDate().toString());
        if (data.getInterestRate() > 0) queryParams.put("interestRate", String.valueOf(data.getInterestRate()));
        if (data.getInterestValue() > 0) queryParams.put("interestValue", String.valueOf(data.getInterestValue()));

        if (data.getFinalBeneficiaryName() != null) queryParams.put("finalBeneficiaryName", data.getFinalBeneficiaryName());
        if (data.getFinalBeneficiaryDocument() != null) queryParams.put("finalBeneficiaryDocument", data.getFinalBeneficiaryDocument());
        if (data.getFinalBeneficiaryZipCode() != null) queryParams.put("finalBeneficiaryZipCode", data.getFinalBeneficiaryZipCode());
        if (data.getFinalBeneficiaryAddress() != null) queryParams.put("finalBeneficiaryAddress", data.getFinalBeneficiaryAddress());
        if (data.getFinalBeneficiaryNeighborhood() != null) queryParams.put("finalBeneficiaryNeighborhood", data.getFinalBeneficiaryNeighborhood());
        if (data.getFinalBeneficiaryCity() != null) queryParams.put("finalBeneficiaryCity", data.getFinalBeneficiaryCity());
        if (data.getFinalBeneficiaryState() != null) queryParams.put("finalBeneficiaryState", data.getFinalBeneficiaryState().name());

        if (data.getBarcode() != null) queryParams.put("barcode", data.getBarcode());
        if (data.getDigitableLine() != null) queryParams.put("digitableLine", data.getDigitableLine());
        if (data.getQrcode() != null) queryParams.put("qrcode", data.getQrcode());
        if (data.getReferenceCode() != null) queryParams.put("referenceCode", data.getReferenceCode());
        if (data.getIntegrationBankingCode() != null) queryParams.put("integrationBankingCode", data.getIntegrationBankingCode());
        if (data.getIntegrationType() != null) queryParams.put("integrationType", data.getIntegrationType().name());
        if (data.getStatus() != null) queryParams.put("status", data.getStatus().name());

        PageResponse<Boleto> response = this.remote.search(queryParams);
        return response.getBody();
    }

    /**
     * Encontra um boleto específico pelo seu ID.
     *
     * @param id ID do boleto a ser buscado.
     * @return O objeto Boleto encontrado.
     * @throws IOException Caso ocorra erro na requisição.
     */
    public Boleto find(String id) throws IOException {
        return this.remote.find(id);
    }

    /**
     * Anula um boleto específico.
     *
     * @param id ID do boleto a ser anulado.
     * @return Retorna verdadeiro se o boleto foi anulado com sucesso.
     * @throws IOException Caso ocorra erro na requisição.
     */
    public Boleto cancel(String id) throws IOException {
        return this.remote.cancel(id);
    }
}

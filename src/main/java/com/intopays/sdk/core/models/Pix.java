package com.intopays.sdk.core.models;

import java.util.List;

import com.intopays.sdk.core.enums.IntegrationEnum;
import com.intopays.sdk.core.enums.PixTransactionStatusEnum;

/**
 * Representa uma solicitação e resposta de integração com o sistema de pagamento via Pix.
 */
public class Pix {

    // Request
    /**
     * Tempo de expiração do Pix em segundos.
     */
    private long calendarExpiration;

    /**
     * Nome do pagador (devedor).
     */
    private String debtorName;

    /**
     * Documento do pagador (CPF ou CNPJ).
     */
    private String debtorDocument;

    /**
     * Valor original da cobrança em formato string (ex: "150.00").
     */
    private String amountOriginal;

    /**
     * Tipo de modificação permitida no valor:
     * - `0`: Valor fixo (sem alteração)
     * - `1`: Permite alteração pelo pagador
     */
    private int amountModificationType;

    /**
     * Mensagem adicional visível ao pagador.
     */
    private String payerRequest;

    /**
     * Lista de informações adicionais visíveis no comprovante de pagamento Pix.
     * Cada item representa uma informação nomeada como "chave: valor".
     */
    private List<PixInfo> additionalInfos;

    /**
     * Instituição financeira utilizada na integração Pix.
     * Pode ser: SICOOB, SICREDI, INTER, SANTANDER, BANCODOBRASIL, BRADESCO ou ITAU.
     */
    private IntegrationEnum integrationType;

    // Response
    /**
     * ID interno da transação Pix.
     */
    private Long id;

    /**
     * Chave Pix utilizada na cobrança (e-mail, CPF, CNPJ, telefone ou chave aleatória).
     */
    private String pixKey;

    /**
     * Status atual da cobrança Pix.
     * Pode ser: ACTIVE, COMPLETED, REMOVED_BY_USER_RECEIVER ou REMOVED_BY_PSP.
     */
    private PixTransactionStatusEnum status;

    /**
     * Revisão atual da cobrança Pix (utilizado em atualizações).
     */
    private Integer revision;

    /**
     * ID da localização gerada pelo PSP (Provedor de Serviços de Pagamento).
     */
    private Long locId;

    /**
     * URL da localização da cobrança (geralmente um link QR Code).
     */
    private String locLocation;

    /**
     * Tipo da localização gerada (ex: `cob`, `cobv`, etc).
     */
    private String locType;

    /**
     * Endereço completo da cobrança Pix (equivalente ao QR Code dinâmico).
     */
    private String location;

    /**
     * Identificador da transação gerado pelo cliente (TxID).
     */
    private String transactionId;

    // Constructor
    public Pix() {}

    public Pix(long calendarExpiration, String debtorName, String debtorDocument, String amountOriginal,
               int amountModificationType, String payerRequest, List<PixInfo> additionalInfos,
               IntegrationEnum integrationType) {
        this.calendarExpiration = calendarExpiration;
        this.debtorName = debtorName;
        this.debtorDocument = debtorDocument;
        this.amountOriginal = amountOriginal;
        this.amountModificationType = amountModificationType;
        this.payerRequest = payerRequest;
        this.additionalInfos = additionalInfos;
        this.integrationType = integrationType;
    }

    // Getters and Setters
    public long getCalendarExpiration() {
        return calendarExpiration;
    }

    public void setCalendarExpiration(long calendarExpiration) {
        this.calendarExpiration = calendarExpiration;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getDebtorDocument() {
        return debtorDocument;
    }

    public void setDebtorDocument(String debtorDocument) {
        this.debtorDocument = debtorDocument;
    }

    public String getAmountOriginal() {
        return amountOriginal;
    }

    public void setAmountOriginal(String amountOriginal) {
        this.amountOriginal = amountOriginal;
    }

    public int getAmountModificationType() {
        return amountModificationType;
    }

    public void setAmountModificationType(int amountModificationType) {
        this.amountModificationType = amountModificationType;
    }

    public String getPayerRequest() {
        return payerRequest;
    }

    public void setPayerRequest(String payerRequest) {
        this.payerRequest = payerRequest;
    }

    public List<PixInfo> getAdditionalInfos() {
        return additionalInfos;
    }

    public void setAdditionalInfos(List<PixInfo> additionalInfos) {
        this.additionalInfos = additionalInfos;
    }

    public IntegrationEnum getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(IntegrationEnum integrationType) {
        this.integrationType = integrationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public PixTransactionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PixTransactionStatusEnum status) {
        this.status = status;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getLocLocation() {
        return locLocation;
    }

    public void setLocLocation(String locLocation) {
        this.locLocation = locLocation;
    }

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Pix{" +
                "calendarExpiration=" + calendarExpiration +
                ", debtorName='" + debtorName + '\'' +
                ", debtorDocument='" + debtorDocument + '\'' +
                ", amountOriginal='" + amountOriginal + '\'' +
                ", amountModificationType=" + amountModificationType +
                ", payerRequest='" + payerRequest + '\'' +
                ", additionalInfos=" + additionalInfos +
                ", integrationType=" + integrationType +
                ", id=" + id +
                ", pixKey='" + pixKey + '\'' +
                ", status=" + status +
                ", revision=" + revision +
                ", locId=" + locId +
                ", locLocation='" + locLocation + '\'' +
                ", locType='" + locType + '\'' +
                ", location='" + location + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}

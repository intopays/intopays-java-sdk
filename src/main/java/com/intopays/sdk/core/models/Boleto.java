package com.intopays.sdk.core.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.intopays.sdk.core.enums.DiscountEnum;
import com.intopays.sdk.core.enums.FineTypeEnum;
import com.intopays.sdk.core.enums.IntegrationEnum;
import com.intopays.sdk.core.enums.InterestEnum;
import com.intopays.sdk.core.enums.PaymentStatusEnum;
import com.intopays.sdk.core.enums.StateEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Boleto {

    /** Identificador único do boleto */
    private long id;

    /** Valor do boleto em centavos */
    private long amount;

    /** Data de vencimento do boleto */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date dueDate;

    /** Dias após o vencimento para cancelamento automático */
    private int daysAfterDueDateForCancellation;

    /** CPF ou CNPJ do pagador */
    private String payerDocument;

    /** Nome completo do pagador */
    private String payerName;

    /** E-mail do pagador */
    private String payerEmail;

    /** Telefone do pagador */
    private String payerPhone;

    /** CEP do pagador */
    private String payerZipCode;

    /** Número do endereço do pagador */
    private String payerNumber;

    /** Complemento do endereço do pagador */
    private String payerComplement;

    /** Bairro do pagador */
    private String payerNeighborhood;

    /** Cidade do pagador */
    private String payerCity;

    /** Estado (UF) do pagador */
    private StateEnum payerState;

    /** Endereço completo do pagador */
    private String payerAddress;

    /** Mensagem personalizada (linha 1) */
    private String messageLine1;

    /** Mensagem personalizada (linha 2) */
    private String messageLine2;

    /** Tipo de desconto aplicado */
    private DiscountEnum discount1Code = DiscountEnum.NO_DISCOUNT;

    /** Percentual do primeiro desconto */
    private double discount1Rate = 0;

    /** Valor fixo do primeiro desconto */
    private double discount1Value = 0;

    /** Data limite para o primeiro desconto */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date discount1Date;

    /** Tipo de desconto aplicado */
    private DiscountEnum discount2Code = DiscountEnum.NO_DISCOUNT;

    /** Percentual do segundo desconto */
    private double discount2Rate = 0;

    /** Valor fixo do segundo desconto */
    private double discount2Value = 0;

    /** Data limite para o segundo desconto */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date discount2Date;

    /** Tipo de multa a ser aplicada */
    private FineTypeEnum fineCode = FineTypeEnum.NO_FINE;

    /** Data de início da multa */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date fineDate;

    /** Percentual da multa */
    private double fineRate = 0;

    /** Valor fixo da multa */
    private double fineValue = 0;

    /** Tipo de juros aplicável ao boleto */
    private InterestEnum interestCode = InterestEnum.EXEMPT;

    /** Data de início da cobrança de juros */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
    private Date interestDate;

    /** Percentual dos juros */
    private double interestRate = 0;

    /** Valor fixo dos juros */
    private double interestValue = 0;

    /** Nome do beneficiário final do boleto */
    private String finalBeneficiaryName;

    /** Documento (CPF/CNPJ) do beneficiário final */
    private String finalBeneficiaryDocument;

    /** CEP do beneficiário final */
    private String finalBeneficiaryZipCode;

    /** Endereço do beneficiário final */
    private String finalBeneficiaryAddress;

    /** Bairro do beneficiário final */
    private String finalBeneficiaryNeighborhood;

    /** Cidade do beneficiário final */
    private String finalBeneficiaryCity;

    /** Estado (UF) do beneficiário final */
    private StateEnum finalBeneficiaryState;

    /** Código de barras do boleto */
    private String barcode;

    /** Linha digitável do boleto */
    private String digitableLine;

    /** Código QR para pagamento instantâneo (ex: PIX) */
    private String qrcode;

    /** Código de referência interno do sistema */
    private String referenceCode;

    /** Código de integração com a instituição bancária */
    private String integrationBankingCode;

    /** Tipo de integração utilizada com a instituição bancária */
    private IntegrationEnum integrationType;

    /** Status atual do boleto */
    private PaymentStatusEnum status = PaymentStatusEnum.OPEN;

    public Boleto() {}

    // Getters and Setters for all fields

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getDaysAfterDueDateForCancellation() {
        return daysAfterDueDateForCancellation;
    }

    public void setDaysAfterDueDateForCancellation(int daysAfterDueDateForCancellation) {
        this.daysAfterDueDateForCancellation = daysAfterDueDateForCancellation;
    }

    public String getPayerDocument() {
        return payerDocument;
    }

    public void setPayerDocument(String payerDocument) {
        this.payerDocument = payerDocument;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public String getPayerZipCode() {
        return payerZipCode;
    }

    public void setPayerZipCode(String payerZipCode) {
        this.payerZipCode = payerZipCode;
    }

    public String getPayerNumber() {
        return payerNumber;
    }

    public void setPayerNumber(String payerNumber) {
        this.payerNumber = payerNumber;
    }

    public String getPayerComplement() {
        return payerComplement;
    }

    public void setPayerComplement(String payerComplement) {
        this.payerComplement = payerComplement;
    }

    public String getPayerNeighborhood() {
        return payerNeighborhood;
    }

    public void setPayerNeighborhood(String payerNeighborhood) {
        this.payerNeighborhood = payerNeighborhood;
    }

    public String getPayerCity() {
        return payerCity;
    }

    public void setPayerCity(String payerCity) {
        this.payerCity = payerCity;
    }

    public StateEnum getPayerState() {
        return payerState;
    }

    public void setPayerState(StateEnum payerState) {
        this.payerState = payerState;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getMessageLine1() {
        return messageLine1;
    }

    public void setMessageLine1(String messageLine1) {
        this.messageLine1 = messageLine1;
    }

    public String getMessageLine2() {
        return messageLine2;
    }

    public void setMessageLine2(String messageLine2) {
        this.messageLine2 = messageLine2;
    }

    public DiscountEnum getDiscount1Code() {
        return discount1Code;
    }

    public void setDiscount1Code(DiscountEnum discount1Code) {
        this.discount1Code = discount1Code;
    }

    public double getDiscount1Rate() {
        return discount1Rate;
    }

    public void setDiscount1Rate(double discount1Rate) {
        this.discount1Rate = discount1Rate;
    }

    public double getDiscount1Value() {
        return discount1Value;
    }

    public void setDiscount1Value(double discount1Value) {
        this.discount1Value = discount1Value;
    }

    public Date getDiscount1Date() {
        return discount1Date;
    }

    public void setDiscount1Date(Date discount1Date) {
        this.discount1Date = discount1Date;
    }

    public DiscountEnum getDiscount2Code() {
        return discount2Code;
    }

    public void setDiscount2Code(DiscountEnum discount2Code) {
        this.discount2Code = discount2Code;
    }

    public double getDiscount2Rate() {
        return discount2Rate;
    }

    public void setDiscount2Rate(double discount2Rate) {
        this.discount2Rate = discount2Rate;
    }

    public double getDiscount2Value() {
        return discount2Value;
    }

    public void setDiscount2Value(double discount2Value) {
        this.discount2Value = discount2Value;
    }

    public Date getDiscount2Date() {
        return discount2Date;
    }

    public void setDiscount2Date(Date discount2Date) {
        this.discount2Date = discount2Date;
    }

    public FineTypeEnum getFineCode() {
        return fineCode;
    }

    public void setFineCode(FineTypeEnum fineCode) {
        this.fineCode = fineCode;
    }

    public Date getFineDate() {
        return fineDate;
    }

    public void setFineDate(Date fineDate) {
        this.fineDate = fineDate;
    }

    public double getFineRate() {
        return fineRate;
    }

    public void setFineRate(double fineRate) {
        this.fineRate = fineRate;
    }

    public double getFineValue() {
        return fineValue;
    }

    public void setFineValue(double fineValue) {
        this.fineValue = fineValue;
    }

    public InterestEnum getInterestCode() {
        return interestCode;
    }

    public void setInterestCode(InterestEnum interestCode) {
        this.interestCode = interestCode;
    }

    public Date getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(double interestValue) {
        this.interestValue = interestValue;
    }

    public String getFinalBeneficiaryName() {
        return finalBeneficiaryName;
    }

    public void setFinalBeneficiaryName(String finalBeneficiaryName) {
        this.finalBeneficiaryName = finalBeneficiaryName;
    }

    public String getFinalBeneficiaryDocument() {
        return finalBeneficiaryDocument;
    }

    public void setFinalBeneficiaryDocument(String finalBeneficiaryDocument) {
        this.finalBeneficiaryDocument = finalBeneficiaryDocument;
    }

    public String getFinalBeneficiaryZipCode() {
        return finalBeneficiaryZipCode;
    }

    public void setFinalBeneficiaryZipCode(String finalBeneficiaryZipCode) {
        this.finalBeneficiaryZipCode = finalBeneficiaryZipCode;
    }

    public String getFinalBeneficiaryAddress() {
        return finalBeneficiaryAddress;
    }

    public void setFinalBeneficiaryAddress(String finalBeneficiaryAddress) {
        this.finalBeneficiaryAddress = finalBeneficiaryAddress;
    }

    public String getFinalBeneficiaryNeighborhood() {
        return finalBeneficiaryNeighborhood;
    }

    public void setFinalBeneficiaryNeighborhood(String finalBeneficiaryNeighborhood) {
        this.finalBeneficiaryNeighborhood = finalBeneficiaryNeighborhood;
    }

    public String getFinalBeneficiaryCity() {
        return finalBeneficiaryCity;
    }

    public void setFinalBeneficiaryCity(String finalBeneficiaryCity) {
        this.finalBeneficiaryCity = finalBeneficiaryCity;
    }

    public StateEnum getFinalBeneficiaryState() {
        return finalBeneficiaryState;
    }

    public void setFinalBeneficiaryState(StateEnum finalBeneficiaryState) {
        this.finalBeneficiaryState = finalBeneficiaryState;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDigitableLine() {
        return digitableLine;
    }

    public void setDigitableLine(String digitableLine) {
        this.digitableLine = digitableLine;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getIntegrationBankingCode() {
        return integrationBankingCode;
    }

    public void setIntegrationBankingCode(String integrationBankingCode) {
        this.integrationBankingCode = integrationBankingCode;
    }

    public IntegrationEnum getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(IntegrationEnum integrationType) {
        this.integrationType = integrationType;
    }

    public PaymentStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PaymentStatusEnum status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Boleto {" +
                "id=" + id +
                ", amount=" + amount +
                ", dueDate=" + (dueDate != null ? dueDate.toString() : "null") +
                ", daysAfterDueDateForCancellation=" + daysAfterDueDateForCancellation +
                ", payerDocument='" + payerDocument + '\'' +
                ", payerName='" + payerName + '\'' +
                ", payerEmail='" + payerEmail + '\'' +
                ", payerPhone='" + payerPhone + '\'' +
                ", payerZipCode='" + payerZipCode + '\'' +
                ", payerAddress='" + payerAddress + '\'' +
                ", payerState=" + payerState +
                ", messageLine1='" + messageLine1 + '\'' +
                ", messageLine2='" + messageLine2 + '\'' +
                ", discount1Code=" + discount1Code +
                ", discount1Rate=" + discount1Rate +
                ", discount1Value=" + discount1Value +
                ", discount1Date=" + (discount1Date != null ? discount1Date.toString() : "null") +
                ", discount2Code=" + discount2Code +
                ", discount2Rate=" + discount2Rate +
                ", discount2Value=" + discount2Value +
                ", discount2Date=" + (discount2Date != null ? discount2Date.toString() : "null") +
                ", fineCode=" + fineCode +
                ", fineDate=" + (fineDate != null ? fineDate.toString() : "null") +
                ", fineRate=" + fineRate +
                ", fineValue=" + fineValue +
                ", interestCode=" + interestCode +
                ", interestDate=" + (interestDate != null ? interestDate.toString() : "null") +
                ", interestRate=" + interestRate +
                ", interestValue=" + interestValue +
                ", finalBeneficiaryName='" + finalBeneficiaryName + '\'' +
                ", finalBeneficiaryDocument='" + finalBeneficiaryDocument + '\'' +
                ", finalBeneficiaryZipCode='" + finalBeneficiaryZipCode + '\'' +
                ", finalBeneficiaryAddress='" + finalBeneficiaryAddress + '\'' +
                ", finalBeneficiaryNeighborhood='" + finalBeneficiaryNeighborhood + '\'' +
                ", finalBeneficiaryCity='" + finalBeneficiaryCity + '\'' +
                ", finalBeneficiaryState=" + finalBeneficiaryState +
                ", barcode='" + barcode + '\'' +
                ", digitableLine='" + digitableLine + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", referenceCode='" + referenceCode + '\'' +
                ", integrationBankingCode='" + integrationBankingCode + '\'' +
                ", integrationType=" + integrationType +
                ", status=" + status +
                '}';
    }
}


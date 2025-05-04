package com.intopays.sdk.core.enums;

/**
 * Enum que representa os possíveis status de uma transação Pix.
 */
public enum PixTransactionStatusEnum {
    /**
     * Cobrança ativa e disponível para pagamento.
     */
    ACTIVE("ACTIVE"),

    /** 
     * Pagamento realizado com sucesso.
     */
    COMPLETED("COMPLETED"),

    /** 
     * Cobrança removida pelo usuário recebedor.
     */
    REMOVED_BY_USER_RECEIVER("REMOVED_BY_USER_RECEIVER"),

    /** 
     * Cobrança removida automaticamente pelo PSP (Provedor de Serviço de Pagamento). 
     */
    REMOVED_BY_PSP("REMOVED_BY_PSP");

    private final String value;

    PixTransactionStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
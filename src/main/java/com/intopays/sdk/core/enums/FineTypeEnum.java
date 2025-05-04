package com.intopays.sdk.core.enums;

/**
 * Enumeração que define os tipos de multa aplicáveis a boletos em caso de atraso no pagamento.
 */
public enum FineTypeEnum {
    /**
     * Nenhuma multa será aplicada após o vencimento.
     */
    NO_FINE("NO_FINE"),

    /**
     * Um valor fixo será cobrado como multa após o vencimento.
     */
    AMOUNT("AMOUNT"),

    /**
     * Um percentual do valor do boleto será cobrado como multa após o vencimento.
     */
    PERCENT("PERCENT");

    private final String value;

    FineTypeEnum(String value) {
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

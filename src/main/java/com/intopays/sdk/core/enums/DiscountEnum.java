package com.intopays.sdk.core.enums;

/**
 * Enumeração que define os tipos de desconto aplicáveis a uma cobrança.
 */
public enum DiscountEnum {

    /**
     * Nenhum desconto será aplicado.
     */
    NO_DISCOUNT("NO_DISCOUNT"),

    /**
     * Um valor fixo de desconto será aplicado até a data informada.
     */
    FIXED_VALUE_UNTIL_INFORMED_DATE("FIXED_VALUE_UNTIL_INFORMED_DATE"),

    /**
     * Um percentual de desconto será aplicado até a data informada.
     */
    PERCENTAGE_UNTIL_INFORMED_DATE("PERCENTAGE_UNTIL_INFORMED_DATE");

    private final String value;

    DiscountEnum(String value) {
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

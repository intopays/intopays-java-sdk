package com.intopays.sdk.core.enums;

/**
 * Enum que representa os bancos disponíveis para integração.
 * Utilizado para determinar qual instituição bancária está sendo usada
 * na geração de boletos ou cobranças via Pix.
 */
public enum IntegrationEnum {
    /** Banco Sicoob */
    SICOOB("SICOOB"),

    /** Banco Sicredi */
    SICREDI("SICREDI"),

    /** Banco Inter */
    INTER("INTER"),

    /** Banco Santander */
    SANTANDER("SANTANDER"),

    /** Banco do Brasil */
    BANCODOBRASIL("BANCODOBRASIL"),

    /** Banco Bradesco */
    BRADESCO("BRADESCO"),

    /** Banco Itaú */
    ITAU("ITAU");

    private final String value;

    IntegrationEnum(String value) {
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
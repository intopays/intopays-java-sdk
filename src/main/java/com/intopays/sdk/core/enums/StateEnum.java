package com.intopays.sdk.core.enums;

/**
 * Enumeração que define os estados do Brasil.
 */
public enum StateEnum {
    /**
     * Acre
     */
    AC("AC"),

    /**
     * Alagoas
     */
    AL("AL"),

    /**
     * Amapá
     */
    AP("AP"),

    /**
     * Amazonas
     */
    AM("AM"),

    /**
     * Bahia
     */
    BA("BA"),

    /**
     * Ceará
     */
    CE("CE"),

    /**
     * Distrito Federal
     */
    DF("DF"),

    /**
     * Espírito Santo
     */
    ES("ES"),

    /**
     * Goiás
     */
    GO("GO"),

    /**
     * Maranhão
     */
    MA("MA"),

    /**
     * Mato Grosso
     */
    MT("MT"),

    /**
     * Mato Grosso do Sul
     */
    MS("MS"),

    /**
     * Minas Gerais
     */
    MG("MG"),

    /**
     * Pará
     */
    PA("PA"),

    /**
     * Paraíba
     */
    PB("PB"),

    /**
     * Paraná
     */
    PR("PR"),

    /**
     * Pernambuco
     */
    PE("PE"),

    /**
     * Piauí
     */
    PI("PI"),

    /**
     * Rio de Janeiro
     */
    RJ("RJ"),

    /**
     * Rio Grande do Norte
     */
    RN("RN"),

    /**
     * Rio Grande do Sul
     */
    RS("RS"),

    /**
     * Rondônia
     */
    RO("RO"),

    /**
     * Roraima
     */
    RR("RR"),

    /**
     * Santa Catarina
     */
    SC("SC"),

    /**
     * São Paulo
     */
    SP("SP"),

    /**
     * Sergipe
     */
    SE("SE"),

    /**
     * Tocantins
     */
    TO("TO");

    private final String value;

    StateEnum(String value) {
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

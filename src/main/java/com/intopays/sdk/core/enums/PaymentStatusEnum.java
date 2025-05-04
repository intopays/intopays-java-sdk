package com.intopays.sdk.core.enums;

/**
 * Enumeração que define os status de pagamento de um boleto.
 */
public enum PaymentStatusEnum {
    /**
     * O boleto expirou e não pode mais ser pago.
     */
    EXPIRED("EXPIRED"),

    /**
     * O boleto está vencido e ainda não foi pago.
     */
    OVERDUE("OVERDUE"),

    /**
     * O boleto está aberto, aguardando pagamento.
     */
    OPEN("OPEN"),

    /**
     * O boleto foi pago com sucesso.
     */
    PAID("PAID"),

    /**
     * O boleto foi cancelado, não pode ser pago.
     */
    CANCELLED("CANCELLED"),

    /**
     * O pagamento está sendo processado.
     */
    PROCESSING("PROCESSING");

    private final String value;

    PaymentStatusEnum(String value) {
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

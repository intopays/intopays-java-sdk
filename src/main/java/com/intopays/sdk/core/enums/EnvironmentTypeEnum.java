package com.intopays.sdk.core.enums;


/**
 * Enumeração que define os ambientes disponíveis para execução da aplicação.
 */
public enum EnvironmentTypeEnum {
    /**
     * Ambiente de produção.
     */
    PRODUCTION("production"),

    /**
     * Ambiente de desenvolvimento.
     */
    DEVELOPMENT("development"),

    /**
     * Ambiente de teste.
     */
    TEST("test");

    private final String value;

    EnvironmentTypeEnum(String value) {
        this.value = value;
    }

    /**
     * Obtém o valor da enumeração como string.
     * @return Valor correspondente ao tipo de ambiente.
     */
    public String getValue() {
        return value;
    }

    /**
     * Retorna a enumeração correspondente ao valor fornecido.
     * @param value Valor string do tipo de ambiente.
     * @return Enum correspondente.
     * @throws IllegalArgumentException se o valor for inválido.
     */
    public static EnvironmentTypeEnum fromValue(String value) {
        for (EnvironmentTypeEnum type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown environment type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}

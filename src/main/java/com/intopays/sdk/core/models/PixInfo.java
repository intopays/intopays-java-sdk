package com.intopays.sdk.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa uma informação adicional incluída na cobrança Pix.
 * Essas informações aparecem no comprovante de pagamento.
 *
 * Exemplo: instruções, número do pedido, referência, etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PixInfo {

    /**
     * Nome da informação (ex: "Pedido", "Referência").
     * Máximo de 50 caracteres.
     */
    private String name;

    /**
     * Valor da informação (ex: "12345", "PIX2025").
     * Máximo de 200 caracteres.
     */
    private String value;

    /**
     * Construtor opcional que inicializa a classe com os dados fornecidos.
     *
     * @param data Dados opcionais para inicializar a instância.
     */
    public PixInfo() {
    }

    public PixInfo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Método para obter o nome da informação.
     *
     * @return Nome da informação.
     */
    public String getName() {
        return name;
    }

    /**
     * Método para definir o nome da informação.
     *
     * @param name Nome da informação.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método para obter o valor da informação.
     *
     * @return Valor da informação.
     */
    public String getValue() {
        return value;
    }

    /**
     * Método para definir o valor da informação.
     *
     * @param value Valor da informação.
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PixInfo{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

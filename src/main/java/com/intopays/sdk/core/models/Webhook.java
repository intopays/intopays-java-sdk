package com.intopays.sdk.core.models;

/**
 * Representa um webhook configurado para receber notificações de eventos.
 */
public class Webhook {

    /**
     * Identificador único do webhook.
     */
    private int id;

    /**
     * URL de destino onde os eventos serão enviados via HTTP POST.
     */
    private String endpoint;

    /**
     * Assinatura secreta usada para validar a autenticidade das requisições recebidas.
     * Normalmente utilizada para verificar a integridade do payload no recebimento do webhook.
     */
    private String signature;

    public Webhook() {
        // Construtor padrão
    }

    public Webhook(Integer id, String endpoint, String signature) {
        this.id = id != null ? id : 0;
        this.endpoint = endpoint;
        this.signature = signature;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
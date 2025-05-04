package com.intopays.sdk;

import com.intopays.sdk.core.enums.EnvironmentTypeEnum;

/**
 * Classe de configuração para inicializar a SDK Intopays.
 * Contém informações como o token de autenticação e o ambiente (modo).
 */
public class IntopaysConstructor {

    /**
     * Token de autenticação da API Intopays.
     */
    private String token;

    /**
     * Modo de ambiente: produção, desenvolvimento ou teste.
     * O padrão é {@link EnvironmentTypeEnum#PRODUCTION}.
     */
    private EnvironmentTypeEnum mode = EnvironmentTypeEnum.PRODUCTION;

    /**
     * Construtor padrão.
     * Inicializa com token vazio e modo {@link EnvironmentTypeEnum#PRODUCTION}.
     */
    public IntopaysConstructor() {
    }

    /**
     * Construtor que define apenas o token, utilizando o modo padrão {@link EnvironmentTypeEnum#PRODUCTION}.
     *
     * @param token Token de autenticação.
     */
    public IntopaysConstructor(String token) {
        this.token = token;
    }

    /**
     * Construtor completo com token e modo definidos.
     *
     * @param token Token de autenticação.
     * @param mode  Ambiente a ser utilizado (ex: {@link EnvironmentTypeEnum#DEVELOPMENT}).
     */
    public IntopaysConstructor(String token, EnvironmentTypeEnum mode) {
        this.token = token;
        this.mode = mode;
    }

    /**
     * Retorna o token de autenticação.
     *
     * @return Token atual.
     */
    public String getToken() {
        return token;
    }

    /**
     * Define o token de autenticação.
     *
     * @param token Novo token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Retorna o modo de ambiente atual.
     *
     * @return Modo definido.
     */
    public EnvironmentTypeEnum getMode() {
        return mode;
    }

    /**
     * Define o modo de ambiente.
     *
     * @param mode Novo ambiente a ser utilizado.
     */
    public void setMode(EnvironmentTypeEnum mode) {
        this.mode = mode;
    }
}

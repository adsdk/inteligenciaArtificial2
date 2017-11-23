package br.com.ia.problemadamochila.dto;

import java.math.BigDecimal;

public class ParametrosDTO {

    private Integer populacao;
    private Integer iteracoes;
    private Integer intGer;
    private Integer txMutacao;
    private Integer txAceitacao;
    private Integer vlIdeal;
    private Integer itensMochila;
    private Integer pesoMaxMochila;
    private Integer pesoMaxObj;
    private Integer pesoMinObj;
    private BigDecimal vlMaxObj;
    private BigDecimal vlMinObj;

    public ParametrosDTO() {
        //Tamanho da populacao
        populacao = 500;

        //Numero de iteracoes
        iteracoes = 5000;

        //Intervao de gerações
        intGer = 150;

        //Taxa de mutação
        txMutacao = 30;

        //Taxa de aceitacao da mochila
        txAceitacao = 90;

        //Valor ideal da mochila
        vlIdeal = 1500;

        //Numero de itens da mochila
        itensMochila = 15;

        //Peso maximo
        pesoMaxMochila = 20;

        //Peso maximo do objeto
        pesoMaxObj = 5;

        //Peso minimo do objeto
        pesoMinObj = 1;

        //Valor maximo do objeto
        vlMaxObj = BigDecimal.valueOf(100);

        //Valor minimo do objeto
        vlMinObj = BigDecimal.ONE;

    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public Integer getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(Integer iteracoes) {
        this.iteracoes = iteracoes;
    }

    public Integer getIntGer() {
        return intGer;
    }

    public void setIntGer(Integer intGer) {
        this.intGer = intGer;
    }

    public Integer getTxMutacao() {
        return txMutacao;
    }

    public void setTxMutacao(Integer txMutacao) {
        this.txMutacao = txMutacao;
    }

    public Integer getTxAceitacao() {
        return txAceitacao;
    }

    public void setTxAceitacao(Integer txAceitacao) {
        this.txAceitacao = txAceitacao;
    }

    public Integer getVlIdeal() {
        return vlIdeal;
    }

    public void setVlIdeal(Integer vlIdeal) {
        this.vlIdeal = vlIdeal;
    }

    public Integer getItensMochila() {
        return itensMochila;
    }

    public void setItensMochila(Integer itensMochila) {
        this.itensMochila = itensMochila;
    }

    public Integer getPesoMaxMochila() {
        return pesoMaxMochila;
    }

    public void setPesoMaxMochila(Integer pesoMaxMochila) {
        this.pesoMaxMochila = pesoMaxMochila;
    }

    public Integer getPesoMaxObj() {
        return pesoMaxObj;
    }

    public void setPesoMaxObj(Integer pesoMaxObj) {
        this.pesoMaxObj = pesoMaxObj;
    }

    public Integer getPesoMinObj() {
        return pesoMinObj;
    }

    public void setPesoMinObj(Integer pesoMinObj) {
        this.pesoMinObj = pesoMinObj;
    }

    public BigDecimal getVlMaxObj() {
        return vlMaxObj;
    }

    public void setVlMaxObj(BigDecimal vlMaxObj) {
        this.vlMaxObj = vlMaxObj;
    }

    public BigDecimal getVlMinObj() {
        return vlMinObj;
    }

    public void setVlMinObj(BigDecimal vlMinObj) {
        this.vlMinObj = vlMinObj;
    }
}

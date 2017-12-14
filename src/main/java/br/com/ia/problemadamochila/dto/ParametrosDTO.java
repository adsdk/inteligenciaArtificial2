package br.com.ia.problemadamochila.dto;

import java.math.BigDecimal;

public class ParametrosDTO {

    private Integer populacao;
    private Integer iteracoes;
    private Integer intGer;
    private Integer txMutacao;
    private Integer txAceitacao;
    private String utilizaVlIdeal;
    private Integer vlIdeal;
    private Integer itensMochila;
    private BigDecimal pesoMaxMochila;
    private BigDecimal pesoMaxObj;
    private BigDecimal pesoMinObj;
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

        //Utiliza valor ideal para mochila
        utilizaVlIdeal = "S";

        //Valor ideal da mochila
        vlIdeal = 1500;

        //Numero de itens da mochila
        itensMochila = 15;

        //Peso maximo
        pesoMaxMochila = BigDecimal.valueOf(20);

        //Peso maximo do objeto
        pesoMaxObj = BigDecimal.valueOf(2);

        //Peso minimo do objeto
        pesoMinObj = BigDecimal.ONE;

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

    public String getUtilizaVlIdeal() {
        return utilizaVlIdeal;
    }

    public void setUtilizaVlIdeal(String utilizaVlIdeal) {
        this.utilizaVlIdeal = utilizaVlIdeal;
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

    public BigDecimal getPesoMaxMochila() {
        return pesoMaxMochila;
    }

    public void setPesoMaxMochila(BigDecimal pesoMaxMochila) {
        this.pesoMaxMochila = pesoMaxMochila;
    }

    public BigDecimal getPesoMaxObj() {
        return pesoMaxObj;
    }

    public void setPesoMaxObj(BigDecimal pesoMaxObj) {
        this.pesoMaxObj = pesoMaxObj;
    }

    public BigDecimal getPesoMinObj() {
        return pesoMinObj;
    }

    public void setPesoMinObj(BigDecimal pesoMinObj) {
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

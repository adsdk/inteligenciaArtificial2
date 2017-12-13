package br.com.ia.problemadamochila.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResultadoDTO implements Comparable<ResultadoDTO>{
    private Integer iteracao;
    private BigDecimal valor;
    private BigDecimal peso;
    private BigDecimal fitness;
    private List<ObjetoDTO> itens = new ArrayList<>();

    public ResultadoDTO(Integer iteracao, BigDecimal valor, BigDecimal peso, BigDecimal fitness, List<ObjetoDTO> itens) {
        this.iteracao = iteracao;
        this.valor = valor;
        this.peso = peso;
        this.fitness = fitness;
        this.itens = itens;
    }

    public Integer getIteracao() {
        return iteracao;
    }

    public void setIteracao(Integer iteracao) {
        this.iteracao = iteracao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getFitness() {
        return fitness;
    }

    public void setFitness(BigDecimal fitness) {
        this.fitness = fitness;
    }

    public List<ObjetoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ObjetoDTO> itens) {
        this.itens = itens;
    }

    @Override
    public int compareTo(ResultadoDTO resultado) {
        return resultado.getIteracao().compareTo(this.getIteracao());
    }
}

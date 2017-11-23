package br.com.ia.problemadamochila.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class ObjetoDTO {

    private BigDecimal valor;
    private BigDecimal peso;

    public ObjetoDTO(Integer pesoMin, Integer pesoMax, BigDecimal valorMin, BigDecimal valorMax) {
        Random rand = new Random();
        this.valor = new BigDecimal(rand.doubles(valorMin.doubleValue(), valorMax.doubleValue()).iterator().next())
                .setScale(2, RoundingMode.HALF_UP);
        this.peso = new BigDecimal(rand.ints(pesoMin, pesoMax).iterator().next())
                .setScale(2, RoundingMode.HALF_UP);
    }

    public ObjetoDTO(BigDecimal valor, BigDecimal peso) {
        this.valor = valor;
        this.peso = peso;
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
}

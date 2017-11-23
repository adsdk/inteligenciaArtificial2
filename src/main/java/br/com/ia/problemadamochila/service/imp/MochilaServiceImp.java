package br.com.ia.problemadamochila.service.imp;

import br.com.ia.problemadamochila.dto.ObjetoDTO;
import br.com.ia.problemadamochila.dto.MochilaDTO;
import br.com.ia.problemadamochila.dto.ParametrosDTO;
import br.com.ia.problemadamochila.service.MochilaService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("mochilaService")
public class MochilaServiceImp implements MochilaService {

    @Override
    public List<MochilaDTO> geraPopulacaoInicial(ParametrosDTO params) {
        List<MochilaDTO> populacao = new ArrayList<>();

        for (int i = 0; i < params.getPopulacao(); i++) {
            populacao.add(geraMochilaAleatoria(params));
        }

        populacao = calculaFitnessDaPopulacao(populacao, params.getPesoMaxMochila());

        return populacao;
    }

    @Override
    public List<MochilaDTO> calculaFitnessDaPopulacao(List<MochilaDTO> populacao, Integer pesoMax) {
        populacao.stream().forEach((mochila) -> {
            mochila.setFitness(calculaFitnessMochila(mochila, pesoMax));
        });

        return populacao;
    }

    /**
     * Se a mochila extrapola o limite de peso, o peso dela no cálculo do
     * fitness será 20% maior que o das mochilas no padrão O fitness será a
     * razão entre o valor e o peso
     */
    private BigDecimal calculaFitnessMochila(MochilaDTO mochila, Integer pesoMax) {
        BigDecimal fitness;
        BigDecimal peso = mochila.getPeso();

        if (mochila.getPeso().compareTo(BigDecimal.valueOf(pesoMax)) == 1) {
            peso = mochila.getPeso().multiply(new BigDecimal("1.2"));
        }

        fitness = mochila.getValor().divide(peso, 2, RoundingMode.HALF_UP);

        return fitness;
    }

    private MochilaDTO geraMochilaAleatoria(ParametrosDTO params) {
        MochilaDTO mochila = new MochilaDTO();
        List<ObjetoDTO> itensMochila = new ArrayList<>();
        BigDecimal valor = BigDecimal.ZERO;
        BigDecimal peso = BigDecimal.ZERO;

        for (int i = 0; i < params.getItensMochila(); i++) {
            ObjetoDTO item = new ObjetoDTO(params.getPesoMinObj(), params.getPesoMaxObj(), params.getVlMinObj(), params.getVlMaxObj());
            itensMochila.add(item);
            peso = peso.add(item.getPeso());
            valor = valor.add(item.getValor());
        }

        mochila.setItens(itensMochila);
        mochila.setValor(valor);
        mochila.setPeso(peso);

        return mochila;
    }
}

package br.com.ia.problemadamochila.service.imp;

import br.com.ia.problemadamochila.dto.ObjetoDTO;
import br.com.ia.problemadamochila.dto.MochilaDTO;
import br.com.ia.problemadamochila.dto.ParametrosDTO;
import br.com.ia.problemadamochila.service.AlgoritmoGeneticoService;
import br.com.ia.problemadamochila.service.MochilaService;
import br.com.ia.problemadamochila.to.ResultadoTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("algoritmoGeneticoService")
public class AlgoritmoGeneticoServiceImp implements AlgoritmoGeneticoService {

    @Autowired
    private MochilaService mochilaService;

    @Override
    public Set<ResultadoTO> execute(ParametrosDTO form) {
        Set<ResultadoTO> resultado = new TreeSet<>();
        Integer iteracoes = 1;
        BigDecimal txAceitacao = new BigDecimal(form.getTxAceitacao()).divide(new BigDecimal(100));
        BigDecimal aceite = new BigDecimal(form.getVlIdeal()).multiply(txAceitacao);

        List<MochilaDTO> populacao = mochilaService.geraPopulacaoInicial(form);

        MochilaDTO mochilaAceitavel = getMochilaPorAceite(populacao, aceite, form.getPesoMaxMochila());

        if (mochilaAceitavel != null) {
            resultado.add(new ResultadoTO(iteracoes, mochilaAceitavel.getValor(), mochilaAceitavel.getPeso(), mochilaAceitavel.getFitness(), mochilaAceitavel.getItens()));
        }

        while (mochilaAceitavel == null && iteracoes <= form.getIteracoes()) {
            populacao = getListaEvolucao(populacao, form);
            populacao = mochilaService.calculaFitnessDaPopulacao(populacao, form.getPesoMaxMochila());
            mochilaAceitavel = getMochilaPorAceite(populacao, aceite, form.getPesoMaxMochila());

            if (mochilaAceitavel == null) {
                MochilaDTO melhorMochila = getMelhorMochila(populacao);
                resultado.add(new ResultadoTO(iteracoes, melhorMochila.getValor(), melhorMochila.getPeso(), melhorMochila.getFitness(), null));
            } else {
                resultado.add(new ResultadoTO(iteracoes, mochilaAceitavel.getValor(), mochilaAceitavel.getPeso(), mochilaAceitavel.getFitness(), mochilaAceitavel.getItens()));
            }

            iteracoes++;
        }

        return resultado;
    }

    private List<MochilaDTO> getListaEvolucao(List<MochilaDTO> populacao, ParametrosDTO param) {
        Integer qntPais = param.getIntGer();
        List<MochilaDTO> pais = new ArrayList<>();
        BigDecimal fitnessTotal = getFitnessTotal(populacao);

        for (int i = 0; i < qntPais; i++) {
            MochilaDTO pai = getPaiRoleta(populacao, fitnessTotal, param.getPopulacao());
            pais.add(pai);
            populacao.remove(pai);
            fitnessTotal = fitnessTotal.subtract(pai.getFitness());
        }

        List<MochilaDTO> filhos = crossover(pais);

        for (int i = 0; i < filhos.size(); i++) {
            int index = new Random().nextInt(populacao.size());
            populacao.remove(index);
        }

        filhos = mutacao(filhos, param);

        populacao.addAll(filhos);
        populacao.addAll(pais);

        return populacao;
    }

    private List<MochilaDTO> crossover(List<MochilaDTO> pais) {
        List<MochilaDTO> filhos = new ArrayList<>();

        Integer qntPais = pais.size();
        Integer qntFilhos = qntPais / 2;

        for (int i = 0; i < qntFilhos; i++) {
            MochilaDTO filho = new MochilaDTO();
            List<ObjetoDTO> itensFilho = new ArrayList<>();
            MochilaDTO mochilaPai = pais.get(i);
            MochilaDTO mochilaMae = pais.get(i + qntFilhos);

            itensFilho.addAll(mochilaPai.getItens().subList(0, 5));
            itensFilho.addAll(mochilaMae.getItens().subList(5, 10));
            itensFilho.addAll(mochilaPai.getItens().subList(10, 15));

            filho.setItens(itensFilho);

            filhos.add(calculaPesoValorMochila(filho));
        }

        return filhos;
    }

    private List<MochilaDTO> mutacao(List<MochilaDTO> filhos, ParametrosDTO form) {
        List<MochilaDTO> filhosMutados = new ArrayList<>();
        BigDecimal txAceitacao = new BigDecimal(form.getTxMutacao()).divide(new BigDecimal(100));
        BigDecimal qntMutacao = new BigDecimal(filhos.size()).multiply(txAceitacao).setScale(0, RoundingMode.HALF_UP);

        for (int i = 0; i < qntMutacao.intValue(); i++) {
            int index = new Random().nextInt(filhos.size());
            MochilaDTO filhoMutacao = filhos.get(index);
            filhos.remove(filhoMutacao);

            int indexFilho = new Random().nextInt(filhoMutacao.getItens().size());
            List<ObjetoDTO> itens = filhoMutacao.getItens();
            itens.remove(indexFilho);
            itens.add(new ObjetoDTO(form.getPesoMinObj(), form.getPesoMaxObj(), form.getVlMinObj(), form.getVlMaxObj()));
            filhoMutacao.setItens(itens);

            filhosMutados.add(filhoMutacao);
        }

        filhos.addAll(filhosMutados);

        return filhos;
    }

    private MochilaDTO calculaPesoValorMochila(MochilaDTO mochila) {
        BigDecimal peso = BigDecimal.ZERO;
        BigDecimal valor = BigDecimal.ZERO;

        for (ObjetoDTO item : mochila.getItens()) {
            peso = peso.add(item.getPeso());
            valor = valor.add(item.getValor());
        }

        mochila.setPeso(peso);
        mochila.setValor(valor);

        return mochila;
    }

    private MochilaDTO getMochilaPorAceite(List<MochilaDTO> lista, BigDecimal aceiteValor, Integer aceitePeso) {
        Set<MochilaDTO> listaOrdenada = new TreeSet<>(lista);
        MochilaDTO retorno = null;

        for (MochilaDTO mochila : listaOrdenada) {
            if (mochila.getValor().compareTo(aceiteValor) != -1 && mochila.getPeso().compareTo(BigDecimal.valueOf(aceitePeso)) != 1) {
                retorno = mochila;
                break;
            }
        }

        return retorno;
    }

    private MochilaDTO getPaiRoleta(List<MochilaDTO> lista, BigDecimal fitnessTotal, Integer tamanhoPopulacao) {
        Random random = new Random();
        BigDecimal randNum = new BigDecimal(random.nextDouble()).multiply(fitnessTotal).setScale(2, RoundingMode.HALF_UP);
        int i;

        for (i = 0; i < tamanhoPopulacao && randNum.compareTo(BigDecimal.ZERO) > 0; ++i) {
            randNum = randNum.subtract(lista.get(i).getFitness());
        }

        return lista.get(i - 1);
    }

    private BigDecimal getFitnessTotal(List<MochilaDTO> lista) {
        BigDecimal retorno = BigDecimal.ZERO;

        for (MochilaDTO item : lista) {
            retorno = retorno.add(item.getFitness());
        }

        return retorno;
    }

    private MochilaDTO getMelhorMochila(List<MochilaDTO> lista) {
        Set<MochilaDTO> listaOrdenada = new TreeSet<>(lista);
        return listaOrdenada.iterator().next();
    }
}

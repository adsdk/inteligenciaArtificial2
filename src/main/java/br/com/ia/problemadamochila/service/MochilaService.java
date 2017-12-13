package br.com.ia.problemadamochila.service;

import br.com.ia.problemadamochila.dto.MochilaDTO;
import br.com.ia.problemadamochila.dto.ParametrosDTO;
import java.math.BigDecimal;
import java.util.List;

public interface MochilaService {

    List<MochilaDTO> geraPopulacaoInicial(ParametrosDTO params);

    List<MochilaDTO> calculaFitnessDaPopulacao(List<MochilaDTO> populacao, BigDecimal pesoMax);
}

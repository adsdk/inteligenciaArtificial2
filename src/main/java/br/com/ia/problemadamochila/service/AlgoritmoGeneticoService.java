package br.com.ia.problemadamochila.service;

import br.com.ia.problemadamochila.dto.ParametrosDTO;
import br.com.ia.problemadamochila.dto.ResultadoDTO;
import java.util.List;

public interface AlgoritmoGeneticoService {

    List<ResultadoDTO> execute(ParametrosDTO form);
}

package br.com.ia.problemadamochila.service;

import br.com.ia.problemadamochila.dto.ParametrosDTO;
import br.com.ia.problemadamochila.to.ResultadoTO;
import java.util.Set;

public interface AlgoritmoGeneticoService {

    Set<ResultadoTO> execute(ParametrosDTO form);
}

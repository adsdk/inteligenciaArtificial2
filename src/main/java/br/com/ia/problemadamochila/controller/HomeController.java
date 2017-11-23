package br.com.ia.problemadamochila.controller;

import br.com.ia.problemadamochila.dto.ParametrosDTO;
import br.com.ia.problemadamochila.service.AlgoritmoGeneticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private AlgoritmoGeneticoService algoritmoGeneticoService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    private String getIndex(Model model) {
        model.addAttribute("form", new ParametrosDTO());
        return "home";
    }

    @RequestMapping(path = "/busca", method = RequestMethod.POST)
    public String postParametros(@ModelAttribute("form") ParametrosDTO form, Model model) {
        model.addAttribute("resultado", algoritmoGeneticoService.execute(form));
        return "home";
    }
}

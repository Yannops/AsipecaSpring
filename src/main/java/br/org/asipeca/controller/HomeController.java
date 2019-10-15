package br.org.asipeca.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;





@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("/home");
    }

    @GetMapping("/assistencia")
    public ModelAndView loadAssistencias() {
        return new ModelAndView("/assistencia");
    }

    @GetMapping("/missao-visao-valores")
    public ModelAndView loadMissaoVisaoValores() {
        return new ModelAndView("/missaovisaovalores");
    }

    @GetMapping("/balanco")
    public ModelAndView loadBalanco() {
        return new ModelAndView("/balanco");
}

    @GetMapping("/eventos")
    public ModelAndView loadEventos() {
        return new ModelAndView("/eventos");
    }
    
    @GetMapping("/contatos")
    public ModelAndView loadContatos() {
        return new ModelAndView("/contatos");
    }
    
    @GetMapping("/historia")
    public ModelAndView loadHistoria() {
        return new ModelAndView("/historia");
    }
    
    @GetMapping("/equipe")
    public ModelAndView loadEquipe(){
        return new ModelAndView("/equipe");
    }

    @GetMapping("/parceiros")
    public ModelAndView loadParceiros(){
        return new ModelAndView("/parceiros");
    }
    
    @GetMapping("/certificacoes")
    public ModelAndView loadCertificacoes(){
        return new ModelAndView("/certificacoes");
    }

    @GetMapping("/sitemap")
    public ModelAndView loadSiteMap() {
        return new ModelAndView("/sitemap");
    }

    @GetMapping(path = "/sitemap.xml", produces = { MediaType.APPLICATION_XML_VALUE })
    public ModelAndView loadSiteMapXml() {
        return new ModelAndView("/sitemap.xml");
    }
    
}
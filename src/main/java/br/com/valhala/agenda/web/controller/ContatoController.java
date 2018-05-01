package br.com.valhala.agenda.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.valhala.agenda.modelo.Contato;
import br.com.valhala.agenda.service.ContatoService;

@Controller
@RequestMapping(value = "/contato")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String novo(Model model) {
        model.addAttribute("contato", new Contato());
        return "contato/novo.html";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void edita(@RequestBody Contato contato, HttpServletResponse resposta) {
        Boolean existe = service.existe(contato.getId());
        if (!existe) {
            resposta.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            service.salva(contato);
            resposta.setStatus(HttpStatus.OK.value());
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void novo(@RequestBody Contato contato, UriComponentsBuilder uriComponentsBuilder,
            HttpServletResponse resposta) {
        contato = service.salva(contato);
        resposta.setStatus(HttpStatus.CREATED.value());
        resposta.addHeader("Location",
                uriComponentsBuilder.path("/contato/{id}").buildAndExpand(contato.getId()).toUriString());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Contato buscaPorId(@PathVariable("id") Long id, HttpServletResponse resposta) {
        Boolean existe = service.existe(id);
        if (!existe) {
            resposta.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        } else {
            Contato contato = service.buscaPorId(id);
            resposta.setStatus(HttpStatus.OK.value());
            return contato;
        }
    }

    @RequestMapping(value = "/edita", method = RequestMethod.GET)
    public String edita(@RequestParam(name = "id") Long id, Model model) {
        Contato contato = service.buscaPorId(id);
        model.addAttribute("contato", contato);
        return "contato/edita.html";
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public String lista(Model model) {
        List<Contato> contatos = service.lista();
        model.addAttribute("contatos", contatos);
        return "contato/lista.html";
    }

    @RequestMapping(value = "/deleta", method = RequestMethod.GET)
    public String deleta(@RequestParam(name = "id") Long id) {
        service.deleta(id);
        return "redirect:/contato/lista";
    }

}

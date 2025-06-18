package com.mypilas.aplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// O @Controller é uma anotação do Spring que indica que a classe é um controlador
@Controller
@RequestMapping("/")
public class HomeController {
    
    // @RequestMapping é usado para mapear URLs para métodos específicos
    // neste caso, estamos mapeando a URL "/home" para o método home()
    // @RequestMapping(path = "/home", method = RequestMethod.GET)
    // ou simplesmente @GetMapping("/home") poderia ser usado
    // para mapear requisições GET para o método home()
    // O método home() retorna o nome da view "index", que será resolvido pelo ViewResolver
    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "Login";
    }

    @RequestMapping("/cadastro")
    public String cadastro() {
        return "Cadastro";
    }

    @RequestMapping("/sobre")
    public String sobre() {
        return "Sobre";
    }
    
}

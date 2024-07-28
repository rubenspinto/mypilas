package com.mypilas.aplication.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mypilas.aplication.model.StatusTitulo;
import com.mypilas.aplication.model.Titulo;
import com.mypilas.aplication.repository.TitulosRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

  @Autowired
  private TitulosRepository titulosRepository;

  @RequestMapping("/novo")
  public ModelAndView novo() {
    ModelAndView mv = new ModelAndView("CadastroTitulo");
    mv.addObject(new Titulo());
    return mv;
  }

  /**
   * @param titulo
   * rebebe um objeto do tipo título por meio de uma requisição do tipo POST e salva no banco de dados
   * converte os dados recebidos na requisição automaticamente para um objeto
   * @return String
   * retorna o nome de uma view
   */
  
  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {

    ModelAndView mv = new ModelAndView("CadastroTitulo");

    if (errors.hasErrors()) {
      return mv;
    }
    titulosRepository.save(titulo);
    
    mv.addObject("mensagem", "Título salvo com sucesso.");
    return mv;
  }

  @RequestMapping
  public ModelAndView pesquisar() {

    List<Titulo> todosTitulos = titulosRepository.findAll();
    var mv = new ModelAndView("PesquisaTitulos");
    mv.addObject("titulos", todosTitulos);
    return mv;
  }

  @ModelAttribute("todosStatusTitulo")
  public List<StatusTitulo> todosStatusTitulo() {
    return Arrays.asList(StatusTitulo.values());
  }
  
}

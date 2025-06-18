package com.mypilas.aplication.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypilas.aplication.model.StatusTitulo;
import com.mypilas.aplication.model.Titulo;
import com.mypilas.aplication.repository.TituloFilter;
import com.mypilas.aplication.repository.TitulosRepository;
import com.mypilas.aplication.service.CadastroTituloService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/titulos")
public class TituloController {

  private static final String CADASTRO_VIEW = "CadastroTitulo";

  @Autowired
  private TitulosRepository titulosRepository;

  @Autowired
  private CadastroTituloService cadastroTituloService;

  @RequestMapping("/novo")
  public ModelAndView novo() {
    ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
    mv.addObject(new Titulo());
    return mv;
  }

  /**
   * @param titulo
   *               rebebe um objeto do tipo título por meio de uma requisição do
   *               tipo POST e salva no banco de dados
   *               converte os dados recebidos na requisição automaticamente para
   *               um objeto
   * @return String
   *         retorna o nome de uma view
   */

  @RequestMapping(method = RequestMethod.POST)
  public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {

    if (errors.hasErrors()) {
      return CADASTRO_VIEW;
    }

    try {
      cadastroTituloService.salvar(titulo);
      attributes.addFlashAttribute("mensagem", "Título salvo com sucesso");
      return "redirect:/titulos/novo";
    } catch (DataIntegrityViolationException e) {
      errors.rejectValue("dataVencimento", "error.dataVencimento", e.getMessage());
      return CADASTRO_VIEW;
    }
  }

  @RequestMapping
  public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {

    String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();
    List<Titulo> todosTitulos = titulosRepository.findByDescricaoContaining(descricao);

    var mv = new ModelAndView("PesquisaTitulos");
    mv.addObject("titulos", todosTitulos);
    return mv;
  }

  @RequestMapping("{codigo}")
  // Faz um findById automaticamente
  public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
    ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
    mv.addObject(titulo);
    return mv;
  }

  @RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
  public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
    cadastroTituloService.excluir(codigo);
    // titulosRepository.deleteById(codigo);

    attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
    return "redirect:/titulos";
  }

  @RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
  public @ResponseBody String receber(@PathVariable Long codigo) {
    return cadastroTituloService.receber(codigo);
  }

  @ModelAttribute("todosStatusTitulo")
  public List<StatusTitulo> todosStatusTitulo() {
    return Arrays.asList(StatusTitulo.values());
  }

  @GetMapping("/login")
  public String login() {
    return "Login";
  }

  @RequestMapping("/home")
  public String home() {
    return "/index";
  }

  @GetMapping("/cadastro")
  public String cadastro() {
    return "Cadastro";
  }
}

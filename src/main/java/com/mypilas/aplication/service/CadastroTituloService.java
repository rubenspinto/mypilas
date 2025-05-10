package com.mypilas.aplication.service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mypilas.aplication.model.StatusTitulo;
import com.mypilas.aplication.model.Titulo;
import com.mypilas.aplication.repository.TitulosRepository;

@Service
public class CadastroTituloService {

  @Autowired
  private TitulosRepository titulosRepository;
  
  public void salvar(Titulo titulo) {
    try {
      titulosRepository.save(titulo);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("Formato de data inválido");
    }
  }

  public void excluir(Long codigo) {
    titulosRepository.deleteById(codigo);
  }

  public String  receber(Long codigo) {
    Titulo titulo = titulosRepository.findById(codigo)
      .orElseThrow(() -> new RuntimeException("Valor não encontrado"));
    titulo.setStatus(StatusTitulo.RECEBIDO);
    titulosRepository.save(titulo);

    return StatusTitulo.RECEBIDO.getDescricao();
  }
}

package com.mypilas.aplication.model;

import lombok.Getter;

@Getter
public enum StatusTitulo {
  PENDENTE("Pendente"),
  RECEBIDO("Recebido");

  private String descricao;

  StatusTitulo(String descricao) {
    this.descricao = descricao;
  }
}

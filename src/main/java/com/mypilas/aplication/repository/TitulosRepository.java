package com.mypilas.aplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypilas.aplication.model.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long> {
    // Método para buscar um título pela descrição
    public List<Titulo> findByDescricaoContaining(String descricao); 
}

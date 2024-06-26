package com.mypilas.aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypilas.aplication.model.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long> {

}

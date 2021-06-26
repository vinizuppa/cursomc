package com.vinicius.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.cursomc.domain.Cidade;

//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}

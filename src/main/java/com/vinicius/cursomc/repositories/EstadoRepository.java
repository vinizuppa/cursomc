package com.vinicius.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.cursomc.domain.Estado;

//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar,
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}

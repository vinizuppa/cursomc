package com.vinicius.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.cursomc.domain.Endereco;

//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar, referente ao objeto Categoria, que está mapeado com a tabela categoria
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}

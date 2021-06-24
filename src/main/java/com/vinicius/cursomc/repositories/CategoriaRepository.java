package com.vinicius.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinicius.cursomc.domain.Categoria;

//Aqui iremos realizar acesso a dados. Pode ser: Buscar, salvar, deletar, referente ao objeto Categoria, que está mapeado com a tabela categoria
//@Repository é a anotação para indicar o que será um tipo de repository. Necessário mudar de public class para public interface e estender JpaRepository<Categoria, Integer>
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}

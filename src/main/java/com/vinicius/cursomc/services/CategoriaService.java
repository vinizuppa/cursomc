package com.vinicius.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.cursomc.domain.Categoria;
import com.vinicius.cursomc.repositories.CategoriaRepository;

//@Service é a anotação para indicar que esse é um service
@Service
public class CategoriaService {
	
	//Criando uma operação para buscar uma categoria por código
	//O @Autowired, indica que essa dependencia será automaticamente instanciada pelo SPRING. É a Injeção de dependencia.
	@Autowired
	private CategoriaRepository repo;
	public Categoria buscar(Integer id) {
		//essa operação acessa o banco de dados, busca uma categoria com o ID passado, e retorna o objeto pronto, caso não encontre, retorna null
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}

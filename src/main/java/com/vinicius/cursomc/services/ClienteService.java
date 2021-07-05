package com.vinicius.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.cursomc.domain.Cliente;
import com.vinicius.cursomc.repositories.ClienteRepository;
import com.vinicius.cursomc.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class ClienteService {
	
	//Criando uma operação para buscar uma categoria por código
	//O @Autowired, indica que essa dependencia será automaticamente instanciada pelo SPRING. É a Injeção de dependencia.
	@Autowired
	private ClienteRepository repo;
		//essa operação acessa o banco de dados, busca uma categoria com o ID passado, e retorna o objeto pronto, caso não encontre, retorna null
		public Cliente find(Integer id) {
			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
			}

	
}

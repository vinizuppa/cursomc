package com.vinicius.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vinicius.cursomc.domain.Categoria;
import com.vinicius.cursomc.repositories.CategoriaRepository;
import com.vinicius.cursomc.services.exceptions.DataIntegrityException;
import com.vinicius.cursomc.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class CategoriaService {
	
	//Criando uma operação para buscar uma categoria por código
	//O @Autowired, indica que essa dependencia será automaticamente instanciada pelo SPRING. É a Injeção de dependencia.
	@Autowired
	private CategoriaRepository repo;
		//essa operação acessa o banco de dados, busca uma categoria com o ID passado, e retorna o objeto pronto, caso não encontre, retorna null
		public Categoria find(Integer id) {
			Optional<Categoria> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
			}

	//Criando uma operação para adicionar categoria
		public Categoria insert(Categoria obj) {
			obj.setId(null);
			return repo.save(obj);
		}
		
	//Criando uma operação para	atualizar categoria
		public Categoria update(Categoria obj) {
			find(obj.getId()); //Chamando o metodo find para buscar o objeto no banco, e caso o Id já exista, lança uma exceção.
			return repo.save(obj);
		}
		
	//Criando uma operação para	deletar categoria
		public void delete(Integer id) {
			find(id);
			
			//Tratando para ser abortado a operação de delete, quando tiver 1 ou mais produtos cadastrados para a categoria
			try {
				repo.deleteById(id);
			}
			catch (DataIntegrityViolationException e){
				throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
			}
			
		}
		
		public List<Categoria> findAll(){
			return repo.findAll();
		}
}

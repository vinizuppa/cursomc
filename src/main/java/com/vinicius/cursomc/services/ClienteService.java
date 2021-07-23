package com.vinicius.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vinicius.cursomc.domain.Cliente;
import com.vinicius.cursomc.dto.ClienteDTO;
import com.vinicius.cursomc.repositories.ClienteRepository;
import com.vinicius.cursomc.services.exceptions.DataIntegrityException;
import com.vinicius.cursomc.services.exceptions.ObjectNotFoundException;

//@Service é a anotação para indicar que esse é um service
@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
		public Cliente find(Integer id) {
			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
			}
		
				public Cliente update(Cliente obj) {
					Cliente newObj = find(obj.getId()); //Chamando o metodo find para buscar o objeto no banco, e caso o Id já exista, lança uma exceção.
					updateData(newObj, obj);
					return repo.save(newObj);
				}
				
				public void delete(Integer id) {
					find(id);
					
				
					try {
						repo.deleteById(id);
					}
					catch (DataIntegrityViolationException e){
						throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
					}
					
				}
			
				public List<Cliente> findAll(){
					return repo.findAll();
				}
				
				public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
					PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);//Objeto que prepara as informações para fazer a consulta e retorne a página de dados.
					return repo.findAll(pageRequest);
				}
				
				public Cliente fromDTO(ClienteDTO objDto) {
					return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
				}
				
				private void updateData(Cliente newObj, Cliente obj) {
					newObj.setNome(obj.getNome());
					newObj.setEmail(obj.getEmail());
				}
}

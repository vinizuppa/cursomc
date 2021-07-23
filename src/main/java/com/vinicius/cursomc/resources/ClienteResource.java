package com.vinicius.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.cursomc.domain.Cliente;
import com.vinicius.cursomc.dto.ClienteDTO;
import com.vinicius.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	//Configurando para o End-Point ser /cliente/id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//Configurando para o ID da URL passar para a váriavel Id
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		//Declarando objeto para utilizar o metodo buscar da ClienteService, passando o Id.
		Cliente obj = service.find(id);
		
		//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
		return ResponseEntity.ok().body(obj);
	}
	
	//Configurando o metodo PUT para categoria
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto,@PathVariable Integer id){ //Como no Put precisamos trazer os dados e depois salvar, utilizamos o RequestBody e PathVariable
			Cliente obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		//Configurando o metodo DELETE para categoria
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		//Configurando para o ID da URL passar para a váriavel Id
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}	
		
		//Configurando para listar todas categorias
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() {
			List<Cliente> list = service.findAll();
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); // Convertendo cada objeto da lista para DTO
			//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
			return ResponseEntity.ok().body(listDto);
		}
		
		//Configurando para listar categorias por paginação
		@RequestMapping(value = "/page", method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage(
				@RequestParam(value = "page", defaultValue="0") Integer page, 
				@RequestParam(value = "linesPerPage", defaultValue="24")Integer linesPerPage, 
				@RequestParam(value = "orderBy", defaultValue="nome")String orderBy, 
				@RequestParam(value = "direction", defaultValue="ASC")String direction) { 
			Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
			//Retornando que a operação ocorreu com sucesso, retornando o objeto obj que criamos.
			return ResponseEntity.ok().body(listDto);
		}
}

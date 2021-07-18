package com.vinicius.cursomc.dto;

import java.io.Serializable;

import com.vinicius.cursomc.domain.Categoria;

//Criando DTO para categoria, para criar um objeto e ele ter somente os dados que preciso. Nesse caso, para somente listar as categorias, ao invés de trazer os produtos delas também.
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

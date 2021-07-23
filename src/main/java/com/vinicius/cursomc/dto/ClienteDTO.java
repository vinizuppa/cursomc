package com.vinicius.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vinicius.cursomc.domain.Cliente;

//Criando DTO para cçiente, para criar um objeto e ele ter somente os dados que preciso
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório") //Anotação para validação do nome do cliente, definindo que nome é um campo de preenchimento obrigatório
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres") //Anotação que define o tamanho minimo e máximo que pode ter o nome do cliente
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

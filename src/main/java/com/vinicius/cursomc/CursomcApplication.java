package com.vinicius.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vinicius.cursomc.domain.Categoria;
import com.vinicius.cursomc.domain.Cidade;
import com.vinicius.cursomc.domain.Estado;
import com.vinicius.cursomc.domain.Produto;
import com.vinicius.cursomc.repositories.CategoriaRepository;
import com.vinicius.cursomc.repositories.CidadeRepository;
import com.vinicius.cursomc.repositories.EstadoRepository;
import com.vinicius.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Instanciando 2 categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//Instanciando 3 produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//Referenciando quais produtos estão referenciados com categorias
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//Referenciando quais categorias estão referenciados com produtos
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//Salvando Categorias no banco
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		//Salvando produtos no banco
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//-----------------------------------------------------//
		
		//Instanciando 2 estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
				
		//Instanciando 3 cidades
		Cidade c1 = new Cidade (null, "Uberlândia", est1);
		Cidade c2 = new Cidade (null, "São Paulo", est2);
		Cidade c3 = new Cidade (null, "Campinas", est2);
				
		//Referenciando quais estados estão referenciando as cidades.
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		//Salvando Estados no banco
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		//Salvando Cidades no banco
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	}

	
	
}
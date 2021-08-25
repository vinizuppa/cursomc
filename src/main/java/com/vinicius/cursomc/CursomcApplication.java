package com.vinicius.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vinicius.cursomc.domain.Categoria;
import com.vinicius.cursomc.domain.Cidade;
import com.vinicius.cursomc.domain.Cliente;
import com.vinicius.cursomc.domain.Endereco;
import com.vinicius.cursomc.domain.Estado;
import com.vinicius.cursomc.domain.ItemPedido;
import com.vinicius.cursomc.domain.Pagamento;
import com.vinicius.cursomc.domain.PagamentoComBoleto;
import com.vinicius.cursomc.domain.PagamentoComCartao;
import com.vinicius.cursomc.domain.Pedido;
import com.vinicius.cursomc.domain.Produto;
import com.vinicius.cursomc.domain.enums.EstadoPagamento;
import com.vinicius.cursomc.domain.enums.TipoCliente;
import com.vinicius.cursomc.repositories.CategoriaRepository;
import com.vinicius.cursomc.repositories.CidadeRepository;
import com.vinicius.cursomc.repositories.ClienteRepository;
import com.vinicius.cursomc.repositories.EnderecoRepository;
import com.vinicius.cursomc.repositories.EstadoRepository;
import com.vinicius.cursomc.repositories.ItemPedidoRepository;
import com.vinicius.cursomc.repositories.PagamentoRepository;
import com.vinicius.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itempedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Instanciando 2 categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
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
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
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
		
		//-----------------------------------------------------//
		//Instanciando 1 Cliente
		Cliente cli1 = new Cliente (null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));//Instanciando 2 Telefones
		
		//Instanciando 2 endereços
		Endereco e1 = new Endereco (null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		//Indicando qual os endereços do cliente
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//Salvando cliente no banco
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		//Salvando endereços no banco
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		//Instanciando 2 pedidos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");//Mascara de formatação para instanciar data
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		//Instanciando Pagamentos
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));//Associando os pagamentos do cliente
		
		//Salvando pedidos e pagamentos no banco
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		//Instanciando 3 itens de pedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		//Salvando Itens de pedido no banco
		itempedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
	
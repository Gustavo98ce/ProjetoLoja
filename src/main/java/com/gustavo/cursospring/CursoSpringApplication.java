package com.gustavo.cursospring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gustavo.cursospring.domain.Categoria;
import com.gustavo.cursospring.domain.Cidade;
import com.gustavo.cursospring.domain.Cliente;
import com.gustavo.cursospring.domain.Endereco;
import com.gustavo.cursospring.domain.Estado;
import com.gustavo.cursospring.domain.ItemPedido;
import com.gustavo.cursospring.domain.Pagamento;
import com.gustavo.cursospring.domain.PagamentoComBoleto;
import com.gustavo.cursospring.domain.PagamentoComCartao;
import com.gustavo.cursospring.domain.Pedido;
import com.gustavo.cursospring.domain.Produto;
import com.gustavo.cursospring.domain.enums.EstadoPagamento;
import com.gustavo.cursospring.domain.enums.TipoCliente;
import com.gustavo.cursospring.repositories.CategoriaRepository;
import com.gustavo.cursospring.repositories.CidadeRepository;
import com.gustavo.cursospring.repositories.ClienteRepository;
import com.gustavo.cursospring.repositories.EnderecoRepository;
import com.gustavo.cursospring.repositories.EstadoRepository;
import com.gustavo.cursospring.repositories.ItemPedidoRepository;
import com.gustavo.cursospring.repositories.PagamentoRepository;
import com.gustavo.cursospring.repositories.PedidoRepository;
import com.gustavo.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner {

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
	private ItemPedidoRepository itemPedidoRepository;
 
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Estado est1 = new Estado(null, " Minas Gerais");
		Estado est2 = new Estado(null, " São Paulo");
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","123456789",TipoCliente.PESSOAFISICA);
		Endereco e1= new Endereco(null,"Rua Flores","300","apto 203","Jardim","60633-666",cli1,c1);
		Endereco e2= new Endereco(null,"Avenida Matos","105","Sala 800","Centro","12345-344",cli1,c2);
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1= new Pedido(null, sdf.parse("30/09/2019 10:32"),e1, cli1);
		Pedido ped2  = new Pedido(null, sdf.parse("10/10/2019 11:32"), e2, cli1);
		Pagamento pag1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2019 00:00"),null);
		ped2.setPagamento(pag2);
		ItemPedido ip1 = new ItemPedido(ped1, p1,0.00,1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
				
				
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		cli1.getTelefones().addAll((Arrays.asList("85-6666.6666","85-9999.9999")));
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		ped1.getItens().addAll(Arrays.asList(ip1,ip3));
		ped2.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

		
	}

}

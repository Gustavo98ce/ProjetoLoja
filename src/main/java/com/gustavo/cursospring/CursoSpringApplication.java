package com.gustavo.cursospring;

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
import com.gustavo.cursospring.domain.Produto;
import com.gustavo.cursospring.domain.enums.TipoCliente;
import com.gustavo.cursospring.repositories.CategoriaRepository;
import com.gustavo.cursospring.repositories.CidadeRepository;
import com.gustavo.cursospring.repositories.ClienteRepository;
import com.gustavo.cursospring.repositories.EnderecoRepository;
import com.gustavo.cursospring.repositories.EstadoRepository;
import com.gustavo.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

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
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		cli1.getTelefones().addAll((Arrays.asList("85-6666.6666","85-9999.9999")));
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
	}

}

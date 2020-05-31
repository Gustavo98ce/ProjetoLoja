package com.gustavo.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.cursospring.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto,Integer>{
	
	
	

}

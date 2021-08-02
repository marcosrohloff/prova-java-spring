package br.senai.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sc.models.Produto;
import br.senai.sc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> ListarProdutos() {
		return produtoRepository.findAll();
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public void delete(Produto produto) {
		produtoRepository.delete(produto);
	}

	public void produtoFindById(Long id) {
		produtoRepository.findById(id);
	}

}

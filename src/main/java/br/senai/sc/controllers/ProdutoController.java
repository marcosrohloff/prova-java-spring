package br.senai.sc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.senai.sc.models.Cliente;
import br.senai.sc.models.Produto;
import br.senai.sc.services.ProdutoService;
import br.senai.sc.util.FacesUtils;

@Controller
@ViewScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PAGE_PRODUTO = "/public/produto.jsf";

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private Produto produto;

	private List<Produto> produtos = new ArrayList<Produto>();

	public ProdutoController() {
	}

	@PostConstruct
	public void init() {
		produtos = produtoService.ListarProdutos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void salvar() {
		try {
			this.produto = produtoService.salvar(produto);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizado com sucesso!"));
			PrimeFaces.current().ajax().update("formAdd:addProduto", "formList:tblProduto");
			limpar();
			produtos = produtoService.ListarProdutos();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void selecionar(Produto produto) {
		produtoService.produtoFindById(produto.getId());
	}

	public void excluir() {
		try {
			produtoService.delete(produto);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro excluido com sucesso!"));
			limpar();
			init();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Object getRowKey(Produto cli) {
		return cli.getId();
	}

	public void rowSelected(SelectEvent<Produto> event) {
		this.produto = (Produto) event.getObject();
	}

	public void onRowUnselect(UnselectEvent<Produto> event) {
		this.produto = (Produto) event.getObject();
	}

	public void limpar() {
		produto = new Produto();
	}

}

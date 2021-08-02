package br.senai.sc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.senai.sc.models.Cliente;
import br.senai.sc.models.Produto;
import br.senai.sc.models.Venda;
import br.senai.sc.services.ClienteService;
import br.senai.sc.services.ProdutoService;
import br.senai.sc.services.VendaService;

@Controller
@ViewScoped
public class VendaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PAGE_VENDA = "/public/venda.jsf";

	@Autowired
	private VendaService vendaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private Venda venda;

	private List<Venda> vendas = new ArrayList<Venda>();

	private List<SelectItem> listaSelectClientes;
	private List<SelectItem> listaSelectProdutos;

	public VendaController() {
	}

	@PostConstruct
	public void init() {
		vendas = vendaService.ListarVendas();
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<SelectItem> carregaListaClientes() {
		List<Cliente> list = clienteService.ListarClientes();
		listaSelectClientes = new ArrayList<SelectItem>();

		listaSelectClientes.clear();
		for (Cliente cli : list) {
			listaSelectClientes.add(new SelectItem(cli, cli.getNome()));
		}
		return listaSelectClientes;
	}

	public List<SelectItem> carregaListaProdutos() {
		List<Produto> list = produtoService.ListarProdutos();
		listaSelectProdutos = new ArrayList<SelectItem>();

		listaSelectProdutos.clear();
		for (Produto pro : list) {
			listaSelectProdutos.add(new SelectItem(pro, pro.getDescricao()));
		}
		return listaSelectProdutos;
	}

}

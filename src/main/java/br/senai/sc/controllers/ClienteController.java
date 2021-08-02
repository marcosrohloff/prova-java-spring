package br.senai.sc.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;

import br.senai.sc.models.Cliente;
import br.senai.sc.services.ClienteService;
import br.senai.sc.util.FacesUtils;

@Controller
@ViewScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PAGE_CLIENTE = "/public/cliente.jsf";
	private UIForm form;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private Cliente cliente;

	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<SelectItem> listaSelectClientes;
	
	public ClienteController() {
	}

	@PostConstruct
	public void init() {
		clientes = clienteService.ListarClientes();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void salvar() {
		try {
			this.cliente = clienteService.salvar(cliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizado com sucesso!"));
			PrimeFaces.current().ajax().update("formAdd:addCliente", "formList:tblCliente");
			limpar();
			clientes = clienteService.ListarClientes();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void selecionar(Cliente cliente) {
		clienteService.clienteFindById(cliente.getId());
	}

	public void excluir() {
		try {
			clienteService.delete(cliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro excluido com sucesso!"));
			limpar();
			init();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public List<SelectItem> carregaComboClientes() {

		List<Cliente> lista = clienteService.ListarClientes();
		listaSelectClientes = new ArrayList<SelectItem>();

		listaSelectClientes.clear();
		for (Cliente cli : lista) {
			listaSelectClientes.add(new SelectItem(cli, cli.getNome()));
		}
		return listaSelectClientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Object getRowKey(Cliente cli) {
		return cli.getId();
	}

	public void rowSelected(SelectEvent<Cliente> event) {
		this.cliente = (Cliente) event.getObject();
	}

	public void onRowUnselect(UnselectEvent<Cliente> event) {
		this.cliente = (Cliente) event.getObject();
	}

	private void limpar() {
		cliente = new Cliente();
	}	

}

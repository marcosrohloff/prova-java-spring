package br.senai.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sc.models.Cliente;
import br.senai.sc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> ListarClientes() {
		return clienteRepository.findAll();
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Transactional
    public void delete(Cliente cliente) {
    	clienteRepository.delete(cliente);
    }
    
    public void clienteFindById(Long id) {
        clienteRepository.findById(id);
    }

}

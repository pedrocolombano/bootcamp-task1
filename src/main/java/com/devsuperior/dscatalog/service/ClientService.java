package com.devsuperior.dscatalog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ClientDTO;
import com.devsuperior.dscatalog.entities.Client;
import com.devsuperior.dscatalog.repositories.ClientRepository;
import com.devsuperior.dscatalog.service.exception.ResourceNotFoundException;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	@Autowired
	public ClientService(final ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest page) {
		Page<Client> clients = this.clientRepository.findAll(page);
		return clients.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = this.clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
		return new ClientDTO(client);
	}

}

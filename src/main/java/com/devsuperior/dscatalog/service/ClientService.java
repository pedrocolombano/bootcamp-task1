package com.devsuperior.dscatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dscatalog.entities.Client;
import com.devsuperior.dscatalog.repositories.ClientRepository;

@Service
public class ClientService {

	private ClientRepository clientRepository;

	@Autowired
	public ClientService(final ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}

}

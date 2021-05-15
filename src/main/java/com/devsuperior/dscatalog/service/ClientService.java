package com.devsuperior.dscatalog.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.ClientDTO;
import com.devsuperior.dscatalog.entities.Client;
import com.devsuperior.dscatalog.repositories.ClientRepository;
import com.devsuperior.dscatalog.service.exception.DatabaseException;
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
		Client client = this.clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = new Client();
		this.setClientData(dto, client);
		client = this.clientRepository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = this.clientRepository.getOne(id);
			this.setClientData(dto, client);
			client = this.clientRepository.save(client);
			return new ClientDTO(client);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found - " + id);
		}
	}

	public void deleteById(Long id) {
		try {
			this.clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException | IllegalArgumentException e) {
			throw new ResourceNotFoundException("Id not found - " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Database violation");
		}
	}

	private void setClientData(ClientDTO dto, Client client) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
	}

}

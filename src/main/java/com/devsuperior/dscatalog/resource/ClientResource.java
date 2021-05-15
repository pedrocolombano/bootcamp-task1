package com.devsuperior.dscatalog.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Client;
import com.devsuperior.dscatalog.service.ClientService;

@RestController
@RequestMapping(path = "/clients")
public class ClientResource {

	private ClientService clientService;
	
	@Autowired
	public ClientResource(final ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> clients = this.clientService.findAll();
		return ResponseEntity.ok().body(clients);
	}
	
}

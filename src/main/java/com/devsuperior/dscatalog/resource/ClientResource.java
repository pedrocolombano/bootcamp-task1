package com.devsuperior.dscatalog.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.dto.ClientDTO;
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
	public ResponseEntity<Page<ClientDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(name = "direction", defaultValue = "DESC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "income") String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction.toUpperCase()), orderBy);
		Page<ClientDTO> clients = this.clientService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(clients);
	}

}

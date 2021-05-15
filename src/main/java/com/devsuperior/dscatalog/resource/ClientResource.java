package com.devsuperior.dscatalog.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO client = this.clientService.findById(id);
		return ResponseEntity.ok().body(client);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
		dto = this.clientService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) {
		dto = this.clientService.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		this.clientService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}

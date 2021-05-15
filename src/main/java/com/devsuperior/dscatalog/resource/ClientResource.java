package com.devsuperior.dscatalog.resource;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Client;

@RestController
@RequestMapping(path = "/clients")
public class ClientResource {

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		Client c1 = new Client(1L, "Cristiane Silva", "12345678900", new BigDecimal("5000"), Instant.parse("1984-05-26T00:00:00.00Z"), 2);
		Client c2 = new Client(2L, "Augusto Ferreira", "00987654321", new BigDecimal("10000"), Instant.parse("1990-03-08T00:00:00.00Z"), 0);
		List<Client> clients = List.of(c1, c2);
		return ResponseEntity.ok().body(clients);
	}
	
}

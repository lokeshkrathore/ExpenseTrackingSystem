package com.anka.test.Controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anka.test.Domain.Client;
import com.anka.test.Service.ClientService;
import com.anka.test.base.BaseController;

@Controller
public class ClientController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ClientController.class);

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/client")
	public String getClients(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request) {
		log.debug("Request to getClients");
		if (page == null) {
			page = 0;
		}
		Page<Client> clients = clientService.getAllClients(page, PAGE_SIZE);
		request.setAttribute("clients", clients);
		return "client";
	}

	@PostMapping("/client")
	public ResponseEntity<?> createOrUpdateClient(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name") String name, @RequestParam(value = "active") Boolean isActive,
			HttpServletRequest request) {
		log.debug("Requset to create or update client");
		return clientService.createOrUpdateClient(id, name, isActive);
	}

	@DeleteMapping("/client/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		log.debug("request to delete Client : {}", id);
		return clientService.deleteClient(id);
	}
}

package com.anka.test.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anka.test.Domain.Client;
import com.anka.test.Repository.ClientRepository;

@Service
public class ClientService {

	private final Logger log = LoggerFactory.getLogger(ClientService.class);

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Page<Client> getAllClients(int page, int size) {
		log.debug("Reqeust to getAllClients");
		return clientRepository.findAll(new PageRequest(page, size));
	}

	public ResponseEntity<?> createOrUpdateClient(Long id, String name, Boolean isActive) {
		try {
			if (id == null) {
				if (name.length() < 4) {
					return ResponseEntity.badRequest().body("Client name must be atleast 4 characters");
				}
				Client client = new Client();
				client.setName(name);
				client.setIsActive(isActive);
				clientRepository.save(client);
				return ResponseEntity.ok().body("Client created successfully.");
			} else {
				Client existClient = clientRepository.findOne(id);
				if (existClient == null) {
					return ResponseEntity.badRequest().body("Client doesn't exist");
				}
				if (name.length() < 4) {
					return ResponseEntity.badRequest().body("Client name must be atleast 4 characters");
				}
				existClient.setName(name);
				existClient.setIsActive(isActive);
				clientRepository.save(existClient);
				return ResponseEntity.ok().body("Client updated successfully.");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Problem occurs while handling request, " + e.getMessage());
		}
	}

	public ResponseEntity<?> deleteClient(Long id) {
		if (id == null) {
			return ResponseEntity.badRequest().body("Client id can not be null");
		}
		clientRepository.delete(id);
		return ResponseEntity.ok().body("Client deleted successfully.");
	}

	public List<Client> findAllClientByActiveTrue() {
		log.debug("Reqeust to get all active client");
		return clientRepository.findAllByIsActiveTrue();
	}
}

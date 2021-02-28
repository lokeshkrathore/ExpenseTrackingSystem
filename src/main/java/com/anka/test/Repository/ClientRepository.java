package com.anka.test.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anka.test.Domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	List<Client> findAllByIsActiveTrue();
}

package com.anka.test.Service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anka.test.Domain.Client;
import com.anka.test.Domain.Expense;
import com.anka.test.Repository.ClientRepository;
import com.anka.test.Repository.ExpenseRepository;

@Service
public class ExpenseService {

	private final Logger log = LoggerFactory.getLogger(ExpenseService.class);

	private final ExpenseRepository expenseRepository;

	private final ClientRepository clientRepository;

	public ExpenseService(ExpenseRepository expenseRepository, ClientRepository clientRepository) {
		this.expenseRepository = expenseRepository;
		this.clientRepository = clientRepository;
	}

	public Page<Expense> getAllExpenses(Integer page, int pageSize) {
		log.debug("Reqeust to getAllClients");
		return expenseRepository.findAll(new PageRequest(page, pageSize));
	}

	public ResponseEntity<?> createOrUpdateExpense(Long id, String title, String description, String currency,
			String amount, String timesStampOfExpense, Long clientId) {

		Client client = clientRepository.findOne(clientId);
		if (client == null) {
			return ResponseEntity.badRequest().body("Client doesn't exist");
		}

		try {
			if (id == null) {
				Expense expense = new Expense();
				expense.setTitle(title);
				expense.setDescription(description);
				expense.setCurrency(currency);
				expense.setAmount(Double.valueOf(amount));
				expense.setTimesStampOfExpense(LocalDate.parse(timesStampOfExpense));
				expense.setClient(client);
				expenseRepository.save(expense);
				return ResponseEntity.ok().body("Expense created successfully.");
			} else {
				Expense existExpense = expenseRepository.findOne(id);
				if (existExpense == null) {
					return ResponseEntity.badRequest().body("Expense doesn't exist");
				}
				existExpense.setTitle(title);
				existExpense.setDescription(description);
				existExpense.setCurrency(currency);
				existExpense.setAmount(Double.valueOf(amount));
				existExpense.setTimesStampOfExpense(LocalDate.parse(timesStampOfExpense));
				existExpense.setClient(client);
				expenseRepository.save(existExpense);
				return ResponseEntity.ok().body("Expense updated successfully.");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Problem occurs while handling request, " + e.getMessage());
		}
	}

	public ResponseEntity<?> deleteExpense(Long id) {
		if (id == null) {
			return ResponseEntity.badRequest().body("Expense id can not be null");
		}
		expenseRepository.delete(id);
		return ResponseEntity.ok().body("Expense deleted successfully.");
	}

}

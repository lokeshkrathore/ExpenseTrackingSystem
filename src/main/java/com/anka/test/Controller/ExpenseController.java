package com.anka.test.Controller;

import java.time.Instant;
import java.util.List;

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
import com.anka.test.Domain.Expense;
import com.anka.test.Service.ClientService;
import com.anka.test.Service.ExpenseService;
import com.anka.test.base.BaseController;

@Controller
public class ExpenseController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ExpenseController.class);

	private final ExpenseService expenseService;

	private final ClientService clientService;

	public ExpenseController(ExpenseService expenseService, ClientService clientService) {
		this.expenseService = expenseService;
		this.clientService = clientService;
	}

	@GetMapping("/expense")
	public String getExpenses(@RequestParam(value = "page", required = false) Integer page,
			HttpServletRequest request) {
		log.debug("Request to getClients");
		if (page == null) {
			page = 0;
		}
		Page<Expense> expenses = expenseService.getAllExpenses(page, PAGE_SIZE);
		request.setAttribute("expenses", expenses);
		List<Client> clients = clientService.findAllClientByActiveTrue();
		request.setAttribute("clients", clients);
		return "expense";
	}

	@PostMapping("/expense")
	public ResponseEntity<?> createOrUpdateExpense(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "title") String title, @RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "currency") String currency, @RequestParam(value = "amount") String amount,
			@RequestParam(value = "timesStampOfExpense") String timesStampOfExpense,
			@RequestParam(value = "clientId") Long clientId, HttpServletRequest request) {
		log.debug("Requset to create or update Expense");
		return expenseService.createOrUpdateExpense(id, title, description, currency, amount, timesStampOfExpense, clientId);
	}

	@DeleteMapping("/expense/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
		log.debug("request to delete Expense : {}", id);
		return expenseService.deleteExpense(id);
	}
}

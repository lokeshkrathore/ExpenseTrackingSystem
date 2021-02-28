package com.anka.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anka.test.Domain.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
}

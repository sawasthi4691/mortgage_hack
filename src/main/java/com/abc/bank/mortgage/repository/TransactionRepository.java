package com.abc.bank.mortgage.repository;

import com.abc.bank.mortgage.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    public List<Transaction> findByFromAccount(Long accountNumber);
}
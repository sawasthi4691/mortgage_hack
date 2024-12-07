package com.abc.bank.mortgage.service;

import com.abc.bank.mortgage.dtos.TransactionDTO;
import com.abc.bank.mortgage.mapper.TransactionDtoMapper;
import com.abc.bank.mortgage.models.Transaction;
import com.abc.bank.mortgage.repository.TransactionRepository;
import com.abc.bank.mortgage.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionDtoMapper transactionDtoMapper;
    private TransactionUtils transactionUtils;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,TransactionDtoMapper transactionDtoMapper,TransactionUtils transactionUtils) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoMapper = transactionDtoMapper;
        this.transactionUtils = transactionUtils;
    }

    public List<TransactionDTO> getTransactionHistoryByAccount(Long accountId) {
        List<Transaction> transaction = transactionRepository.findByFromAccount(accountId );
        return transactionDtoMapper.transactionDtoMapper(transaction);
    }

    public List<TransactionDTO> getTransactionHistoryByAccountAndDuration(Long accountId, Integer duration) {
        List<Transaction> res = transactionUtils.filterTransactions(transactionRepository.findByFromAccount(accountId), duration);
        return transactionDtoMapper.transactionDtoMapper(res);
    }

}

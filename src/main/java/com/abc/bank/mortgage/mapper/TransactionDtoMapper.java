package com.abc.bank.mortgage.mapper;


import com.abc.bank.mortgage.dtos.TransactionDTO;
import com.abc.bank.mortgage.models.Transaction;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionDtoMapper {

    public List<TransactionDTO> transactionDtoMapper(List<Transaction> transaction) {
        return transaction.stream().map(transaction1 -> TransactionDTO.builder()
                .transactionId(transaction1.getTransactionId())
                .fromAccount(transaction1.getFromAccount())
                .toAccount(transaction1.getToAccount())
                .createdAt(transaction1.getCreatedAt())
                .transactionStatus(transaction1.getTransactionStatus())
                .amount(transaction1.getAmount())
                .remark(transaction1.getRemark())
                .build()).collect(Collectors.toList());
    }
}

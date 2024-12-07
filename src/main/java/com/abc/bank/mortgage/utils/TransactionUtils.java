package com.abc.bank.mortgage.utils;

import com.abc.bank.mortgage.models.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionUtils {

    // Method to filter transactions based on the provided month threshold
    public static List<Transaction> filterTransactions(List<Transaction> transactions, int duration) {
        List<Transaction> filteredList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        transactions.forEach(transaction -> {
            LocalDateTime createdAt = transaction.getCreatedAt();
            long monthsDiff = ChronoUnit.MONTHS.between(createdAt, now);
            if (monthsDiff < duration) {
                filteredList.add(transaction);
            }
        });
        return filteredList;
    }
}

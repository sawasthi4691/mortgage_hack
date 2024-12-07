package com.abc.bank.mortgage.controller;

import com.abc.bank.mortgage.Exception.TransactionException;
import com.abc.bank.mortgage.dtos.TransactionDTO;
import com.abc.bank.mortgage.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {


    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> TransactionHistoryByAccount(@PathVariable("accountId") String accountId) {
        if (!accountId.matches("\\d+")) {
            throw new TransactionException("Not a valid Number", "700");
        }
        return new ResponseEntity<>(transactionService.getTransactionHistoryByAccount(Long.valueOf(accountId)), HttpStatus.OK);
    }


    @GetMapping("account/{accountId}/duration/{duration}")
    public ResponseEntity<List<TransactionDTO>> TransactionHistoryByAccountAndDuration(
           @PathVariable("accountId") String accountId,
           @PathVariable("duration") Integer duration) {
        if (!accountId.matches("\\d+")) {
            throw new TransactionException("Not a valid Number", "100");
        }
        if (duration < 1 || (duration > 1 && duration > 12)) {
            throw new TransactionException("Not a valid Duration", "101");
        }
        return new ResponseEntity<>(transactionService.getTransactionHistoryByAccountAndDuration(Long.valueOf(accountId), duration), HttpStatus.OK);
    }
}

package com.abc.bank.mortgage.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.abc.bank.mortgage.dtos.TransactionDTO;
import com.abc.bank.mortgage.mapper.TransactionDtoMapper;
import com.abc.bank.mortgage.models.Transaction;
import com.abc.bank.mortgage.repository.TransactionRepository;
import com.abc.bank.mortgage.utils.TransactionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionDtoMapper transactionDtoMapper;

    @Mock
    private TransactionUtils transactionUtils;

    @Autowired
    private TransactionService transactionService;

    private Long accountId;
    private List<Transaction> transactionList;
    private List<TransactionDTO> transactionDTOList;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Setup test data
        accountId = 1L;
        transactionList = new ArrayList<>();
        transactionDTOList = new ArrayList<>();

        // Mock behavior for transactionRepository
        Transaction transaction = new Transaction();
        transaction.setFromAccount(accountId);
        transaction.setCreatedAt(LocalDateTime.now());
        transactionList.add(transaction);

        // Mock behavior for transactionDtoMapper
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .transactionId(1L) // provide suitable test values here
                .fromAccount(accountId)
                .toAccount(1L)
                .amount(1000L)
                .createdAt(LocalDateTime.now())
                .transactionStatus("STATUS")
                .remark("Test Remark")
                .build();
        transactionDTOList.add(transactionDTO);
    }

    @Test
    void testGetTransactionHistoryByAccount() {
        // Arrange
        when(transactionRepository.findByFromAccount(accountId)).thenReturn(transactionList);
        when(transactionDtoMapper.transactionDtoMapper(transactionList)).thenReturn(transactionDTOList);

        // Act
        List<TransactionDTO> result = transactionService.getTransactionHistoryByAccount(accountId);

        // Assert
        assertNotNull(result);
        assertEquals(6, result.size());
    }

    @Test
    void testGetTransactionHistoryByAccountAndDuration() {
        Integer duration = 6;

        // Arrange
        when(transactionRepository.findByFromAccount(accountId)).thenReturn(transactionList);
        when(transactionDtoMapper.transactionDtoMapper(transactionList)).thenReturn(transactionDTOList);

        // Act
        List<TransactionDTO> result = transactionService.getTransactionHistoryByAccountAndDuration(accountId, duration);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
    }
}
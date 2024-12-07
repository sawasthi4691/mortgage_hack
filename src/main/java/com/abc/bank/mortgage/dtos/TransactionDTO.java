package com.abc.bank.mortgage.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private Long transactionId;

    @NotBlank
    private Long fromAccount;

    @NotBlank
    private Long toAccount;

    @NotBlank
    private Long amount;

    private LocalDateTime createdAt;

    private String transactionStatus;

    private String remark;

}

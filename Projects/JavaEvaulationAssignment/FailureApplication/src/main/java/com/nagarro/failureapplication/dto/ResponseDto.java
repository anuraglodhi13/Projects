package com.nagarro.failureapplication.dto;

import com.nagarro.failureapplication.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String accountNumber;
    private List<Transaction> failure;
}

package com.nagarro.pendingapplication.dto;

import com.nagarro.pendingapplication.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String accountNumber;
    private List<Transaction> pending;
}

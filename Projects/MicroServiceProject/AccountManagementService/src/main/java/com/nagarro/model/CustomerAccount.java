package com.nagarro.model;

import lombok.Data;

@Data
public class CustomerAccount {
    private Long customerId;
    private String name;
    private String accno;
    private String email;
    private String money;
}

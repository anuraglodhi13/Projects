package com.nagarro.model;


import lombok.Data;

@Data
public class Customer {

    private Long customerId;

    private String name;

    private String accno;

    private String email;
}

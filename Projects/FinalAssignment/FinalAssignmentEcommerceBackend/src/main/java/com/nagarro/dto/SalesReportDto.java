package com.nagarro.dto;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.nagarro.model.Product;

import lombok.Data;

@Data
public class SalesReportDto {
	public SalesReportDto(LocalDate dateCreated, String productName, String name, String email, Long orderId,
			Integer quantity, Double price, Double discount) {
		this.dateCreated = dateCreated;
		this.productName = productName;
		this.name = name;
		this.email = email;
		this.orderId = orderId;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}
	public SalesReportDto() {
		
	}
	private LocalDate dateCreated;
	private String productName;
	private String name;
	private String email;
	private Long orderId;
	private Integer quantity;
	private Double price;
	private Double discount;
	
}

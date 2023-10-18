package com.nagarro.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.nagarro.constants.ResponseMessageConstants;
import com.nagarro.dto.OrderRequestDto;
import com.nagarro.dto.SalesReportDto;
import com.nagarro.model.Order;
import com.nagarro.model.OrderProduct;
import com.nagarro.model.User;
import com.nagarro.repository.OrderRepository;
import com.nagarro.request.OrderRequest;
import com.nagarro.request.SalesReportRequest;
import com.nagarro.response.ResponseHandler;
import com.nagarro.service.OrderProductService;
import com.nagarro.service.OrderService;
import com.nagarro.service.UserService;

@CrossOrigin
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderProductService orderProductService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/ecommerce/order")
	public ResponseEntity<Object> createOrder (@RequestBody OrderRequest orderRequest) {
		try {
			List <OrderRequestDto> ordereRequestDto = orderRequest.getOrders();
//			System.out.print(ordereRequestDto);
			String username = orderRequest.getUsername();
//			System.out.print("Hi"+username);
			if(ordereRequestDto.size() >= 0 && StringUtils.isNotEmpty(username)) {
				// finding userId for given username 
				Optional<User> user = userService.findByUsername(username);
				if(!user.isPresent()) {
					return ResponseHandler.generateResponse(ResponseMessageConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND, null);
				}
				
				// todays date
				LocalDate todayDate = LocalDate.now();
				
				// creating order
				Order order = new Order();
				order.setDateCreated(todayDate);
				
				// saving order in db and getting order Id
				Long orderId = orderService.save(order).getId();
				
				// totalPrice for Order
				Double totalPrice = 0.00;
				
				List<OrderProduct> orderProducts = new ArrayList<>();
				
				for(OrderRequestDto orderProductDto : ordereRequestDto) {
					
					OrderProduct orderProduct = new OrderProduct(orderId,orderProductDto.getProduct().getProductId(),orderProductDto.getQuantity(),user.get().getUserId());
					orderProducts.add(orderProduct);
					
					totalPrice += ( ( orderProductDto.getProduct().getPrice() - orderProductDto.getProduct().getDiscount() )*orderProductDto.getQuantity());
					
					orderProductService.createOrderProduct(orderProduct); // saving order product relations in db
				}
				
				Optional <Order> orderInDb = orderService.getOrder(orderId);
				if(orderInDb.isPresent()) { // updating date and orderproducts in same order object
					
					orderInDb.get().setOrderProducts(orderProducts);
					orderInDb.get().setTotalPrice(totalPrice);
					Order savedOrder = orderService.save(orderInDb.get());
					
					return ResponseHandler.generateResponse(ResponseMessageConstants.ORDER_CREATED, HttpStatus.CREATED, savedOrder);
				}
				else {
					return ResponseHandler.generateResponse(ResponseMessageConstants.ORDER_NOT_CREATED, HttpStatus.NOT_FOUND, null);
				}
			}
			
			return ResponseHandler.generateResponse(ResponseMessageConstants.ORDER_REQUEST_NOT_COMPATIBLE, HttpStatus.BAD_REQUEST, null);
		}catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(ResponseMessageConstants.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	@PostMapping("/ecommerce/salesreport/{username}")
	public ResponseEntity<Object> salesReport (@RequestBody SalesReportRequest salesReportRequest , @PathVariable String username) {
		try {
			Optional <User> user = userService.findByUsername(username);
			if(!user.isPresent()) {
				return ResponseHandler.generateResponse(ResponseMessageConstants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST, null);
			}
			LocalDate localDateFrom = LocalDate.parse(salesReportRequest.getFromDate());
			LocalDate localDateTo = LocalDate.parse(salesReportRequest.getToDate());
			Set <Object[]> salesReportDb = orderService.generateSalesReport(localDateFrom,localDateTo,salesReportRequest.getBrand()
					,salesReportRequest.getCategory(),user.get().getUserId());
			if(salesReportDb.size() != 0) {
				List <SalesReportDto> salesReport = new ArrayList<>();
				salesReportDb.stream().forEach((record) -> {
					salesReport.add(mappingWithSales(record));
				});
				
				return ResponseHandler.generateResponse(ResponseMessageConstants.SALES_REPORT_GENERATED, HttpStatus.OK, salesReport);
			}
			
			return ResponseHandler.generateResponse(ResponseMessageConstants.NO_DETAILS_FOUND, HttpStatus.OK, null);
			
		}catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(ResponseMessageConstants.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	
	private SalesReportDto mappingWithSales(Object[] record) {
		return new SalesReportDto(LocalDate.parse(record[0].toString()),
				record[1].toString(),record[2].toString(),record[3].toString(),Long.parseLong(record[4].toString()),Integer.parseInt(record[5].toString())
				,new Double(record[6].toString()).doubleValue(),new Double(record[7].toString()).doubleValue());
	}
}

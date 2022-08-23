package com.demo.order_management.ordermanagement.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.order_management.ordermanagement.controller.CustomerController;
import com.demo.order_management.ordermanagement.model.Customer;
import com.demo.order_management.ordermanagement.request.CustomerRequest;
import com.demo.order_management.ordermanagement.response.BaseCustomerResponse;
import com.demo.order_management.ordermanagement.response.CustomerResponse;
import com.demo.order_management.ordermanagement.service.CustomerService;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	private CustomerRequest setCustomer() {
		CustomerRequest customerRequest =  new CustomerRequest();
		customerRequest.setCustomerName("Jhon Doe");
		customerRequest.setEmail("jhondoe@gmail.com");
		customerRequest.setPassword("password123!!");
		customerRequest.setPhone("3232111000");
		customerRequest.setBillingInfo("123 House Address, City, State, zcode");
		customerRequest.setCustomerAddress("123 House Address, City, State, zcode");
		return customerRequest;
	}
	
	private CustomerResponse getCustomerResponse() {
		CustomerResponse customerResponse =  new CustomerResponse();
		customerResponse.setCustomerId(123L);
		customerResponse.setCustomerName("Jhon Doe");
		customerResponse.setEmail("jhondoe@gmail.com");
		customerResponse.setPassword("password123!!");
		customerResponse.setCustomerAddress("123 House Address, City, State, zcode");
		return customerResponse;
	}
	private CustomerResponse getNullCustomerResponse() {
		CustomerResponse customerResponse =  new CustomerResponse();
		return customerResponse;
	}
	private Customer getCustomer() {
		Customer customerRequest =  new Customer();
		customerRequest.setCustomerId(123L);
		customerRequest.setCustomerName("Jhon Doe");
		customerRequest.setEmail("jhondoe@gmail.com");
		customerRequest.setPassword("password123!!");
		customerRequest.setPhone("3232111000");
		customerRequest.setBillingInfo("123 House Address, City, State, zcode");
		customerRequest.setCustomerAddress("123 House Address, City, State, zcode");
		return customerRequest;
	}
	private BaseCustomerResponse getBaseResponse() {
		BaseCustomerResponse baseResponse = new BaseCustomerResponse();
		baseResponse.setData(getCustomerResponse());
		baseResponse.setMessage("Approved");
		return baseResponse;
	}
	private BaseCustomerResponse getNullBaseResponse() {
		BaseCustomerResponse baseResponse = new BaseCustomerResponse();
		baseResponse.setData(getNullCustomerResponse());
		baseResponse.setMessage("Approved");
		return baseResponse;
	}
	
	private CustomerRequest setNullCustomer() {
		CustomerRequest customerRequest =  new CustomerRequest();
		return customerRequest;
	}
	private Customer getNull() {
		Customer customerRequest =  new Customer();
		return customerRequest;
	}
	
	@Test
	public void createTest() {
		Mockito.when(customerService.addCustomer(Mockito.any())).thenReturn(getCustomer());
		ResponseEntity<Customer> response = customerController.create(setCustomer());
		assertNotNull(response);
	}
	@Test
	public void createExceptionTest() {
		Mockito.when(customerService.addCustomer(Mockito.any())).thenReturn(getNull());
		ResponseEntity<Customer> response = customerController.create(setNullCustomer());
		assertEquals(getNull().getCustomerId(), response.getBody().getCustomerId());
	}
	
	@Test
	public void readTest() {
		Mockito.when(customerService.getCustomer(Mockito.any())).thenReturn(getBaseResponse());
		ResponseEntity<BaseCustomerResponse> response = customerController.read(123L);
		assertNotNull(response.getBody().getData().getCustomerId());
	}
	@Test
	public void readExceptionTest() {
		Mockito.when(customerService.getCustomer(Mockito.any())).thenReturn(getNullBaseResponse());
		ResponseEntity<BaseCustomerResponse> response = customerController.read(123L);
		assertEquals(getNull().getCustomerId(), response.getBody().getData().getCustomerId());
	}
	@Test
	public void readbyemailTest() {
		Mockito.when(customerService.getCustomerbyEmail(Mockito.any())).thenReturn(getCustomer());
		ResponseEntity<Customer> response = customerController.readbyemail("jhondoe@gmail.com");
		assertNotNull(response);
	}
	@Test
	public void readbyemailExceptionTest() {
		Mockito.when(customerService.getCustomerbyEmail(Mockito.any())).thenReturn(getNull());
		ResponseEntity<Customer> response = customerController.readbyemail("jhondoe@gmail.com");
		assertEquals(getNull().getCustomerId(), response.getBody().getCustomerId());
	}
	@Test
	public void readallTest() {
		List<Customer> list = new ArrayList<>();
		list.add(getCustomer());
		Mockito.when(customerService.getCustomers()).thenReturn(list);
		ResponseEntity<List<Customer>> response = customerController.readall();
		assertNotNull(response);
	}
	@Test
	public void readallExceptionTest() {
		List<Customer> list = new ArrayList<>();
		Mockito.when(customerService.getCustomers()).thenReturn(list);
		ResponseEntity<List<Customer>> response = customerController.readall();
		assertNotNull(response);
	}
	@Test
	public void readallbypaginationTest() {
		List<Customer> list = new ArrayList<>();
		list.add(getCustomer());
		Mockito.when(customerService.getCustomerswithpagination(Mockito.any(), Mockito.any())).thenReturn(list);
		ResponseEntity<List<Customer>> response = customerController.readallbypagination(1,1);
		assertNotNull(response);
	}
	@Test
	public void readallbypaginationExceptionTest() {
		List<Customer> list = new ArrayList<>();
		Mockito.when(customerService.getCustomerswithpagination(Mockito.any(), Mockito.any())).thenReturn(list);
		ResponseEntity<List<Customer>> response = customerController.readallbypagination(1,1);
		assertNotNull(response);
	}
	@Test
	public void deletebyIdTest() {
		Mockito.when(customerService.deleteCustomerbyID(Mockito.any())).thenReturn(true);
		ResponseEntity<Boolean> response = customerController.deletebyId(123L);
		assertNotNull(response.getBody());
	}
	@Test
	public void deletebyIdExceptionTest() {
		Mockito.when(customerService.deleteCustomerbyID(Mockito.any())).thenReturn(false);
		ResponseEntity<Boolean> response = customerController.deletebyId(123L);
		assertNotNull(response.getBody());
	}
	@Test
	public void deletebyEmailTest() {
		Mockito.when(customerService.deleteCustomerbyEmail(Mockito.any())).thenReturn(true);
		ResponseEntity<Boolean> response = customerController.deletebyEmail("jhondoe@gmail.com");
		assertNotNull(response.getBody());
	}
	@Test
	public void deletebyEmailExceptionTest() {
		Mockito.when(customerService.deleteCustomerbyEmail(Mockito.any())).thenReturn(false);
		ResponseEntity<Boolean> response = customerController.deletebyEmail("jhondoe@gmail.com");
		assertNotNull(response.getBody());
	}
	@Test
	public void updatebyIdTest() {
		Mockito.when(customerService.update(Mockito.any(), Mockito.any())).thenReturn(getCustomer());
		ResponseEntity<Customer> response = customerController.updatebyid(123L, setCustomer());
		assertNotNull(response.getBody());
	}
	@Test
	public void updatebyIdExceptionTest() {
		Mockito.when(customerService.update(Mockito.any(), Mockito.any())).thenReturn(getNull());
		ResponseEntity<Customer> response = customerController.updatebyid(123L, setCustomer());
		assertEquals(null, response.getBody().getCustomerId());
	}
	@Test
	public void updatebyEmailTest() {
		Mockito.when(customerService.updatebyEmail(Mockito.any(), Mockito.any())).thenReturn(getCustomer());
		ResponseEntity<Customer> response = customerController.updatebyEmail("jhondoe@gmail.com", setCustomer());
		assertNotNull(response.getBody());
	}
	@Test
	public void updatebyEmailExceptionTest() {
		Mockito.when(customerService.updatebyEmail(Mockito.any(), Mockito.any())).thenReturn(getNull());
		ResponseEntity<Customer> response = customerController.updatebyEmail("jhondoe@gmail.com", setCustomer());
		assertEquals(null, response.getBody().getCustomerId());
	}
}

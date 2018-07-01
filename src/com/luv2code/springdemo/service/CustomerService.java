package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luv2code.springdemo.entity.Customer;

@Service
public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerById(int customerId);

	public void deleteCustomerById(int customerId);

	public List<Customer> searchCustomers(String theSearchName);
}

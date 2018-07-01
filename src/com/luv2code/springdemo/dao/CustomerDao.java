package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerDao {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomerById(int customerId);

	public void deleteCustomerById(int customerId);

	public List<Customer> searchCustomers(String theSearchName);
}

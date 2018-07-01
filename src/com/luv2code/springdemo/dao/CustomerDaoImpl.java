package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		
		Session currentSession=sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery=currentSession.
				createQuery("from Customer order by lastName",
							Customer.class);
		
		List<Customer> customers=theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session session=sessionFactory.getCurrentSession();
		
		//this method saves new customers and updates existing customers
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.getCurrentSession();
		
		Customer theCustomer=session.get(Customer.class, customerId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomerById(int theId) {

		Session session=sessionFactory.getCurrentSession();
		
		Customer theCustomer=session.get(Customer.class, theId);
		
		//this also will delete customer from DB
		//Query<Customer> theQuery=currentSession.
					//createQuery("delete from Customer where id=:customerId");
		//theQuery.setParameter("customerId",theId);
		//theQuery.executeUpdate();
		
		session.delete(theCustomer);
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName "
					+ "	or lower(lastName) like :theName or lower(email) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Customer", Customer.class);			
		}
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
		
	}
}

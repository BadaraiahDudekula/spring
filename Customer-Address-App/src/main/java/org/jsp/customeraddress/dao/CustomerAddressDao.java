package org.jsp.customeraddress.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.customeraddress.dto.Address;
import org.jsp.customeraddress.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerAddressDao {
	@Autowired
	public EntityManager manager;
	
	public Customer saveUser(Customer customer) {
		EntityTransaction transaction=manager.getTransaction();
		manager.persist(customer);
		transaction.begin();
		transaction.commit();
		return customer;
	}
	
	public Address saveAddress(Address address ,int id) {
		Customer c=manager.find(Customer.class, id);
		if(c!=null) {
			address.setCustomer(c);
			c.getAddress().add(address);
			EntityTransaction transaction=manager.getTransaction();
			manager.persist(address);
			transaction.begin();
			transaction.commit();
			return address;
		}
		return null;
	}

	public Customer updateCustomer(Customer customer) {
		EntityTransaction transaction=manager.getTransaction();
		Customer dbUser=manager.find(Customer.class, customer.getId());
		if(dbUser!=null) {
			dbUser.setName(customer.getName());
			dbUser.setEmail(customer.getEmail());
			dbUser.setName(customer.getName());
			dbUser.setPassword(customer.getPassword());
			dbUser.setPhone(customer.getPhone());

			transaction.begin();
			transaction.commit();
			return customer;
		}
		return null;
	}
	
	public Customer findById(int id) {
		return manager.find(Customer.class,id);
	}
}

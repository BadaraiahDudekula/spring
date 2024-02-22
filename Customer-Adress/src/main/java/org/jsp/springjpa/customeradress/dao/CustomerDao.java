package org.jsp.springjpa.customeradress.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springjpa.customeradress.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
	@Autowired
	public EntityManager manager;

	public Customer save(Customer c) {
		manager.persist(c);
		EntityTransaction t = manager.getTransaction();
		t.begin();
		t.commit();
		return c;
	}

	public Customer updateCustomer(Customer c) {
		Customer cu = manager.find(Customer.class, c.getId());
		if (cu != null) {
			manager.merge(c);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return c;
		}
		return null;
	}
	public Customer findById(int id) {
		return manager.find(Customer.class, id);
	}
	
	public Customer verify(long phone,String password) {
		Query q=manager.createQuery("select c from Customer c where c.phone=?1 and c.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Customer) q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	public Customer verify(String email,String password) {
		Query q=manager.createQuery("select c from Customer c where c.email=?1 and c.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Customer) q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

}

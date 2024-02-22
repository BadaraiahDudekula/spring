package org.jsp.springjpa.customeradress.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springjpa.customeradress.dto.Adress;
import org.jsp.springjpa.customeradress.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdressDao {
	@Autowired
	public EntityManager manager;

	public Adress save(Adress ad, int id) {
		Customer c = manager.find(Customer.class, id);
		if (c != null) {
			ad.setCustomer(c);
			c.getAdress().add(ad);
			manager.persist(ad);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return ad;
		}
		return null;
	}

	public Adress update(Adress a, int id) {
		Customer c = manager.find(Customer.class, id);
		if (c != null) {
			a.setCustomer(c);
			// c.getAdress().add(ad);
			manager.merge(a);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return a;
		}
		return null;
	}

	public Adress findById(int id) {
		return manager.find(Adress.class, id);
	}

	public List<Adress> findByCustoId(int id) {
		Query q = manager.createQuery("select a from Adress a where a.customer.id=?1 ");
		q.setParameter(1, id);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Adress> findByCustoPhoneandPassword(long phone, String password) {
		Query q = manager.createQuery("select a from Adress a where a.customer.phone=?1 and a.customer.password=?2 ");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}

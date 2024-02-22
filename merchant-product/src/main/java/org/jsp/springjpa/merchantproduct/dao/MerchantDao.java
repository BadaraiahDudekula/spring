package org.jsp.springjpa.merchantproduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.jsp.springjpa.merchantproduct.dto.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	public EntityManager manager;

	public Merchant saveMerchantDetails(Merchant merchant) {
		manager.persist(merchant);
		EntityTransaction t = manager.getTransaction();
		t.begin();
		t.commit();
		return merchant;
	}

	public Merchant updateMerchantDetails(Merchant merchant) {
		manager.merge(merchant);
		EntityTransaction t = manager.getTransaction();
		t.begin();
		t.commit();
		return merchant;
	}

	public Merchant getMercgantId(int id) {
		return manager.find(Merchant.class, id);
	}

	public boolean merchantDelete(int id) {

		Merchant m = manager.find(Merchant.class, id);
		if (m != null) {
			manager.remove(m);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return true;
		}
		return false;
	}

	public Merchant verify(long phone, String password) {
		Query q = manager.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public Merchant verify(String email, String password) {
		Query q = manager.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Merchant verifyIdAndPassword(int id, String password) {
		Query q = manager.createQuery("select m from Merchant m where m.id=?1 and m.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}

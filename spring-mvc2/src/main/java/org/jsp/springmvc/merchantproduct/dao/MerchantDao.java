package org.jsp.springmvc.merchantproduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.springmvc.merchantproduct.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private EntityManager manager;

	public Merchant save(Merchant merchant) {
		manager.persist(merchant);
		EntityTransaction t = manager.getTransaction();
		t.begin();
		t.commit();
		return merchant;
	}

	public Merchant update(Merchant merchant) {
		Merchant m = manager.find(Merchant.class, merchant.getId());
		if (m != null) {
			manager.merge(merchant);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return merchant;
		}
		return null;
	}

	public Merchant findById(int id) {
		return manager.find(Merchant.class, id);
	}

	public boolean delete(int id) {
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

}
